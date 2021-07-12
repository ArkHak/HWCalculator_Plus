package com.example.hwcalculatorplus;

public class Variables {
    private double variableA;
    private double variableB;
    private double result;
    private String action;
    private boolean emptyA;

    public double getVariableA() {
        return variableA;
    }

    public void setVariableA(String variableA) {
        Double tmpA;
        if (variableA.isEmpty()) {
            tmpA = 0.0;
        } else {
            tmpA = Double.parseDouble(variableA);
        }
        if (this.emptyA) {
            this.variableA = tmpA;
        } else {
            calculate(tmpA);
            this.variableA = this.result;
        }

        this.emptyA = false;
    }

    public void calculate(Double tmpA) {
        switch (action) {
            case СalculatorАctions.PLUS:
                this.variableA += tmpA;
                break;
            case СalculatorАctions.MINUS.toString():
                this.variableA -= tmpA;
                break;
            case СalculatorАctions.MULTIPLY.toString():
                this.variableA *= tmpA;
                break;
            default:
                this.variableA = 0.0;
        }
    }

    public double getVariableB() {
        return variableB;
    }

    public void setVariableB(double variableB) {
        this.variableB = variableB;
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
        this.emptyA = true;
    }


    public void clear() {
        this.variableA = 0.0;
        this.variableB = 0.0;
        this.result = 0.0;
        this.action = null;
        this.emptyA = true;
    }

}
