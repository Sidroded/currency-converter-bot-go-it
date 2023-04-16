package com.goit.currencyconverterbotgoit.user;

import com.goit.currencyconverterbotgoit.bankapi.Currency;

public enum OperationType {
    UAH_TO_USD("UAH/USD", Currency.UAH, Currency.USD),
    UAH_TO_EUR("UAH/EUR", Currency.UAH, Currency.EUR),
    USD_TO_UAH("USD/UAH", Currency.USD, Currency.UAH),
    EUR_TO_UAH("EUR/UAH", Currency.EUR, Currency.UAH);

    private final String operationName;
    private final Currency from;
    private final Currency to;

    OperationType(String operationName, Currency from, Currency to) {
        this.operationName = operationName;
        this.from = from;
        this.to = to;
    }

    public String getOperationName() {
        return operationName;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }
}
