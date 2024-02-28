//******************************************************************************
//  MainActivity.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class is the main Activity for the app.
//******************************************************************************
package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //--------------------------------------------------------------------------
    //  A constructor for the class that handles inflating the layout.
    //--------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
