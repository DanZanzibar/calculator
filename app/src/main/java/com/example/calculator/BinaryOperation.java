package com.example.calculator;

import java.util.function.BiFunction;

public class BinaryOperation extends Operation{

    private final BiFunction<Double, Double, Double> binaryFunction;

    public BinaryOperation(CalculatorViewModel viewModel, String operatorSymbol,
                           BiFunction<Double, Double, Double> binaryFunction) {
        super(viewModel, operatorSymbol);
        this.binaryFunction = binaryFunction;
    }

    @Override
    public void execute() {
        CalculatorViewModel viewModel = getViewModel();
        BinaryOperation savedOperation = viewModel.getSavedOperation();
        if (savedOperation != null)
            performSavedOperation();
        else {
            viewModel.storeSavedOperand();
        }

    }

    private void performSavedOperation() {
        CalculatorViewModel viewModel = getViewModel();

        Double firstOperand = viewModel.getSavedOperand();
        Double secondOperand = viewModel.getCurrentOperand();
        BinaryOperation savedOperation = viewModel.getSavedOperation();
        Double calculatedValue = savedOperation.calculate(firstOperand, secondOperand);
        String formattedValue = viewModel.getFmt().doubleToStr(calculatedValue);

        // This is wrong, should be adding current operator to history after the number. Or maybe not???
        viewModel.appendToHistory(savedOperation.getOperatorSymbol());
        viewModel.appendToHistory(viewModel.getFmt().doubleToStr(secondOperand));

        viewModel.storeSavedOperand();
        viewModel.setCurrentDisplay(formattedValue);

        viewModel.setSavedOperation(this);
    }

    private Double calculate(Double firstOperand, Double secondOperand) {
        return binaryFunction.apply(firstOperand, secondOperand);
    }
}
