package com.example.hwcalculatorplus;

public enum СalculatorАctions {
    MULTIPLY("×"),
    DIVISION("÷"),
    MINUS("−"),
    PLUS("+"),
    CALCULATE("="),
    POINT("."),
    NUM0("0"),
    NUM1("1"),
    NUM2("2"),
    NUM3("3"),
    NUM4("4"),
    NUM5("5"),
    NUM6("6"),
    NUM7("7"),
    NUM8("8"),
    NUM9("9");


    private String action;

    СalculatorАctions(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return action;
    }
}
