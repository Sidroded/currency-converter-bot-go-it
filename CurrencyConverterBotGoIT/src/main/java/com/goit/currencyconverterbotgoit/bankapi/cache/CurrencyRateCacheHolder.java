package com.goit.currencyconverterbotgoit.bankapi.cache;

import com.goit.currencyconverterbotgoit.bankapi.RateResponse;
import com.goit.currencyconverterbotgoit.user.BankType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyRateCacheHolder {
    private static final CurrencyRateCacheHolder INSTANCE = new CurrencyRateCacheHolder();
    private static final Map<BankType, List<RateResponse>> RATE_RESPONSES_BY_BANK = new HashMap<>();

    private CurrencyRateCacheHolder() {
    }

    public static CurrencyRateCacheHolder getInstance() {
        return INSTANCE;
    }

    public void put(BankType bankType, List<RateResponse> rateResponses) {
        RATE_RESPONSES_BY_BANK.put(bankType, rateResponses);
    }

    public List<RateResponse> get(BankType bankType) {
        return RATE_RESPONSES_BY_BANK.get(bankType);
    }
}