package com.example.calculator;

public class History {

    private String currentValue;
    private String storedUnaryOperators;
    private String storedOperand;
    private int lengthOfLastOperatorSymbol;

    public History() {
        currentValue = "";
        storedUnaryOperators = "";
        storedOperand = "";
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void addOperation(Operation operation, String operand) {
        String operatorSymbol = operation.getOperatorSymbol();
        if (operation instanceof UnaryOperation)
            addUnaryOperation((UnaryOperation) operation, operand);
        else
            addBinaryOperation((BinaryOperation) operation, operand);
    }

    public void replaceLastBinaryOperator(BinaryOperation operation) {
        currentValue = currentValue.substring(0, currentValue.length() - lengthOfLastOperatorSymbol);
        currentValue += operation.getOperatorSymbol();
    }

    private void addUnaryOperation(UnaryOperation operation, String operand) {
        if (storedOperand.equals(""))
            storedOperand = operand;
        storedUnaryOperators = operation.getOperatorSymbol() + storedUnaryOperators;
    }

    private void addBinaryOperation(BinaryOperation operation, String operand) {
        if (!storedOperand.equals("")){
            currentValue += storedUnaryOperators + storedOperand;
            storedUnaryOperators = "";
            storedOperand = "";
        }
        else
            currentValue += operand;
        String operator = operation.getOperatorSymbol();
        currentValue += operator;
        lengthOfLastOperatorSymbol = operator.length();
    }
}
