package com.example.calculator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private CalculatorNumFormatter fmt;
    private MutableLiveData<String> historyDisplay;
    private History history;
    private boolean BinaryOperatorButtonPushedLast;

    public void executeOperatorButtonPush(Operation operation, String operand) {
        if (BinaryOperatorButtonPushedLast == true)
            replaceBinaryOperator();
        Double operandNum = fmt.strToDouble(operand);
        String operandFormatted = fmt.doubleToStr(operandNum);

        history.addOperation(operation, operandFormatted);
        historyDisplay.setValue(history.getCurrentValue());
    }

    private void replaceBinaryOperator(Operation operation) {

    }
}
