package com.demo.dto.builder;

public class AutoPricedBuilder extends OrderBuilder{

    private final String text;

    public AutoPricedBuilder(String text){
        this.text = text;
    }

    @Override
    public void buildId() {
        order.setId(0);
    }

    @Override
    public void buildText() {
        order.setText(text);
    }

    @Override
    public void buildPrice() {
        order.setPrice(priceGenerator());
    }

    private float priceGenerator() {
        return Math.round(100f * (float) (100 + Math.random() * 400)) / 100f;
    }

}
