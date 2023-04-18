package com.goit.currencyconverterbotgoit.user;

public enum DigitAfterDotType {
    TWO_DIGITS(2),
    THREE_DIGITS(3),
    FOUR_DIGITS(4);

    private final int chooseDigit;

    DigitAfterDotType(int chooseDigit) {
            this.chooseDigit = chooseDigit;
        }

        public int getChooseDigit() {
            return chooseDigit;
        }
    }