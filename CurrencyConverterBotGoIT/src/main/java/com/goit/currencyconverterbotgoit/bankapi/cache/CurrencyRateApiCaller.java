package com.goit.currencyconverterbotgoit.bankapi.cache;

import com.goit.currencyconverterbotgoit.bankapi.CurrencyRateApiService;
import com.goit.currencyconverterbotgoit.bankapi.RateResponse;
import com.goit.currencyconverterbotgoit.user.BankType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.goit.currencyconverterbotgoit.bankapi.cache.CurrencyRateApiUtils.getCurrencyRateApiService;
import static com.goit.currencyconverterbotgoit.bankapi.cache.CurrencyRateApiUtils.tryAddReverseRateResponse;

public class CurrencyRateApiCaller implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(CurrencyRateApiCaller.class.getName());

    @Override
    public void run() {
        for (BankType bankType : BankType.values()) {
            LOGGER.info(String.format("Calling %s API...", bankType.getBankName()));
            CurrencyRateApiService apiService = getCurrencyRateApiService(bankType);
            try {
                List<RateResponse> bankRateResponses = apiService.getRates();
                List<RateResponse> resultRateResponses = new ArrayList<>(bankRateResponses);
                bankRateResponses.forEach(rateResponse -> tryAddReverseRateResponse(rateResponse, resultRateResponses));
                LOGGER.info(String.format("Caching %s currency rates: %s", bankType.getBankName(), bankRateResponses));
                CurrencyRateCacheHolder.getInstance().put(bankType, resultRateResponses);
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, String.format("Caught an exception during %s API call", bankType.getBankName()), e);
            }
        }
    }
}