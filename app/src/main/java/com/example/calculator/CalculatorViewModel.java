package com.example.calculator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.function.Function;

public class CalculatorViewModel extends ViewModel {

    private Double savedOperand;
    private MathOperation savedOperator;
    private MutableLiveData<String> currentDisplay;
    private MutableLiveData<String> history;
    private MutableLiveData<String> memory;

    public Double getSavedOperand() {
        return savedOperand;
    }

    public void setSavedOperand(Double savedOperand) {
        this.savedOperand = savedOperand;
    }

    public MathOperation getSavedOperator() {
        return savedOperator;
    }

    public void setSavedOperator(MathOperation savedOperator) {
        this.savedOperator = savedOperator;
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

    public MutableLiveData<String> getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory.setValue(memory);
    }

    public void operatorButtonClick(MathOperation operation) {
        Double secondOperand = currentDisplay.getValue();
        Double calculatedValue = savedOperator.getNumber(savedOperand, )
    }
}
