package com.example.calculator;

import java.util.function.BiFunction;

public class BinaryOperation implements Operation{

    private final BiFunction<Double, Double, Double> binaryFunction;
    private final String operatorSymbol;
    private Double firstOperand;
    private Double secondOperand;

    public BinaryOperation(String operatorSymbol,
                           BiFunction<Double, Double, Double> binaryFunction) {
        this.binaryFunction = binaryFunction;
        this.operatorSymbol = operatorSymbol;
    }

    public void setFirstOperand(Double firstOperand) {
        this.firstOperand = firstOperand;
    }

    public void setSecondOperand(Double secondOperand) {
        this.secondOperand = secondOperand;
    }

    @Override
    public String getOperatorSymbol() {
        return operatorSymbol;
    }

    @Override
    public Double getResult() {
        return binaryFunction.apply(firstOperand, secondOperand);
    }
}
