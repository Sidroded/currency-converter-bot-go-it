package com.goit.currencyconverterbotgoit.constant;

public enum ButtonId {

    GET_INFO_BUTTON,
    SETTINGS_BUTTON,
    TEST_BUTTON,

    CHOOSE_DIGITS_AFTER_DECIMAL_BUTTON,
    TWO_DIGITS_BUTTON,
    THREE_DIGITS_BUTTON,
    FOUR_DIGITS_BUTTON,

    CHOOSE_BANK_BUTTON,
    MONOBANK_BUTTON,
    PRIVATBANK_BUTTON,
    NBU_BUTTON,

    CHOOSE_CURRENCY_BUTTON,
    USD_CURRENCY_BUTTON,
    EUR_CURRENCY_BUTTON,

    CHOOSE_NOTIFICATIONS_TIME_BUTTON;

    public String getId(){
        return this.name();
    }

}