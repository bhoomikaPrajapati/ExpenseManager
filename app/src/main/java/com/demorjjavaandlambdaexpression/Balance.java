package com.demorjjavaandlambdaexpression;

/**
 * Created by bhoomika on 30/1/17.
 */

public class Balance {
    private float cash;
    private float card;

    public Balance(float cash, float card) {
        this.card = card;
        this.cash = cash;

    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public float getCard() {
        return card;
    }

    public void setCard(float card) {
        this.card = card;
    }
}
