package com.example.calculator;

public abstract class Operation implements CalculatorAction {

    private CalculatorViewModel viewModel;
    public Operation(CalculatorViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public CalculatorViewModel getViewModel() {
        return viewModel;
    }

    public abstract void concatToHistory();
}
