package com.goit.currencyconverterbotgoit.users;

public class UserUtils {
    public static User getDefaultUser(long chatId) {
        User user = new User(true, 0, null, null, chatId, BankType.MONOBANK, 2, null, null);
        user.setNotificationTime(null);
        return user;
    }
}
