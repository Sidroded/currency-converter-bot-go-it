package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.bankapi.Currency;
import com.goit.currencyconverterbotgoit.bankapi.CurrencyRateApiService;
import com.goit.currencyconverterbotgoit.bankapi.RateResponse;
import com.goit.currencyconverterbotgoit.bankapi.service.MonoBankCurrencyRateService;
import com.goit.currencyconverterbotgoit.bankapi.service.NationalBankCurrencyRateService;
import com.goit.currencyconverterbotgoit.bankapi.service.PrivatBankCurrencyRateService;
import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.user.BankType;
import com.goit.currencyconverterbotgoit.user.OperationType;
import com.goit.currencyconverterbotgoit.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            CurrencyRateApiService apiService = getCurrencyRateApiService(bankType);
            Map<OperationType, RateResponse> rateResponseByOperationType = buildRateResponseByOperationTypeMap(apiService.getRates());

            for (OperationType operationType : user.getOperationTypes()) {
                RateResponse rateResponse = getRateResponseByOperationType(operationType, rateResponseByOperationType);
                rateMessageBuilder.append(formatMessage(rateResponse, bankType, user.getCountSymbolsAfterDot()))
                        .append("\n");
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

    private static CurrencyRateApiService getCurrencyRateApiService(BankType bankType) {
        switch (bankType) {
            case PRIVAT_BANK:
                return new PrivatBankCurrencyRateService();
            case MONO_BANK:
                return new MonoBankCurrencyRateService();
            case NATIONAL_BANK:
                return new NationalBankCurrencyRateService();
        }
        throw new IllegalArgumentException("Not supported bank type!");
    }

    private static Map<OperationType, RateResponse> buildRateResponseByOperationTypeMap(List<RateResponse> rateResponses) {
        return rateResponses.stream()
                .flatMap(rateResponse -> {
                    OperationType operationType = findOperationTypeByCurrencies(rateResponse.getCurrencyFrom(), rateResponse.getCurrencyTo());
                    return operationType != null ? Stream.of(Map.entry(operationType, rateResponse)) : null;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static RateResponse getRateResponseByOperationType(OperationType operationType,
                                                               Map<OperationType, RateResponse> rateResponseByOperationType) {
        return rateResponseByOperationType.computeIfAbsent(operationType, ot -> {
            OperationType reverseOperationType = findOperationTypeByCurrencies(ot.getTo(), ot.getFrom());
            return constructReverseRateResponse(rateResponseByOperationType.get(reverseOperationType));
        });
    }

    private static RateResponse constructReverseRateResponse(RateResponse rateResponse) {
        RateResponse reverseRateResponse = new RateResponse();
        reverseRateResponse.setRateBuy(rateResponse.getRateSell());
        reverseRateResponse.setRateSell(rateResponse.getRateBuy());
        reverseRateResponse.setCurrencyFrom(rateResponse.getCurrencyTo());
        reverseRateResponse.setCurrencyTo(rateResponse.getCurrencyFrom());
        return reverseRateResponse;
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