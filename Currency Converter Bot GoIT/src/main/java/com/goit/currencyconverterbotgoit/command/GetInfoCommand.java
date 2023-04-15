package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.bankapi.Currency;
import com.goit.currencyconverterbotgoit.bankapi.RateResponse;
import com.goit.currencyconverterbotgoit.bankapi.cache.CurrencyRateCacheHolder;
import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.user.BankType;
import com.goit.currencyconverterbotgoit.user.OperationType;
import com.goit.currencyconverterbotgoit.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.Optional;

public class GetInfoCommand {

    public static SendMessage getMessage(User user) {
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(buildMessage(user));

        InlineKeyboardButton sendInfoButton = new InlineKeyboardButton();
        sendInfoButton.setText(ButtonText.GET_INFO_BUTTON_TEXT);
        sendInfoButton.setCallbackData(ButtonId.GET_INFO_BUTTON.getId());

        InlineKeyboardButton settingsButton = new InlineKeyboardButton();
        settingsButton.setText(ButtonText.SETTINGS_BUTTON_TEXT);
        settingsButton.setCallbackData(ButtonId.SETTINGS_BUTTON.getId());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(List.of(List.of(sendInfoButton), List.of(settingsButton)));
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }

    private static String buildMessage(User user) {
        StringBuilder rateMessageBuilder = new StringBuilder();
        for (BankType bankType : user.getBankTypes()) {
            List<RateResponse> rateResponses = CurrencyRateCacheHolder.getInstance().get(bankType);

            for (OperationType operationType : user.getOperationTypes()) {
                Optional<RateResponse> rateResponse = getRateResponseByOperationType(operationType, rateResponses);
                rateResponse.ifPresent(response -> rateMessageBuilder.append(formatMessage(response, bankType, user.getCountSymbolsAfterDot()))
                        .append("\n"));
            }
            rateMessageBuilder.append("\n");
        }
        return rateMessageBuilder.toString();
    }

    private static String formatMessage(RateResponse rateResponse, BankType bankType, int countSymbolsAfterDot) {
        String format = "Курс в %s: %s/%s\n" +
                "Купівля: %." + countSymbolsAfterDot + "f\n" +
                "Продаж: %." + countSymbolsAfterDot + "f";
        return String.format(
                format,
                bankType.getBankName(),
                rateResponse.getCurrencyFrom().name(),
                rateResponse.getCurrencyTo().name(),
                rateResponse.getRateBuy(),
                rateResponse.getRateSell()
        );
    }

    private static Optional<RateResponse> getRateResponseByOperationType(OperationType operationType, List<RateResponse> rateResponses) {
        return rateResponses.stream()
                .filter(rateResponse -> operationType.equals(findOperationTypeByCurrencies(rateResponse.getCurrencyFrom(), rateResponse.getCurrencyTo())))
                .findAny();
    }

    private static OperationType findOperationTypeByCurrencies(Currency from, Currency to) {
        for (OperationType operationType : OperationType.values()) {
            if (operationType.getFrom() == from && operationType.getTo() == to) {
                return operationType;
            }
        }
        return null;
    }
}