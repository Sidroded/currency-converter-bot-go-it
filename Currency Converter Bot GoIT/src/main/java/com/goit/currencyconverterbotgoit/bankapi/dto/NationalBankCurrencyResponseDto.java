package com.goit.currencyconverterbotgoit.bankapi.dto;

import com.goit.currencyconverterbotgoit.bankapi.Currency;

import java.math.BigDecimal;

public class NationalBankCurrencyResponseDto {
    private String txt;
    private BigDecimal rate;
    private Currency cc;
    private String exchangedate;

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Currency getCc() {
        return cc;
    }

    public void setCc(Currency cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

    @Override
    public String toString() {
        return "NationalBankCurrencyResponseDto{" +
                "txt='" + txt + '\'' +
                ", rate=" + rate +
                ", cc=" + cc +
                ", exchangedate='" + exchangedate + '\'' +
                '}';
    }
}
