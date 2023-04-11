package com.goit.currencyconverterbotgoit.bankapi.service;

import com.goit.currencyconverterbotgoit.bankapi.CurrencyRateApiService;
import com.goit.currencyconverterbotgoit.bankapi.RateResponse;
import com.goit.currencyconverterbotgoit.bankapi.dto.PrivatBankCurrencyResponseDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class PrivatBankCurrencyRateService implements CurrencyRateApiService {

    private final static String URL = "https://api.privatbank.ua/p24api/pubinfo";
    private final Gson gson = new Gson();

    @Override
    public List<RateResponse> getRates() {
        String response;
        try {
            response = Jsoup.connect(URL).ignoreContentType(true).get().text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<PrivatBankCurrencyResponseDto> responseDtos = convertResponse(response);
        return responseDtos.stream()
                .map(dto -> {
                    RateResponse rate = new RateResponse();
                    rate.setCurrencyTo(dto.getCcy());
                    rate.setCurrencyFrom(dto.getBase_ccy());
                    rate.setRateBuy(dto.getBuy());
                    rate.setRateSell(dto.getSale());
                    return rate;
                })
                .collect(Collectors.toList());
    }

    private List<PrivatBankCurrencyResponseDto> convertResponse(String response) {
        Type type = TypeToken
                .getParameterized(List.class, PrivatBankCurrencyResponseDto.class)
                .getType();
        return gson.fromJson(response, type);
    }
}
