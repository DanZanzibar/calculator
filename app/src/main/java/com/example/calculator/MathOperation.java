package com.example.calculator;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MathOperation {

    private final BiFunction<Double, Double, Double> numberOperator;
    private final Function<String, String> historyOperator;

    public MathOperation(BiFunction<Double, Double, Double> numberOperator,
                         Function<String, String> historyOperator) {
        this.numberOperator = numberOperator;
        this.historyOperator = historyOperator;
    }

    public Double getNumber(Double firstOperand, Double secondOperand) {
        return numberOperator.apply(firstOperand, secondOperand);
    }

    public String getHistory(String currentHistory) {
        return historyOperator.apply(currentHistory);
    }
}
