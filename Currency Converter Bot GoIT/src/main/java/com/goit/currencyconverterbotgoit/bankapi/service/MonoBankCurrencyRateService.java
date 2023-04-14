package com.goit.currencyconverterbotgoit.bankapi.service;

import com.goit.currencyconverterbotgoit.bankapi.Currency;
import com.goit.currencyconverterbotgoit.bankapi.CurrencyRateApiService;
import com.goit.currencyconverterbotgoit.bankapi.RateResponse;
import com.goit.currencyconverterbotgoit.bankapi.dto.MonoBankCurrencyResponseDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MonoBankCurrencyRateService implements CurrencyRateApiService {
    private final static String URL = "https://api.monobank.ua/bank/currency";
    private final Gson gson = new Gson();

    @Override
    public List<RateResponse> getRates() {
        String response;
        try {
            response = Jsoup.connect(URL).ignoreContentType(true).get().text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<MonoBankCurrencyResponseDto> responseDtos = convertResponse(response);
        Set<Integer> codes = Currency.codes();
        return responseDtos.stream()
                .filter(dto -> codes.contains(dto.getCurrencyCodeA())
                        && codes.contains(dto.getCurrencyCodeB()))
                .map(dto -> {
                    RateResponse rate = new RateResponse();
                    rate.setCurrencyTo(Currency.fromCode(dto.getCurrencyCodeA()));
                    rate.setCurrencyFrom(Currency.fromCode(dto.getCurrencyCodeB()));
                    rate.setRateBuy(dto.getRateBuy());
                    rate.setRateSell(dto.getRateSell());
                    return rate;
                })
                .collect(Collectors.toList());
    }

    private List<MonoBankCurrencyResponseDto> convertResponse(String response) {
        Type type = TypeToken
                .getParameterized(List.class, MonoBankCurrencyResponseDto.class)
                .getType();
        return gson.fromJson(response, type);
    }
}
