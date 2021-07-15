package com.example.hwcalculatorplus;

public enum СalculatorАctions {
    MULTIPLY("×"),
    DIVISION("÷"),
    MINUS("−"),
    PLUS("+"),
    CALCULATE("="),
    POINT("."),
    NUM0("0");

    private final String action;

    СalculatorАctions(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return action;
    }
}
