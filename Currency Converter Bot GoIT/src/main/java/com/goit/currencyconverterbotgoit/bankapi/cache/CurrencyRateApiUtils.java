package com.goit.currencyconverterbotgoit.bankapi.cache;

import com.goit.currencyconverterbotgoit.bankapi.Currency;
import com.goit.currencyconverterbotgoit.bankapi.CurrencyRateApiService;
import com.goit.currencyconverterbotgoit.bankapi.RateResponse;
import com.goit.currencyconverterbotgoit.bankapi.service.MonoBankCurrencyRateService;
import com.goit.currencyconverterbotgoit.bankapi.service.NationalBankCurrencyRateService;
import com.goit.currencyconverterbotgoit.bankapi.service.PrivatBankCurrencyRateService;
import com.goit.currencyconverterbotgoit.user.BankType;

import java.util.List;

public class CurrencyRateApiUtils {
    static CurrencyRateApiService getCurrencyRateApiService(BankType bankType) {
        return switch (bankType) {
            case PRIVAT_BANK -> new PrivatBankCurrencyRateService();
            case MONO_BANK -> new MonoBankCurrencyRateService();
            case NATIONAL_BANK -> new NationalBankCurrencyRateService();
        };
    }

    static void tryAddReverseRateResponse(RateResponse rateResponse, List<RateResponse> rateResponses) {
        if (!containsByCurrencies(rateResponses, rateResponse.getCurrencyTo(), rateResponse.getCurrencyFrom())) {
            rateResponses.add(constructReverseRateResponse(rateResponse));
        }
    }

    static boolean containsByCurrencies(List<RateResponse> rateResponses, Currency from, Currency to) {
        return rateResponses.stream()
                .anyMatch(rateResponse -> rateResponse.getCurrencyFrom().equals(from)
                        && rateResponse.getCurrencyTo().equals(to));
    }

    static RateResponse constructReverseRateResponse(RateResponse rateResponse) {
        RateResponse reverseRateResponse = new RateResponse();
        reverseRateResponse.setRateBuy(rateResponse.getRateSell());
        reverseRateResponse.setRateSell(rateResponse.getRateBuy());
        reverseRateResponse.setCurrencyFrom(rateResponse.getCurrencyTo());
        reverseRateResponse.setCurrencyTo(rateResponse.getCurrencyFrom());
        return reverseRateResponse;
    }
}
