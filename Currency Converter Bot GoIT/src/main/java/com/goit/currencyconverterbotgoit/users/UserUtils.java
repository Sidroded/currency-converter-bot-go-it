package com.goit.currencyconverterbotgoit.users;

import java.util.List;

public class UserUtils {
    public static User getDefaultUser(String chatId) {
        String userName = "User";
        String lastName = null;
        BankType bankType = BankType.MONO_BANK;
        int countSymbolsAfterDot = 2;
        OperationType operationType = OperationType.USD_TO_UAH;
        String notificationTime = null;

        User user = new User(userName, lastName, chatId, List.of(bankType), countSymbolsAfterDot, List.of(operationType), notificationTime);

        return user;
    }
}
