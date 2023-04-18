package com.goit.currencyconverterbotgoit.bankapi;

import java.util.List;

public interface CurrencyRateApiService {
    List<RateResponse> getRates();
}
