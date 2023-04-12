package com.goit.currencyconverterbotgoit.constant;

public enum ButtonId {

    GET_INFO_BUTTON,
    SETTINGS_BUTTON,

    DIGITS_AFTER_DECIMAL_BUTTON,
    BANK_BUTTON,
    CURRENCY_BUTTON,
    NOTIFICATIONS_TIME_BUTTON,

    TWO_DIGITS_BUTTON,
    THREE_DIGITS_BUTTON,
    FOUR_DIGITS_BUTTON,

    USD_CURRENCY_BUTTON,
    EUR_CURRENCY_BUTTON,

    MONOBANK_BUTTON,
    PRIVATBANK_BUTTON,
    NBU_BUTTON;



    public String getId(){
        return this.name();
    }

}
