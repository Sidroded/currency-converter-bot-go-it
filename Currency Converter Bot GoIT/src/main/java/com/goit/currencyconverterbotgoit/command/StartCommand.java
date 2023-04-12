package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.constant.MessageText;
import com.goit.currencyconverterbotgoit.user.User;
import com.goit.currencyconverterbotgoit.user.UserService;
import com.goit.currencyconverterbotgoit.user.UserUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class StartCommand {

    public static SendMessage getMessage(User user) {
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(String.format(MessageText.START_TEXT, user.getUserName()));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var sendInfoButton = new InlineKeyboardButton();
        sendInfoButton.setText(ButtonText.GET_INFO_BUTTON_TEXT);
        sendInfoButton.setCallbackData(ButtonId.GET_INFO_BUTTON.getId());

        rowInLine.add(sendInfoButton);
        rowsInLine.add(rowInLine);

        rowInLine = new ArrayList<>();

        var settingsButton = new InlineKeyboardButton();
        settingsButton.setText(ButtonText.SETTINGS_BUTTON_TEXT);
        settingsButton.setCallbackData(ButtonId.SETTINGS_BUTTON.getId());

        rowInLine.add(settingsButton);
        rowsInLine.add(rowInLine);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;
    }

    public static User initializeUser(String chatId, String name) {
        User user = UserUtils.getDefaultUser(chatId, name);
        UserService.addUser(user);

        return user;
    }
}
