package com.example.calculator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private CalculatorNumFormatter fmt;
    private Double savedOperand;
    private BinaryOperation savedOperation;
    private String historyAppendage;
    private MutableLiveData<String> currentDisplay;
    private MutableLiveData<String> history;
    private MutableLiveData<String> memory;

    public Double getSavedOperand() {
        return savedOperand;
    }

    public void storeSavedOperand() {
        fmt.strToDouble(currentDisplay.getValue());
    }

    public BinaryOperation getSavedOperation() {
        return savedOperation;
    }

    public void setSavedOperation(BinaryOperation savedOperation) {
        this.savedOperation = savedOperation;
    }

    public MutableLiveData<String> getCurrentDisplay() {
        return currentDisplay;
    }

    public void setCurrentDisplay(String currentDisplay) {
        this.currentDisplay.setValue(currentDisplay);
    }

    public MutableLiveData<String> getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history.setValue(history);
    }

    public void appendToHistory(String addition) {
        String historyStr = history.getValue();
        history.setValue(historyStr + addition);
    }

    public MutableLiveData<String> getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory.setValue(memory);
    }

    public CalculatorNumFormatter getFmt() {
        return fmt;
    }

    public Double getCurrentOperand() {
        return fmt.strToDouble(currentDisplay.getValue());
    }
}
