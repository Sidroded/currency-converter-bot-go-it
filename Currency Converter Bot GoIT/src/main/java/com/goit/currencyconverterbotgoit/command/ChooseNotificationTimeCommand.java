package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.constant.MessageText;
import com.goit.currencyconverterbotgoit.user.User;
import com.goit.currencyconverterbotgoit.user.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseNotificationTimeCommand {

    //мапа із сhatId та boolean щоб відслідковувати момент що наразі ми чекаємо повідомлення
    private static Map<String, Boolean> waitingNotificationMap = new HashMap<>();

    public static Map<String, Boolean> getMap() {
        return waitingNotificationMap;
    }


    //----------------Создание и изменение сообщений ---------------------------


    public static SendMessage getMessage(User user){
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());

        if(userHasNotifications(user)){
            message.setText(MessageText.DISABLED_NOTIFICATIONS_TIME_TEXT);
        }
        else{
            message.setText(MessageText.ENABLED_NOTIFICATIONS_TIME_TEXT);
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = getInclineKeyboardMarkup(user);

        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;
    }

    public static SendMessage getInfoMessage(User user){
        SendMessage infoMessage = new SendMessage();
        infoMessage.setChatId(user.getChatId());
        infoMessage.setText(MessageText.CHANGE_NOTIFICATIONS_TIME_TEXT);

        return infoMessage;
    }

    public static SendMessage getResultMessage(User user){
        SendMessage infoMessage = new SendMessage();
        infoMessage.setChatId(user.getChatId());
        infoMessage.setText(MessageText.ENABLED_NOTIFICATIONS_TIME_TEXT);

        return infoMessage;
    }

    public static EditMessageText editMessage(User user, int messageId){
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(user.getChatId());
        editMessage.setMessageId(messageId);

        if(userHasNotifications(user)){
            editMessage.setText(MessageText.DISABLED_NOTIFICATIONS_TIME_TEXT);
        }
        else{
            editMessage.setText(MessageText.ENABLED_NOTIFICATIONS_TIME_TEXT);
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = getInclineKeyboardMarkup(user);

        editMessage.setReplyMarkup(inlineKeyboardMarkup);

        return editMessage;
    }

    //-----------------Вспомогательные методы для сборки----------------------

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

    // ------------------Создание кнопок---------------------------------

    private static InlineKeyboardButton getEnabledButton(){
        InlineKeyboardButton enableButton = new InlineKeyboardButton();
        enableButton.setText(ButtonText.ENABLE_NOTIFICATIONS_TIME_BUTTON_TEXT);
        enableButton.setCallbackData(ButtonId.ENABLE_NOTIFICATIONS_TIME_BUTTON.getId());
        return enableButton;
    }
    private static InlineKeyboardButton getDisabledButton(){
        InlineKeyboardButton disableButton = new InlineKeyboardButton();
        disableButton.setText(ButtonText.DISABLE_NOTIFICATIONS_TIME_BUTTON_TEXT);
        disableButton.setCallbackData(ButtonId.DISABLE_NOTIFICATIONS_TIME_BUTTON.getId());
        return disableButton;
    }

    private static InlineKeyboardButton getSettingsTimeButton(){
        InlineKeyboardButton settingsTimeButton = new InlineKeyboardButton();
        settingsTimeButton.setText(ButtonText.CHANGE_NOTIFICATIONS_TIME_BUTTON_TEXT);
        settingsTimeButton.setCallbackData(ButtonId.CHANGE_NOTIFICATIONS_TIME_BUTTON.getId());
        return settingsTimeButton;
    }


    // ------------------Создание клавиатуры-----------------------------

    private static InlineKeyboardMarkup getInclineKeyboardMarkup(User user){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton enableButton = getEnabledButton();
        InlineKeyboardButton settingsTimeButton = getSettingsTimeButton();
        InlineKeyboardButton disableButton = getDisabledButton();

        if(userHasNotifications(user)){
            row.add(disableButton);
            row = new ArrayList<>();
            row.add(settingsTimeButton);
        }
        else{
            row.add(enableButton);
        }
        keyboard.add(row);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
    // ------------------Проверка на наличие уведомлений у юзера -----------------------------
    private static boolean userHasNotifications(User user){
        return user.getNotificationTime() != null;
    }




}
