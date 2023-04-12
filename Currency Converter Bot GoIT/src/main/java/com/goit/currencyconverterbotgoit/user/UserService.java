package com.goit.currencyconverterbotgoit.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private static Map<String, User> users = new HashMap<>();

    public static User createUser(String userName, String lastName, String chatId, List<BankType> bankTypes, int countSymbolsAfterDot, List<OperationType> operationTypes, String notificationTime) {
        User user = new User(userName, lastName, chatId, bankTypes, countSymbolsAfterDot, operationTypes, notificationTime);
        addUser(user);
        return user;
    }

    public static void addUser(User user) {
        users.put(user.getChatId(), user);
    }

    public static User getUser(String chatId) {
        return users.get(chatId);
    }

    public static Map<String, User> getUsers() {
        return users;
    }
}