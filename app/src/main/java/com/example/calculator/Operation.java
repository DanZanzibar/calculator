package com.example.calculator;

public abstract class Operation implements CalculatorAction {

    private CalculatorViewModel viewModel;
    private String operatorSymbol;
    public Operation(CalculatorViewModel viewModel, String operatorSymbol) {
        this.viewModel = viewModel;
        this.operatorSymbol = operatorSymbol;
    }

    public CalculatorViewModel getViewModel() {
        return viewModel;
    }

    public String getOperatorSymbol() {
        return operatorSymbol;
    }
}
