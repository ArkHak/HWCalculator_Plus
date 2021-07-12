package com.example.hwcalculatorplus;

public class Variables {
    private double variableA;
    private double variableB;
    private double result;
    private String action;
    private final String PLUS = СalculatorАctions.PLUS.toString();
    private final String MINUS = СalculatorАctions.MINUS.toString();
    private final String MULTIPLY = СalculatorАctions.MULTIPLY.toString();
    private final String DIVISION = СalculatorАctions.DIVISION.toString();

    public double getVariableA() {
        return variableA;
    }

    public void setVariableA(String variableA) {
        if (variableA.isEmpty()) {
            this.variableA = 0.0;
        } else {
            this.variableA = Double.parseDouble(variableA);
        }
    }

    public double getVariableB() {
        return variableB;
    }

    public void setVariableB(String variableB) {
        if (variableB.isEmpty()) {
            this.variableB = 0.0;
        } else {
            this.variableB = Double.parseDouble(variableB);
        }
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Variables() {
        this.variableA = 0.0;
        this.variableB = 0.0;
        this.result = 0.0;
        this.action = null;
    }


    public void clear() {
        this.variableA = 0.0;
        this.variableB = 0.0;
        this.result = 0.0;
        this.action = null;
    }

    public void calculate() {
        if (action != null) {
            if (action.equals(PLUS)) result = variableA + variableB;
            if (action.equals(MINUS)) result = variableA - variableB;
            if (action.equals(MULTIPLY)) result = variableA * variableB;
            if (action.equals(DIVISION)) result = variableA / variableB;
        }
    }

    public void changeSignB() {
        variableB *= -1;
    }
}
