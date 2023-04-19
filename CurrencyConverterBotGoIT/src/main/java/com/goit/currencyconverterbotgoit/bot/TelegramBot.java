package com.goit.currencyconverterbotgoit.bot;

import com.goit.currencyconverterbotgoit.bot.botconfig.BotConfig;
import com.goit.currencyconverterbotgoit.command.*;
import com.goit.currencyconverterbotgoit.constant.ButtonId;
import com.goit.currencyconverterbotgoit.constant.ButtonText;
import com.goit.currencyconverterbotgoit.user.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;

@Component
@EnableScheduling
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
            User user = UserService.getUser(chatId);

            switch (messageText) {
                case "/start" -> startCommandReceived(chatId, userName);
                case ButtonText.GET_INFO_BUTTON_TEXT -> getInfoCommandReceived(user);
                case ButtonText.SETTINGS_BUTTON_TEXT -> settingsCommandReceived(user);

                default -> {
                    if (ChooseNotificationTimeCommand.isMessageInWaiting(chatId) && ChooseNotificationTimeCommand.isTime(messageText)) {
                        ChooseNotificationTimeCommand.setNotificationTimeToUser(user, messageText);
                        getPositiveSetMessageNotification(user);
                    } else if (ChooseNotificationTimeCommand.isMessageInWaiting(chatId) && !ChooseNotificationTimeCommand.isTime(messageText)) {
                        getWrongDataMessageNotification(user);
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            User user = UserService.getUser(chatId);

            switch (ButtonId.valueOf(callBackData)) {
                case GET_INFO_BUTTON -> getInfoCommandReceived(user);
                case SETTINGS_BUTTON -> settingsCommandReceived(user);

                case CHOOSE_DIGITS_AFTER_DECIMAL_BUTTON -> chooseDigitAfterDotCommandReceived(user);
                case TWO_DIGITS_BUTTON -> updateChooseDigitAfterDotCommandReceived(user, messageId, DigitAfterDotType.TWO_DIGITS);
                case THREE_DIGITS_BUTTON -> updateChooseDigitAfterDotCommandReceived(user, messageId, DigitAfterDotType.THREE_DIGITS);
                case FOUR_DIGITS_BUTTON -> updateChooseDigitAfterDotCommandReceived(user, messageId, DigitAfterDotType.FOUR_DIGITS);

                case CHOOSE_BANK_BUTTON -> chooseBankCommandReceived(user);
                case MONOBANK_BUTTON -> updateChooseBankCommandReceived(user, messageId, BankType.MONO_BANK);
                case PRIVATBANK_BUTTON -> updateChooseBankCommandReceived(user, messageId, BankType.PRIVAT_BANK);
                case NBU_BUTTON -> updateChooseBankCommandReceived(user, messageId, BankType.NATIONAL_BANK);

                case CHOOSE_CURRENCY_BUTTON -> chooseOperationTypeCommandReceived(user);
                case USD_CURRENCY_BUTTON -> updateChooseOperationTypeCommandReceived(user, messageId, OperationType.USD_TO_UAH);
                case EUR_CURRENCY_BUTTON -> updateChooseOperationTypeCommandReceived(user, messageId, OperationType.EUR_TO_UAH);

                case CHOOSE_NOTIFICATIONS_TIME_BUTTON -> chooseNotificationTimeCommandReceived(user);
                case ENABLE_NOTIFICATIONS_TIME_BUTTON, CHANGE_NOTIFICATIONS_TIME_BUTTON -> getInfoMessageAboutSetNotification(user);
                case DISABLE_NOTIFICATIONS_TIME_BUTTON -> disableNotificationCommandReceived(user);
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

    private void getInfoCommandReceived(User user) {
        try {
            execute(GetInfoCommand.getMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void settingsCommandReceived(User user) {
        try {
            execute(SettingsCommand.getMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void chooseDigitAfterDotCommandReceived(User user) {
        try {
            execute(ChooseDigitAfterDot.getMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateChooseDigitAfterDotCommandReceived(User user, int messageId, DigitAfterDotType digitAfterDotType) {
        User editUser = ChooseDigitAfterDot.getEditUser(user, digitAfterDotType);

        try {
            execute(ChooseDigitAfterDot.getEditMessage(editUser, messageId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void chooseBankCommandReceived(User user) {
        try {
            execute(ChooseBankCommand.getMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateChooseBankCommandReceived(User user, int messageId, BankType bankType) {
        User editUser = ChooseBankCommand.getEditUser(user, bankType);

        try {
            execute(ChooseBankCommand.getEditMessage(editUser, messageId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void chooseOperationTypeCommandReceived(User user) {
        try {
            execute(ChooseOperationTypeCommand.getMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateChooseOperationTypeCommandReceived(User user, int messageId, OperationType operationType) {
        User editUser = ChooseOperationTypeCommand.getEditUser(user, operationType);

        try {
            execute(ChooseOperationTypeCommand.getEditMessage(editUser, messageId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void chooseNotificationTimeCommandReceived(User user) {
        try {
            execute(ChooseNotificationTimeCommand.getMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void getInfoMessageAboutSetNotification(User user) {
        try {
            execute(ChooseNotificationTimeCommand.getInfoMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void getPositiveSetMessageNotification(User user) {
        try {
            execute(ChooseNotificationTimeCommand.getResultEnabledNotificationMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void getWrongDataMessageNotification(User user) {
        try {
            execute(ChooseNotificationTimeCommand.getWrongMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void disableNotificationCommandReceived(User user) {
        try {
            execute(ChooseNotificationTimeCommand.getResultDisabledNotificationMessage(user));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 60000)
    public void sendInfoToUsers() {
        LocalTime now = LocalTime.now();

        for (User user : UserService.getUsers().values()) {
            if (user.getNotificationTime() != null) {
                LocalTime time = LocalTime.parse(user.getNotificationTime());
                if (time.getHour() == now.getHour() && time.getMinute() == now.getMinute()) {
                    getInfoCommandReceived(user);
                }
            }
        }
    }
}
