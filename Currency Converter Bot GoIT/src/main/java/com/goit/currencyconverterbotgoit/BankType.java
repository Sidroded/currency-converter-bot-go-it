package com.goit.currencyconverterbotgoit;

public enum BankType {
    PRIVAT_BANK("ПриватБанк"),
    MONO_BANK("Монобанк"),
    NATIONAL_BANK("НБУ");

    private final String bankName;

    BankType(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }
}