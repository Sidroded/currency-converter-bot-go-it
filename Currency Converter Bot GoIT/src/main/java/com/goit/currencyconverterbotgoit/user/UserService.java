package com.goit.currencyconverterbotgoit.user;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Map<String, User> users = new HashMap<>();

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