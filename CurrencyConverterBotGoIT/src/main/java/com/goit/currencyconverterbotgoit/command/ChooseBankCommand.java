package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.constant.MessageText;
import com.goit.currencyconverterbotgoit.emoji.Emoji;
import com.goit.currencyconverterbotgoit.user.BankType;
import com.goit.currencyconverterbotgoit.user.User;
import com.goit.currencyconverterbotgoit.user.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ChooseBankCommand {
    public static SendMessage getMessage(User user){
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(MessageText.CHOOSE_BANK_TEXT);

        InlineKeyboardMarkup inlineKeyboardMarkup = setInclineKeyboardMarkupOfBank(user);
        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;
    }

    private static Emoji getEmoji(BankType bankType, User user){
        if(user.getBankTypes().contains(bankType)){
            return Emoji.CHECK;
        }
        else{
            return Emoji.NOT;
        }
    }
    private static InlineKeyboardMarkup setInclineKeyboardMarkupOfBank(User user){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var monoBankButton = new InlineKeyboardButton();
        monoBankButton.setText(ButtonText.MONOBANK_BUTTON_TEXT + getEmoji(BankType.MONO_BANK,user).get());
        monoBankButton.setCallbackData(ButtonId.MONOBANK_BUTTON.getId());
        rowInLine.add(monoBankButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();

        var privatBankButton = new InlineKeyboardButton();
        privatBankButton.setText(ButtonText.PRIVATBANK_BUTTON_TEXT + getEmoji(BankType.PRIVAT_BANK,user).get());
        privatBankButton.setCallbackData(ButtonId.PRIVATBANK_BUTTON.getId());
        rowInLine.add(privatBankButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();

        var nbuBankButton = new InlineKeyboardButton();
        nbuBankButton.setText(ButtonText.NBU_BUTTON_TEXT + getEmoji(BankType.NATIONAL_BANK,user).get());
        nbuBankButton.setCallbackData(ButtonId.NBU_BUTTON.getId());
        rowInLine.add(nbuBankButton);
        keyboard.add(rowInLine);


        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public static User getEditUser(User user, BankType bankType){
        List<BankType> banksOfUserModified = new ArrayList<>(user.getBankTypes());
        if(banksOfUserModified.contains(bankType) && banksOfUserModified.size() > 1 ){
            banksOfUserModified.remove(bankType);
        } else if (!banksOfUserModified.contains(bankType)) {
            banksOfUserModified.add(bankType);
        }
        user.setBankTypes(banksOfUserModified);

        UserService.addUser(user);

        return user;
    }

    public static EditMessageText getEditMessage(User user, int messageId){
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(user.getChatId());
        editMessage.setMessageId(messageId);
        editMessage.setText(MessageText.CHOOSE_BANK_TEXT);

        InlineKeyboardMarkup keyboardMarkup = setInclineKeyboardMarkupOfBank(user);
        editMessage.setReplyMarkup(keyboardMarkup);

        return editMessage;

    }


}
