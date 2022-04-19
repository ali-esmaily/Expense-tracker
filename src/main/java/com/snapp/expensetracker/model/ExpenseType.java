package com.snapp.expensetracker.model;

public enum ExpenseType {

    DAILY_PURCHASE("خرید روزانه"),
    CAFE_AND_RESTAURANT("کافه و رستوران"),
    COMMUTING("رفت و آمد"),
    HOME("خانه"),
    BEAUTY_AND_HEALTH("زیبایی و سلامت"),
    CHARGING_AND_BILLING("شارژ و قبض"),
    FASHION_AND_CLOTHING("مد و پوشاک"),
    CULTURAL_AND_ARTISTIC("فرهنگی و هنری"),
    ENTERTAINMENT("تفریح و سرگرمی"),
    SAVINGS("پس انداز و سرمایه گذاری"),
    EXERCISE_AND_FITNESS("ورزش و تندرستی"),
    MONEY_TRANSFER("انتقال وجه"),
    OTHER("سایر");

    public final String perName;


    public String getPerName() {
        return perName;
    }

    ExpenseType(String name) {
        this.perName = name;
    }

}

