package com.goit.currencyconverterbotgoit.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final Map<Long, User> users = new HashMap<>(); // Приватное поле users, которое представляет собой хеш-таблицу, используемую для хранения всех пользователей.

    // Метод createUser создает новый объект User, используя переданные параметры, устанавливает newUser в true, и затем вызывает метод addUser, чтобы добавить пользователя в список пользователей.
    public User createUser(long userId, String userName, String lastName, long chatId, BankType bankType, int countSymbolsAfterDot, List<OperationType> operationTypes, String notificationTime) {
        User user = new User(true, userId, userName, lastName, chatId, bankType, countSymbolsAfterDot, operationTypes, notificationTime);
        addUser(user);
        return user;
    }

    // Метод addUser добавляет переданный объект User в список пользователей, используя userId в качестве ключа в хеш-таблице.
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    // Метод updateUser обновляет существующий объект User, используя userId в качестве ключа в хеш-таблице.
    public void updateUser(User user) {
        users.put(user.getUserId(), user);
    }
}