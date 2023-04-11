package com.goit.currencyconverterbotgoit;

public class User {
    private String chatId;
    private BankType bank;
    private int countOfSymbolsAfterDot;
    private String notificationTime;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public BankType getBank() {
        return bank;
    }

    public void setBank(BankType bank) {
        this.bank = bank;
    }

    public int getCountOfSymbolsAfterDot() {
        return countOfSymbolsAfterDot;
    }

    public void setCountOfSymbolsAfterDot(int countOfSymbolsAfterDot) {
        this.countOfSymbolsAfterDot = countOfSymbolsAfterDot;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}