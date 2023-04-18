package com.goit.currencyconverterbotgoit.bankapi.service;

import com.goit.currencyconverterbotgoit.bankapi.Currency;
import com.goit.currencyconverterbotgoit.bankapi.CurrencyRateApiService;
import com.goit.currencyconverterbotgoit.bankapi.RateResponse;
import com.goit.currencyconverterbotgoit.bankapi.dto.NationalBankCurrencyResponseDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class NationalBankCurrencyRateService implements CurrencyRateApiService {
    private final static String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final Gson gson = new Gson();

    @Override
    public List<RateResponse> getRates() {
        String response;
        try {
            response = Jsoup.connect(URL).ignoreContentType(true).get().text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<NationalBankCurrencyResponseDto> responseDtos = convertResponse(response);
        return responseDtos.stream()
                .filter(item -> item.getCc() != null)
                .map(dto -> {
                    RateResponse rate = new RateResponse();
                    rate.setCurrencyTo(dto.getCc());
                    rate.setCurrencyFrom(Currency.UAH);
                    rate.setRateBuy(dto.getRate());
                    rate.setRateSell(dto.getRate());
                    return rate;
                })
                .collect(Collectors.toList());
    }

    private List<NationalBankCurrencyResponseDto> convertResponse(String response) {
        Type type = TypeToken
                .getParameterized(List.class, NationalBankCurrencyResponseDto.class)
                .getType();
        return gson.fromJson(response, type);
    }
}
