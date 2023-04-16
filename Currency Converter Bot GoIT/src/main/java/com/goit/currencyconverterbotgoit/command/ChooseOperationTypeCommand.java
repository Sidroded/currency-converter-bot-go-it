package com.goit.currencyconverterbotgoit.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

public class ChooseOperationTypeCommand {

    public class ChooseOperationTypeCommand {

        private User user;

        public ChooseOperationTypeCommand(User user) {
            this.user = user;
        }

        public SendMessage getMessage() {
            StringBuilder messageText = new StringBuilder();
            messageText.append("Оберіть тип операції:");

            if (user.isCheckOperationEnabled()) {
                messageText.append("\n")
                        .append(ButtonText.CHECK)
                        .append(" \uD83D\uDD18");
            }

            if (user.isNotOperationEnabled()) {
                messageText.append("\n")
                        .append(ButtonText.NOT)
                        .append(" \uD83D\uDEAB");
            }

            SendMessage message = new SendMessage();
            message.setChatId(user.getId().toString());
            message.setText(messageText.toString());

            return message;
        }
    }