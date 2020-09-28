package com.demo.service.controller;

import lombok.Data;

public @Data class postOrderRequestData {
    private String text;
    private String password;

    public postOrderRequestData() {
        super();
    }

    public postOrderRequestData(String text, String password) {
        this.text = text;
        this.password = password;
    }
}
