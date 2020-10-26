package com.demo.security;

import lombok.Getter;

public enum PermissionsConfig {
    READ("read"),
    WRITE("write"),
    MAKE_ORDER("make-order");

    @Getter
    private final String permission;

    PermissionsConfig(String permission) {
        this.permission = permission;
    }

}
