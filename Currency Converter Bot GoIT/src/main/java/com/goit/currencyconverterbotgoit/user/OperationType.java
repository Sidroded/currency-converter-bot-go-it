package com.goit.currencyconverterbotgoit.user;

public enum OperationType {
    UAH_TO_USD("UAH/USD"),
    UAH_TO_EUR("UAH/EUR"),
    USD_TO_UAH("USD/UAH"),
    EUR_TO_UAH("EUR/UAH");

    private final String operationName;

    OperationType(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }
}
