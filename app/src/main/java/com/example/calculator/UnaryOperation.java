//******************************************************************************
//  UnaryOperation.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class represents a unary math operation. Implements the Operation
//  interface.
//******************************************************************************
package com.example.calculator;

import java.util.function.Function;

public class UnaryOperation implements Operation{

    private final Function<Double, Double> unaryFunction;
    private final String operatorSymbol;
    private Double operand;

    //--------------------------------------------------------------------------
    //  A constructor that takes the computational function and operator symbol
    //  for the operation.
    //--------------------------------------------------------------------------
    public UnaryOperation(String operatorSymbol, Function<Double, Double> unaryFunction) {
        this.unaryFunction = unaryFunction;
        this.operatorSymbol = operatorSymbol;
    }

    //--------------------------------------------------------------------------
    //  A setter method for operand.
    //--------------------------------------------------------------------------
    public void setOperand(Double operand) {
        this.operand = operand;
    }

    //--------------------------------------------------------------------------
    //  A getter method for operatorSymbol.
    //--------------------------------------------------------------------------
    @Override
    public String getOperatorSymbol() {
        return operatorSymbol;
    }

    //--------------------------------------------------------------------------
    //  This method uses the Function 'unaryFunction' to calculate the result
    //  using the 'operand' value.
    //--------------------------------------------------------------------------
    @Override
    public Double getResult() {
        return unaryFunction.apply(operand);
    }
}
