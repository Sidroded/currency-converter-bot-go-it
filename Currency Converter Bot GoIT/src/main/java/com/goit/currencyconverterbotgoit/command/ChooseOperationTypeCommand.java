package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.constant.MessageText;
import com.goit.currencyconverterbotgoit.emoji.Emoji;
import com.goit.currencyconverterbotgoit.user.OperationType;
import com.goit.currencyconverterbotgoit.user.User;
import com.goit.currencyconverterbotgoit.user.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ChooseOperationTypeCommand {
    public static SendMessage getMessage(User user){
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(MessageText.CHOOSE_CURRENCY_TEXT);

        InlineKeyboardMarkup inlineKeyboardMarkup = setInclineKeyboardMarkupOfOperationType(user);
        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;
    }

    private static Emoji getEmoji(OperationType operationType, User user){
        if(user.getOperationTypes().contains(operationType)){
            return Emoji.CHECK;
        }
        else{
            return Emoji.NOT;
        }
    }

    private static InlineKeyboardMarkup setInclineKeyboardMarkupOfOperationType(User user){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var usdButton = new InlineKeyboardButton();
        usdButton.setText(ButtonText.USD_CURRENCY_BUTTON_TEXT + getEmoji(OperationType.USD_TO_UAH,user).get());
        usdButton.setCallbackData(ButtonId.USD_CURRENCY_BUTTON.getId());
        rowInLine.add(usdButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();

        var eurButton = new InlineKeyboardButton();
        eurButton.setText(ButtonText.EUR_CURRENCY_BUTTON_TEXT + getEmoji(OperationType.EUR_TO_UAH,user).get());
        eurButton.setCallbackData(ButtonId.EUR_CURRENCY_BUTTON.getId());
        rowInLine.add(eurButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public static User getEditUser(User user, OperationType operationType){
        List<OperationType> operationsOfUserModified = new ArrayList<>(user.getOperationTypes());
        if(operationsOfUserModified.contains(operationType) && operationsOfUserModified.size() > 1 ){
            operationsOfUserModified.remove(operationType);
        } else if (!operationsOfUserModified.contains(operationType)) {
            operationsOfUserModified.add(operationType);
        }
        user.setOperationTypes(operationsOfUserModified);

        UserService.addUser(user);

        return user;
    }

    public static EditMessageText getEditMessage(User user, int messageId){
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(user.getChatId());
        editMessage.setMessageId(messageId);
        editMessage.setText(MessageText.CHOOSE_CURRENCY_TEXT);

        InlineKeyboardMarkup keyboardMarkup = setInclineKeyboardMarkupOfOperationType(user);
        editMessage.setReplyMarkup(keyboardMarkup);

        return editMessage;
    }
}
