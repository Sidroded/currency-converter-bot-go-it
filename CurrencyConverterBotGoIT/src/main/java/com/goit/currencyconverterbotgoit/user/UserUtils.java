package com.goit.currencyconverterbotgoit.user;

import java.util.List;

public class UserUtils {
    public static User getDefaultUser(String chatId, String name) {
        String lastName = null;
        BankType bankType = BankType.MONO_BANK;
        int countSymbolsAfterDot = 2;
        OperationType operationType = OperationType.USD_TO_UAH;
        String notificationTime = null;
        return new User(name, lastName, chatId, List.of(bankType), countSymbolsAfterDot, List.of(operationType), notificationTime);
    }
}
