//******************************************************************************
//  BinaryOperation.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class represents a binary math operation. Implements the Operation
//  interface.
//******************************************************************************
package com.example.calculator;

import java.util.function.BiFunction;

public class BinaryOperation implements Operation{

    private final BiFunction<Double, Double, Double> binaryFunction;
    private final String operatorSymbol;
    private Double firstOperand;
    private Double secondOperand;

    //--------------------------------------------------------------------------
    //  A constructor that takes the computational function and operator symbol
    //  for the operation.
    //--------------------------------------------------------------------------
    public BinaryOperation(String operatorSymbol,
                           BiFunction<Double, Double, Double> binaryFunction) {
        this.binaryFunction = binaryFunction;
        this.operatorSymbol = operatorSymbol;
    }

    //--------------------------------------------------------------------------
    //  A getter method for firstOperand.
    //--------------------------------------------------------------------------
    public Double getFirstOperand() {
        return firstOperand;
    }

    //--------------------------------------------------------------------------
    //  A setter method for firstOperand.
    //--------------------------------------------------------------------------
    public void setFirstOperand(Double firstOperand) {
        this.firstOperand = firstOperand;
    }

    //--------------------------------------------------------------------------
    //  A setter method for secondOperand.
    //--------------------------------------------------------------------------
    public void setSecondOperand(Double secondOperand) {
        this.secondOperand = secondOperand;
    }

    //--------------------------------------------------------------------------
    //  A getter method for operatorSymbol.
    //--------------------------------------------------------------------------
    @Override
    public String getOperatorSymbol() {
        return operatorSymbol;
    }

    //--------------------------------------------------------------------------
    //  This method uses the stored BiFunction to calculate the result using the
    //  two stored operands and returns it.
    //--------------------------------------------------------------------------
    @Override
    public Double getResult() {
        return binaryFunction.apply(firstOperand, secondOperand);
    }
}
