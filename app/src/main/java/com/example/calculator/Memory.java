//******************************************************************************
//  Memory.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class contains the logic for the calculator's memory, as well as the
//  memoryDisplay LiveData observed by DisplayFragment's TextView responsible
//  for indicating when it is active.
//******************************************************************************
package com.example.calculator;

import androidx.lifecycle.MutableLiveData;

public class Memory {

    private Double memoryValue;
    private final MutableLiveData<String> memoryDisplay;

    //--------------------------------------------------------------------------
    //  A constructor for the class.
    //--------------------------------------------------------------------------
    public Memory() {
        memoryValue = 0.0;
        memoryDisplay = new MutableLiveData<String>("");
    }

    //--------------------------------------------------------------------------
    //  A getter method for the memoryDisplay LiveData field.
    //--------------------------------------------------------------------------
    public MutableLiveData<String> getMemoryDisplay() {
        return memoryDisplay;
    }

    //--------------------------------------------------------------------------
    //  This method is invoked when the memory plus button is pushed.
    //--------------------------------------------------------------------------
    public void memoryPlusButtonPush(Double num) {
        memoryValue += num;
        updateDisplay();
    }

    //--------------------------------------------------------------------------
    //  This method is invoked when the memory minus button is pushed.
    //--------------------------------------------------------------------------
    public void memoryMinusButtonPush(Double num) {
        memoryValue -= num;
        updateDisplay();
    }

    //--------------------------------------------------------------------------
    //  This method is invoked when the memory clear button is pushed.
    //--------------------------------------------------------------------------
    public void memoryClearButtonPush() {
        memoryValue = 0.0;
        updateDisplay();
    }

    //--------------------------------------------------------------------------
    //  This method is invoked when the memory recall button is pushed.
    //--------------------------------------------------------------------------
    public Double memoryRecall() {
        return memoryValue;
    }

    //--------------------------------------------------------------------------
    //  This helper method sets the LiveData field 'memoryDisplay' to "M" if
    //  the current memory's value is nonzero.
    //--------------------------------------------------------------------------
    private void updateDisplay() {
        if (memoryValue != 0.0)
            memoryDisplay.setValue("M");
        else
            memoryDisplay.setValue("");
    }
}
