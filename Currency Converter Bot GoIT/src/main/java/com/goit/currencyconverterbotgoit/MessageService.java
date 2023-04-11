package com.goit.currencyconverterbotgoit;

import com.goit.currencyconverterbotgoit.bankapi.CurrencyRateApiService;
import com.goit.currencyconverterbotgoit.bankapi.RateResponse;
import com.goit.currencyconverterbotgoit.bankapi.service.MonoBankCurrencyRateService;
import com.goit.currencyconverterbotgoit.bankapi.service.NationalBankCurrencyRateService;
import com.goit.currencyconverterbotgoit.bankapi.service.PrivatBankCurrencyRateService;

import java.util.List;
import java.util.stream.Collectors;

public class MessageService {

    public static void main(String[] args) {
        MessageService messageService = new MessageService();
        User user = new User();
        user.setBank(BankType.PRIVAT_BANK);
        user.setCountOfSymbolsAfterDot(3);
        System.out.println(messageService.getMessage(user));
        user.setBank(BankType.MONO_BANK);
        user.setCountOfSymbolsAfterDot(5);
        System.out.println(messageService.getMessage(user));
        user.setBank(BankType.NATIONAL_BANK);
        user.setCountOfSymbolsAfterDot(4);
        System.out.println(messageService.getMessage(user));
    }

    public List<String> getMessage(User user) {
        CurrencyRateApiService apiService = getCurrencyRateApiService(user.getBank());
        List<RateResponse> rates = apiService.getRates();
        return rates.stream()
                .map(rateResponse -> formatMessage(user, rateResponse))
                .collect(Collectors.toList());
    }

    private String formatMessage(User user, RateResponse rateResponse) {
        String format = "Курс в %s: %s/%s\n" +
                "Купівля: %." + user.getCountOfSymbolsAfterDot() + "f\n" +
                "Продаж: %." + user.getCountOfSymbolsAfterDot() + "f";
        return String.format(
                format,
                user.getBank().getBankName(),
                rateResponse.getCurrencyFrom().name(),
                rateResponse.getCurrencyTo().name(),
                rateResponse.getRateBuy(),
                rateResponse.getRateSell()
        );
    }

    private CurrencyRateApiService getCurrencyRateApiService(BankType bankType) {
        switch (bankType) {
            case PRIVAT_BANK:
                return new PrivatBankCurrencyRateService();
            case MONO_BANK:
                return new MonoBankCurrencyRateService();
            case NATIONAL_BANK:
                return new NationalBankCurrencyRateService();
        }
        throw new IllegalArgumentException("Not supported bank type!");
    }
}