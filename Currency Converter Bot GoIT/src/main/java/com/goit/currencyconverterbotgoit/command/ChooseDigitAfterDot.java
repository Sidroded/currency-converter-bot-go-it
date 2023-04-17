package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.constant.MessageText;
import com.goit.currencyconverterbotgoit.emoji.Emoji;
import com.goit.currencyconverterbotgoit.user.DigitAfterDotType;
import com.goit.currencyconverterbotgoit.user.User;
import com.goit.currencyconverterbotgoit.user.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ChooseDigitAfterDot {
    public static SendMessage getMessage(User user){
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(MessageText.CHOOSE_DIGITS_AFTER_DECIMAL_TEXT);

        InlineKeyboardMarkup inlineKeyboardMarkup = setInclineKeyboardMarkupOfDigit(user);
        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;
    }

    private static Emoji getEmoji(DigitAfterDotType digitAfterDotType, User user){
        if(user.getCountSymbolsAfterDot() == digitAfterDotType.getChooseDigit()){
            return Emoji.CHECK;
        }
        else{
            return Emoji.NOT;
        }
    }
    private static InlineKeyboardMarkup setInclineKeyboardMarkupOfDigit(User user){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var twoDigitButton = new InlineKeyboardButton();
        twoDigitButton.setText(ButtonText.TWO_DIGITS_BUTTON_TEXT + " " + getEmoji(DigitAfterDotType.TWO_DIGITS,user).get());
        twoDigitButton.setCallbackData(ButtonId.TWO_DIGITS_BUTTON.getId());
        rowInLine.add(twoDigitButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();

        var threeDigitButton = new InlineKeyboardButton();
        threeDigitButton.setText(ButtonText.THREE_DIGITS_BUTTON_TEXT + " "  + getEmoji(DigitAfterDotType.THREE_DIGITS,user).get());
        threeDigitButton.setCallbackData(ButtonId.THREE_DIGITS_BUTTON.getId());
        rowInLine.add(threeDigitButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();

        var fourDigitButton = new InlineKeyboardButton();
        fourDigitButton.setText(ButtonText.FOUR_DIGITS_BUTTON_TEXT + " "  + getEmoji(DigitAfterDotType.FOUR_DIGITS,user).get());
        fourDigitButton.setCallbackData(ButtonId.FOUR_DIGITS_BUTTON.getId());
        rowInLine.add(fourDigitButton);
        keyboard.add(rowInLine);


        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public static User getEditUser(User user, DigitAfterDotType digitAfterDotType){
        int digitsOfUserModified = user.getCountSymbolsAfterDot();

        if (digitsOfUserModified != digitAfterDotType.getChooseDigit() ) {
            digitsOfUserModified = digitAfterDotType.getChooseDigit();
        }
        user.setCountSymbolsAfterDot(digitsOfUserModified);

        UserService.addUser(user);

        return user;
    }
    public static EditMessageText getEditMessage(User user, int messageId){
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(user.getChatId());
        editMessage.setMessageId(messageId);
        editMessage.setText(MessageText.CHOOSE_DIGITS_AFTER_DECIMAL_TEXT);

        InlineKeyboardMarkup keyboardMarkup = setInclineKeyboardMarkupOfDigit(user);
        editMessage.setReplyMarkup(keyboardMarkup);

        return editMessage;

    }
}
