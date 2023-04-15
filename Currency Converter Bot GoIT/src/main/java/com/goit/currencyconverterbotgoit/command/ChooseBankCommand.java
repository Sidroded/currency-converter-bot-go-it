package com.goit.currencyconverterbotgoit.command;

import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.constant.MessageText;
import com.goit.currencyconverterbotgoit.emoji.Emoji;
import com.goit.currencyconverterbotgoit.user.BankType;
import com.goit.currencyconverterbotgoit.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ChooseBankCommand {
    public static SendMessage getMessage(User user){
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(MessageText.CHOOSE_BANK_TEXT);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        InlineKeyboardButton monoBankButton = new InlineKeyboardButton();
        monoBankButton.setText(ButtonText.MONOBANK_BUTTON_TEXT + getEmoji(BankType.MONO_BANK,user));
        monoBankButton.setCallbackData(ButtonId.MONOBANK_BUTTON.getId());
        rowInLine.add(monoBankButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();

        InlineKeyboardButton privatBankButton = new InlineKeyboardButton();
        privatBankButton.setText(ButtonText.PRIVATBANK_BUTTON_TEXT + getEmoji(BankType.PRIVAT_BANK,user));
        privatBankButton.setCallbackData(ButtonId.PRIVATBANK_BUTTON.getId());
        rowInLine.add(privatBankButton);
        keyboard.add(rowInLine);
        rowInLine = new ArrayList<>();

        InlineKeyboardButton nbuBankButton = new InlineKeyboardButton();
        nbuBankButton.setText(ButtonText.NBU_BUTTON_TEXT + getEmoji(BankType.NATIONAL_BANK,user));
        nbuBankButton.setCallbackData(ButtonId.NBU_BUTTON.getId());
        rowInLine.add(nbuBankButton);
        keyboard.add(rowInLine);


        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);
        
        return message;
    }

    private static Emoji getEmoji(BankType bankType, User user){
        for(BankType bankUser: user.getBankTypes()){
            if(bankType.equals(bankUser)){
                return Emoji.CHECK;
            }
        }
        return Emoji.NOT;
    }
}