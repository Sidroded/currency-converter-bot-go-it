package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.user.User;
import com.goit.currencyconverterbotgoit.user.UserService;
import com.goit.currencyconverterbotgoit.user.UserUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class StartCommand {

    public static SendMessage getMessage(User user) {
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(String.format("Ласкаво просимо %s. Цей бот допоможе відслідковувати актуальні курси валют", user.getUserName()));

        message.setReplyMarkup(replyKeyboardMarkup());

        return message;
    }

    private static ReplyKeyboardMarkup replyKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardButton getInfoButton = new KeyboardButton(ButtonText.GET_INFO_BUTTON_TEXT);
        KeyboardButton settingsButton = new KeyboardButton(ButtonText.SETTINGS_BUTTON_TEXT);

        List<KeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(getInfoButton);
        keyboardButtonsRow.add(settingsButton);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(keyboardButtonsRow);
        keyboardRows.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);

        return replyKeyboardMarkup;
    }

    public static User initializeUser(String chatId, String name) {
        User user = UserUtils.getDefaultUser(chatId, name);
        UserService.addUser(user);

        return user;
    }
}
