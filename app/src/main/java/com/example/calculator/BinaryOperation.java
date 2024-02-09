package com.example.calculator;

import java.util.function.BiFunction;

public class BinaryOperation extends Operation{

    private String operatorSymbol;
    private BiFunction<Double, Double, Double> binaryFunction;

    public BinaryOperation(CalculatorViewModel viewModel, String operatorSymbol,
                           BiFunction<Double, Double, Double> binaryFunction) {
        super(viewModel);
        this.operatorSymbol = operatorSymbol;
        this.binaryFunction = binaryFunction;
    }
}
