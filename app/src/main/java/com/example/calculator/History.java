//******************************************************************************
//  History.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class contains the logic for the calculator's history and stores the
//  MutableLiveData<String> that is observed by the DisplayFragment's TextView
//  responsible for displaying it.
//******************************************************************************
package com.example.calculator;

import androidx.lifecycle.MutableLiveData;

public class History {

    private final MutableLiveData<String> historyLiveData;
    private boolean resetNeeded;

    //--------------------------------------------------------------------------
    //  The constructor for the class.
    //--------------------------------------------------------------------------
    public History() {
        historyLiveData = new MutableLiveData<String>("");
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
    //  A getter method for historyLiveData.
    //--------------------------------------------------------------------------
    public MutableLiveData<String> getHistoryLiveData() {
        return historyLiveData;
    }

    //--------------------------------------------------------------------------
    //  This method concatenates a String to the String stored in
    //  historyLiveData.
    //--------------------------------------------------------------------------
    public void add(String str) {
        historyLiveData.setValue(historyLiveData.getValue() + str);
    }

    //--------------------------------------------------------------------------
    //  This method replaces the last character in the history string with a new
    //  one. Used when replaceLastBinaryOperation is invoked in the ViewModel.
    //--------------------------------------------------------------------------
    public void replaceLastCharacter(String str) {
        String historyStr = historyLiveData.getValue();
        historyLiveData.setValue(historyStr.substring(0, historyStr.length() - 1) + str);
    }

    //--------------------------------------------------------------------------
    //  This method clears the existing history.
    //--------------------------------------------------------------------------
    public void reset() {
        historyLiveData.setValue("");
        resetNeeded = false;
    }
}
