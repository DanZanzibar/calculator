//******************************************************************************
//  MainDisplay.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class contains the logic for the numerical main display of the
//  calculator and holds displayLiveData, which is observed by the
//  DisplayFragment's TextView responsible for displaying it in the UI.
//******************************************************************************
package com.example.calculator;

import androidx.lifecycle.MutableLiveData;

public class MainDisplay {

    private final MutableLiveData<String> displayLiveData;
    private Double result;
    private boolean resetNeeded;
    private boolean displayingResult;
    private final int MAX_DIGITS = 9;

    //--------------------------------------------------------------------------
    //  A constructor for the class.
    //--------------------------------------------------------------------------
    public MainDisplay() {
        displayLiveData = new MutableLiveData<String>("0");
        resetNeeded = false;
        displayingResult = false;
    }

    //--------------------------------------------------------------------------
    //  A getter method for displayLiveData.
    //--------------------------------------------------------------------------
    public MutableLiveData<String> getDisplayLiveData() {
        return displayLiveData;
    }

    //--------------------------------------------------------------------------
    //  This method returns a boolean indicating if the current display is an
    //  error.
    //--------------------------------------------------------------------------
    public boolean isDisplayingError() {
        return "Error".equals(displayLiveData.getValue());
    }

    //--------------------------------------------------------------------------
    //  This method clears the display, resetting it to zero.
    //--------------------------------------------------------------------------
    public void reset() {
        displayLiveData.setValue("0");
        resetNeeded = false;
        displayingResult = false;
    }

    //--------------------------------------------------------------------------
    //  A getter method for resetNeeded.
    //--------------------------------------------------------------------------
    public boolean isResetNeeded() {
        return resetNeeded;
    }

    //--------------------------------------------------------------------------
    //  A setter method for resetNeeded.
    //--------------------------------------------------------------------------
    public void setResetNeeded(boolean resetNeeded) {
        this.resetNeeded = resetNeeded;
    }

    //--------------------------------------------------------------------------
    //  This method returns the number currently in the display as a Double. If
    //  the displayed number is actually a truncated representation of a number
    //  that can't be fully displayed, it returns the complete value stored in
    //  'result'.
    //--------------------------------------------------------------------------
    public Double getDisplayedNum() {
        Double num;

        if (isDisplayingError()) {
            reset();
            num = 0.0;
        }
        else if (displayingResult)
            num = result;
        else
            num = strToDouble(displayLiveData.getValue());

        return num;
    }

    //--------------------------------------------------------------------------
    //  This method returns the currently displayed value as a String. It
    //  handles appropriate formatting.
    //--------------------------------------------------------------------------
    public String getDisplayedNumAsStr() {
        return doubleToStr(getDisplayedNum());
    }

    //--------------------------------------------------------------------------
    //  This method takes a Double value and sets the current display to a
    //  correctly formatted String representation of it. It also stores 'result'
    //  for full retrieval of the value later.
    //--------------------------------------------------------------------------
    public void displayResult(Double num) {
        result = num;
        displayLiveData.setValue(doubleToStr(num));
        displayingResult = true;
        resetNeeded = true;
    }

    //--------------------------------------------------------------------------
    //  This method is invoked whenever a calculation didn't return a real
    //  number and an error needs to be displayed.
    //--------------------------------------------------------------------------
    public void displayError() {
        displayLiveData.setValue("Error");
        resetNeeded = true;
    }

    //--------------------------------------------------------------------------
    //  This method handles adding a given number value - as a 1-length String -
    //  to the main display while suppressing extraneous leading zeros.
    //--------------------------------------------------------------------------
    public void numberButtonPush(String numAsStr) {
        String currentDisplayedStr = displayLiveData.getValue();
        if ("0".equals(currentDisplayedStr))
            displayLiveData.setValue(numAsStr);
        else if ("-0".equals(currentDisplayedStr))
            displayLiveData.setValue("-" + numAsStr);
        else
            displayLiveData.setValue(currentDisplayedStr + numAsStr);
    }

    //--------------------------------------------------------------------------
    //  This method is invoked when the decimal point button is pressed.
    //--------------------------------------------------------------------------
    public void decimalButtonPush() {
        if (!displayLiveData.getValue().contains("."))
            displayLiveData.setValue(displayLiveData.getValue() + ".");
    }

    //--------------------------------------------------------------------------
    //  This method is invoked when the plus/minus button is pressed.
    //--------------------------------------------------------------------------
    public void plusMinusButtonPush() {
        if (displayLiveData.getValue().charAt(0) == '-')
            displayLiveData.setValue(displayLiveData.getValue().substring(1));
        else
            displayLiveData.setValue("-" + displayLiveData.getValue());
    }

    //--------------------------------------------------------------------------
    //  This method is invoked when the DEL button is pressed.
    //--------------------------------------------------------------------------
    public void deleteButtonPush() {
        String currentDisplay = displayLiveData.getValue();
        if (currentDisplay.length() == 1)
            displayLiveData.setValue("0");
        else if (!resetNeeded)
            displayLiveData.setValue(currentDisplay.substring(0, currentDisplay.length() - 1));
    }

    //--------------------------------------------------------------------------
    //  This helper method correctly formats a String from a Double value.
    //--------------------------------------------------------------------------
    private String doubleToStr(Double num) {
        String numStr = String.format("%1." + MAX_DIGITS + "g", num);
        if (numStr.contains("e"))
            numStr = removeExtraZerosScientificNotation(numStr);
        else
            numStr = removeExtraZerosAfterDecimal(numStr);

        return numStr;
    }

    //--------------------------------------------------------------------------
    //  This helper method parses a String representation of a number, returning
    //  a Double.
    //--------------------------------------------------------------------------
    private Double strToDouble(String str) {
        return Double.parseDouble(str);
    }

    //--------------------------------------------------------------------------
    //  This helper method removes trailing zeros after the last significant
    //  digit of a decimal.
    //--------------------------------------------------------------------------
    private String removeExtraZerosAfterDecimal(String numStr) {
        if (numStr.contains(".") && lastDigitIsZeroOrDecimal(numStr))
            return removeExtraZerosAfterDecimal(numStr.substring(0, numStr.length() - 1));
        else
            return numStr;
    }

    //--------------------------------------------------------------------------
    //  This helper method removes trailing zeros after the last significant
    //  digit of a number in scientific notation.
    //--------------------------------------------------------------------------
    private String removeExtraZerosScientificNotation(String numStr) {
        String[] numStrArray = numStr.split("e");
        String decimal = removeExtraZerosAfterDecimal(numStrArray[0]);

        return decimal + "e" + numStrArray[1];
    }

    //--------------------------------------------------------------------------
    //  This helper method return a boolean based on if the last character of
    //  the String is zero or a decimal.
    //--------------------------------------------------------------------------
    private boolean lastDigitIsZeroOrDecimal(String numStr) {
        char lastChar = numStr.charAt(numStr.length() - 1);
        return lastChar == '0' || lastChar == '.';
    }
}
