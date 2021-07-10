package com.example.hwcalculatorplus;

public enum СalculatorАctions {
    MULTIPLY("@string/btn_multiply"),
    DIVISION("@string/btn_division"),
    MINUS("@string/btn_minus"),
    PLUS("@string/btn_plus"),
    CALCULATE("@string/btn_calculate"),
    NUM0("@string/btn_0"),
    NUM1("@string/btn_1"),
    NUM2("@string/btn_2"),
    NUM3("@string/btn_3"),
    NUM4("@string/btn_4"),
    NUM5("@string/btn_5"),
    NUM6("@string/btn_6"),
    NUM7("@string/btn_7"),
    NUM8("@string/btn_8"),
    NUM9("@string/btn_9");


    private String action;

    СalculatorАctions(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return action;
    }
}
