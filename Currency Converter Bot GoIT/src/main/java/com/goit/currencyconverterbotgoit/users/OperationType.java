package com.goit.currencyconverterbotgoit.users;

public enum OperationType {
    UAH_TO_USD("Курс гривні до долара"),
    UAH_TO_EUR("Курс гривні до євро"),
    USD_TO_UAH("Курс долара до гривні"),
    EUR_TO_UAH("Курс євро до гривні");

    private final String operationName;

    OperationType(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }
}
