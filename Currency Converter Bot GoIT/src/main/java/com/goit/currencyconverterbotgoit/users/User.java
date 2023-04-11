package com.goit.currencyconverterbotgoit.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String userName;
    private String lastName;
    private String chatId;
    private BankType bankType;
    private int countSymbolsAfterDot;
    private List<OperationType> operationTypes = new ArrayList<>();
    private String notificationTime;

    public User(String userName, String lastName, String chatId, BankType bankType, int countSymbolsAfterDot, List<OperationType> operationTypes, String notificationTime) {
        this.userName = userName;
        this.lastName = lastName;
        this.chatId = chatId;
        this.bankType = bankType;
        this.countSymbolsAfterDot = countSymbolsAfterDot;
        this.operationTypes = operationTypes;
        this.notificationTime = notificationTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public BankType getBankType() {
        return bankType;
    }

    public void setBankType(BankType bankType) {
        this.bankType = bankType;
    }

    public int getCountSymbolsAfterDot() {
        return countSymbolsAfterDot;
    }

    public void setCountSymbolsAfterDot(int countSymbolsAfterDot) {
        this.countSymbolsAfterDot = countSymbolsAfterDot;
    }

    public List<OperationType> getOperationTypes() {
        return operationTypes;
    }

    public void setOperationTypes(List<OperationType> operationTypes) {
        this.operationTypes = operationTypes;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}