package com.example.calculator;

import java.util.function.Function;

public class UnaryOperation implements Operation{

    private final Function<Double, Double> unaryFunction;
    private final String operatorSymbol;
    private Double operand;

    public UnaryOperation(Function<Double, Double> unaryFunction,
                          String operatorSymbol, Double operand) {
        this.unaryFunction = unaryFunction;
        this.operatorSymbol = operatorSymbol;
        this.operand = operand;
    }

    public void setOperand(Double operand) {
        this.operand = operand;
    }

    @Override
    public String getOperatorSymbol() {
        return operatorSymbol;
    }

    @Override
    public Double getResult() {
        return unaryFunction.apply(operand);
    }
}
