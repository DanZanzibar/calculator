//******************************************************************************
//  CalculatorViewModel.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class subclasses ViewModel and is used to store most of the logic of
//  the app, as well as maintain state during orientation changes.
//******************************************************************************
package com.example.calculator;

import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private final History history;
    private final MainDisplay mainDisplay;
    private final Memory memory;
    private boolean binaryButtonPushedLast;
    private BinaryOperation storedBinaryOperation;

    //--------------------------------------------------------------------------
    //  A constructor for the class.
    //--------------------------------------------------------------------------
    public CalculatorViewModel() {
        super();
        history = new History();
        mainDisplay = new MainDisplay();
        memory = new Memory();
        binaryButtonPushedLast = false;
        storedBinaryOperation = null;
    }

    //--------------------------------------------------------------------------
    //  A getter method for the stored MainDisplay instance.
    //--------------------------------------------------------------------------
    public MainDisplay getMainDisplay() {
        return mainDisplay;
    }

    //--------------------------------------------------------------------------
    //  A getter method for the stored History instance.
    //--------------------------------------------------------------------------
    public History getHistory() {
        return history;
    }

    //--------------------------------------------------------------------------
    //  A getter method for the stored Memory instance.
    //--------------------------------------------------------------------------
    public Memory getMemory() {
        return memory;
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by any binary operator button in the calculator
    //  and utilizes the BinaryOperation passed to it.
    //--------------------------------------------------------------------------
    public void binaryButtonPush(BinaryOperation operation) {
        if (binaryButtonPushedLast)
            replaceStoredBinaryOperation(operation);

        else {
            if (history.isResetNeeded())
                history.reset();
            history.add(mainDisplay.getDisplayedNumAsStr());
            history.add(operation.getOperatorSymbol());

            boolean operationFailed = false;
            if (storedBinaryOperation != null){
                storedBinaryOperation.setSecondOperand(mainDisplay.getDisplayedNum());
                Double result = storedBinaryOperation.getResult();
                if (Double.isNaN(result) || Double.isInfinite(result)){
                    operationFailed = true;
                    displayError();
                }
                else
                    mainDisplay.displayResult(result);
            }

	    // Store the new operation regardless if there was a previously stored binary
	    // operation, so long as executing the stored operation did not produce
	    // an error.
            if (!operationFailed) {
                operation.setFirstOperand(mainDisplay.getDisplayedNum());
                storedBinaryOperation = operation;
                mainDisplay.setResetNeeded(true);
            }
        }
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by any unary operator button in the calculator
    //  and utilizes the UnaryOperation passed to it.
    //--------------------------------------------------------------------------
    public void unaryButtonPush(UnaryOperation operation) {
        binaryButtonPushedLast = false;

        String operandStr = mainDisplay.getDisplayedNumAsStr();

        operation.setOperand(mainDisplay.getDisplayedNum());
        Double result = operation.getResult();

        // If the operation produces a non real number, display an error.
        if (Double.isNaN(result) || Double.isInfinite(result))
            displayError();
        else {
            mainDisplay.displayResult(result);
            history.reset();
            history.add(operation.getOperatorSymbol());
            history.add(operandStr);
            history.setResetNeeded(true);
            storedBinaryOperation = null;
        }
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by the equals sign button.
    //--------------------------------------------------------------------------
    public void equalsButtonPush() {
        if (storedBinaryOperation != null) {
            binaryButtonPushedLast = false;

            history.add(mainDisplay.getDisplayedNumAsStr());
            history.add("=");

            Double operand = mainDisplay.getDisplayedNum();
            storedBinaryOperation.setSecondOperand(operand);
            Double result = storedBinaryOperation.getResult();

            if (Double.isNaN(result) || Double.isInfinite(result))
                displayError();
            else {
                mainDisplay.displayResult(result);
                storedBinaryOperation = null;
                history.setResetNeeded(true);
            }
        }
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by the number, decimal point, and plus/minus
    //  buttons, with the button passing a string that represents what it is.
    //--------------------------------------------------------------------------
    public void numberButtonPush(String numAsStr) {
        binaryButtonPushedLast = false;

        if (mainDisplay.isResetNeeded())
            mainDisplay.reset();
        if (history.isResetNeeded())
            history.reset();

        if (numAsStr.equals("."))
            mainDisplay.decimalButtonPush();
        else if (numAsStr.equals("+/-"))
            mainDisplay.plusMinusButtonPush();
        else
            mainDisplay.numberButtonPush(numAsStr);
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by the clear button.
    //--------------------------------------------------------------------------
    public void clearButtonPush() {
        history.reset();
        mainDisplay.reset();
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by the memory plus button.
    //--------------------------------------------------------------------------
    public void memoryPlusButtonPush() {
        if (!mainDisplay.isDisplayingError()) {
            Double currentValue = mainDisplay.getDisplayedNum();
            memory.memoryPlusButtonPush(currentValue);
        }
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by the memory minus button.
    //--------------------------------------------------------------------------
    public void memoryMinusButtonPush() {
        if (!mainDisplay.isDisplayingError()) {
            Double currentValue = mainDisplay.getDisplayedNum();
            memory.memoryMinusButtonPush(currentValue);
        }
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by the memory clear button.
    //--------------------------------------------------------------------------
    public void memoryClearButtonPush() {
        memory.memoryClearButtonPush();
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by the memory recall button.
    //--------------------------------------------------------------------------
    public void memoryRecallButtonPush() {
        if (storedBinaryOperation == null)
            history.reset();
        mainDisplay.displayResult(memory.memoryRecall());
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by the constant button: pi and Euler's number.
    //--------------------------------------------------------------------------
    public void constantButtonPush(Double constant) {
        if (storedBinaryOperation == null)
            history.reset();
        mainDisplay.displayResult(constant);
    }

    //--------------------------------------------------------------------------
    //  This helper method replaces the stored BinaryOperation if another binary
    //  operator button is pushed immediately afterwards.
    //--------------------------------------------------------------------------
    private void replaceStoredBinaryOperation(BinaryOperation operation) {
        history.replaceLastCharacter(operation.getOperatorSymbol());
        operation.setFirstOperand(storedBinaryOperation.getFirstOperand());
        storedBinaryOperation = operation;
    }

    //--------------------------------------------------------------------------
    //  This helper method is used when the result of a calculation to display
    //  returns a non-real number.
    //--------------------------------------------------------------------------
    private void displayError() {
        mainDisplay.displayError();
        history.reset();
        storedBinaryOperation = null;
        binaryButtonPushedLast = false;
    }
}
