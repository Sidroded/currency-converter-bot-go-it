package com.goit.currencyconverterbotgoit;

import com.goit.currencyconverterbotgoit.bankapi.cache.CurrencyRateApiCaller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class CurrencyConverterBotGoItApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterBotGoItApplication.class, args);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new CurrencyRateApiCaller(), 0, 5, TimeUnit.MINUTES);
    }

}
