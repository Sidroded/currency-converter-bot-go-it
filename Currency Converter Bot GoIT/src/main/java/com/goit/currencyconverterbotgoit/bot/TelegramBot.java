package com.goit.currencyconverterbotgoit.bot;

import com.goit.currencyconverterbotgoit.bot.botconfig.BotConfig;
import com.goit.currencyconverterbotgoit.command.StartCommand;
import com.goit.currencyconverterbotgoit.user.User;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    final BotConfig config;
    public TelegramBot(BotConfig config){
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String chatId = String.valueOf(update.getMessage().getChatId());
            String userName = update.getMessage().getChat().getFirstName();

            switch (messageText) {
                case "/start" -> startCommandReceived(chatId, userName);

                default -> sendMessage(chatId, "Sorry, command was not recognized");
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());

            switch (callBackData) {

            }
        }
    }

    private void startCommandReceived(String chatId, String name){
        User user = StartCommand.initializeUser(chatId, name);

        try {
            execute(StartCommand.getMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void sendMessage(String chatId, String text){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try{
            execute(message);
        }
        catch(TelegramApiException ignored){}
    }
}
