package com.goit.currencyconverterbotgoit.bankapi;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Currency {
    EUR(978),
    UAH(980),
    USD(840),
    GBP(826),
    PLN(985);

    private final int code;

    Currency(int code) {
        this.code = code;
    }

    public static Currency fromCode(Integer code) {
        for (Currency currency : values()) {
            if (currency.getCode() == code) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Not valid code!");
    }

    public static Set<Integer> codes() {
        return Arrays.stream(values())
                .map(Currency::getCode)
                .collect(Collectors.toSet());
    }

    public int getCode() {
        return code;
    }
}
