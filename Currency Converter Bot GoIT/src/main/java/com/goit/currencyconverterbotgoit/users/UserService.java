package com.goit.currencyconverterbotgoit.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private static Map<String, User> users = new HashMap<>(); // Приватное статическое поле users, которое представляет собой хеш-таблицу, используемую для хранения всех пользователей.

    // Метод createUser создает новый объект User, используя переданные параметры и вызывает метод addUser, чтобы добавить пользователя в список пользователей.
    public static User createUser(String userName, String lastName, String chatId, BankType bankType, int countSymbolsAfterDot, List<OperationType> operationTypes, String notificationTime) {
        User user = new User(userName, lastName, chatId, bankType, countSymbolsAfterDot, operationTypes, notificationTime);
        addUser(user);
        return user;
    }

    // Метод addUser добавляет переданный объект User (переданного пользователя) в список пользователей, используя user.getChatId() в качестве ключа в хеш-таблице.
    public static void addUser(User user) {
        users.put(user.getChatId(), user);
    }

    // Метод getUser возвращает значение из HashMap по ключу chatId.
    public static User getUser(String chatId) {
        return users.get(chatId);
    }

    // Метод getUsers() возвращает весь HashMap (всех пользователей).
    public static Map<String, User> getUsers() {
        return users;
    }
}