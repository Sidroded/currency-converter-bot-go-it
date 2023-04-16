package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.constant.MessageText;
import com.goit.currencyconverterbotgoit.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SettingsCommand {
    public static SendMessage getMessage(User user){
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(MessageText.CHANGE_SETTINGS_TEXT);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var chooseDigitsButton = new InlineKeyboardButton();
        chooseDigitsButton.setText(ButtonText.DIGITS_AFTER_DECIMAL_BUTTON_TEXT);
        chooseDigitsButton.setCallbackData(ButtonId.CHOOSE_DIGITS_AFTER_DECIMAL_BUTTON.getId());
        rowInLine.add(chooseDigitsButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();

        var chooseBankButton = new InlineKeyboardButton();
        chooseBankButton.setText(ButtonText.BANK_BUTTON_TEXT);
        chooseBankButton.setCallbackData(ButtonId.CHOOSE_BANK_BUTTON.getId());
        rowInLine.add(chooseBankButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();


        var chooseCurrencyButton = new InlineKeyboardButton();
        chooseCurrencyButton.setText(ButtonText.CURRENCY_BUTTON_TEXT);
        chooseCurrencyButton.setCallbackData(ButtonId.CHOOSE_CURRENCY_BUTTON.getId());
        rowInLine.add(chooseCurrencyButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();


        var chooseNotificationTimeButton = new InlineKeyboardButton();
        chooseNotificationTimeButton.setText(ButtonText.NOTIFICATIONS_TIME_BUTTON_TEXT);
        chooseNotificationTimeButton.setCallbackData(ButtonId.CHOOSE_NOTIFICATIONS_TIME_BUTTON.getId());
        rowInLine.add(chooseNotificationTimeButton);
        keyboard.add(rowInLine);


        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }
}
