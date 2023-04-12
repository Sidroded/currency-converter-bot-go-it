package com.goit.currencyconverterbotgoit.emoji;

import com.vdurmont.emoji.EmojiParser;

public enum Emoji {
    CHECK(":white_check_mark:"),
    NOT(":x:");

    private String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Emoji(String value) {
        this.value = value;
    }
}
