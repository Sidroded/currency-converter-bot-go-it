package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.constant.MessageText;
import com.goit.currencyconverterbotgoit.user.User;
import com.goit.currencyconverterbotgoit.user.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ChooseNotificationTimeCommand {

    public static SendMessage getMessage(User user){
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton enableButton = new InlineKeyboardButton();
        enableButton.setText(ButtonText.ENABLE_NOTIFICATIONS_TIME_BUTTON_TEXT);
        enableButton.setCallbackData(ButtonId.ENABLE_NOTIFICATIONS_TIME_BUTTON.getId());

        InlineKeyboardButton disableButton = new InlineKeyboardButton();
        disableButton.setText(ButtonText.DISABLE_NOTIFICATIONS_TIME_BUTTON_TEXT);
        disableButton.setCallbackData(ButtonId.DISABLE_NOTIFICATIONS_TIME_BUTTON.getId());

        if(user.getNotificationTime() == null){
            message.setText(MessageText.DISABLED_NOTIFICATIONS_TIME_TEXT);
            row.add(enableButton);
        }
        else{
            message.setText(MessageText.ENABLED_NOTIFICATIONS_TIME_TEXT);
            row.add(disableButton);
        }
        keyboard.add(row);
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return message;
    }

    public static boolean isTime(String inputData){
        String regex = "^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$";
        return inputData.matches(regex);
    }

    public static void setNotificationTimeToUser(User user, String time){
        user.setNotificationTime(time);
        UserService.addUser(user);
    }

    public static void changeNotificationTimeOfUserToNull(User user){
        user.setNotificationTime(null);
        UserService.addUser(user);
    }
}
