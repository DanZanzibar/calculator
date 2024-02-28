//******************************************************************************
//  BasicButtonsFragment.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class represents the basic button portion of the calculator: the only
//  ones available in portrait mode.
//******************************************************************************
package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class BasicButtonsFragment extends Fragment {

    private CalculatorViewModel viewModel;
    private final BinaryOperation ADD = new BinaryOperation("+", Double::sum);
    private final BinaryOperation SUBTRACT = new BinaryOperation("-", (a, b) -> a - b);
    private final BinaryOperation MULTIPLY = new BinaryOperation("*", (a, b) -> a * b);
    private final BinaryOperation DIVIDE = new BinaryOperation("/", (a, b) -> a / b);
    private final UnaryOperation PERCENT = new UnaryOperation("%", a -> 0.01 * a);

    //--------------------------------------------------------------------------
    //  A basic constructor to inflate the layout.
    //--------------------------------------------------------------------------
    public BasicButtonsFragment() {
        super(R.layout.fragment_basic_buttons);
    }

    //--------------------------------------------------------------------------
    //  This method is used to set up the reference to the CalculatorViewModel
    //  and the various buttons in the fragment.
    //--------------------------------------------------------------------------
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);

        Button zeroButton = requireView().findViewById(R.id.zero);
        zeroButton.setOnClickListener(v -> viewModel.numberButtonPush("0"));

        Button oneButton = requireView().findViewById(R.id.one);
        oneButton.setOnClickListener(v -> viewModel.numberButtonPush("1"));

        Button twoButton = requireView().findViewById(R.id.two);
        twoButton.setOnClickListener(v -> viewModel.numberButtonPush("2"));

        Button threeButton = requireView().findViewById(R.id.three);
        threeButton.setOnClickListener(v -> viewModel.numberButtonPush("3"));

        Button fourButton = requireView().findViewById(R.id.four);
        fourButton.setOnClickListener(v -> viewModel.numberButtonPush("4"));

        Button fiveButton = requireView().findViewById(R.id.five);
        fiveButton.setOnClickListener(v -> viewModel.numberButtonPush("5"));

        Button sixButton = requireView().findViewById(R.id.six);
        sixButton.setOnClickListener(v -> viewModel.numberButtonPush("6"));

        Button sevenButton = requireView().findViewById(R.id.seven);
        sevenButton.setOnClickListener(v -> viewModel.numberButtonPush("7"));

        Button eightButton = requireView().findViewById(R.id.eight);
        eightButton.setOnClickListener(v -> viewModel.numberButtonPush("8"));

        Button nineButton = requireView().findViewById(R.id.nine);
        nineButton.setOnClickListener(v -> viewModel.numberButtonPush("9"));

        Button decimalButton = requireView().findViewById(R.id.decimal);
        decimalButton.setOnClickListener(v -> viewModel.numberButtonPush("."));

        Button plusMinusButton = requireView().findViewById(R.id.plus_minus);
        plusMinusButton.setOnClickListener(v -> viewModel.numberButtonPush("+/-"));

        Button equalsButton = requireView().findViewById(R.id.equals);
        equalsButton.setOnClickListener(v -> viewModel.equalsButtonPush());

        Button plusButton = requireView().findViewById(R.id.plus);
        plusButton.setOnClickListener(v -> viewModel.binaryButtonPush(ADD));

        Button minusButton = requireView().findViewById(R.id.subtract);
        minusButton.setOnClickListener(v -> viewModel.binaryButtonPush(SUBTRACT));

        Button multiplyButton = requireView().findViewById(R.id.multiply);
        multiplyButton.setOnClickListener(v -> viewModel.binaryButtonPush(MULTIPLY));

        Button divideButton = requireView().findViewById(R.id.divide);
        divideButton.setOnClickListener(v -> viewModel.binaryButtonPush(DIVIDE));

        Button percentButton = requireView().findViewById(R.id.percent);
        percentButton.setOnClickListener(v -> viewModel.unaryButtonPush(PERCENT));

        Button clearButton = requireView().findViewById(R.id.clear);
        clearButton.setOnClickListener(v -> viewModel.clearButtonPush());

        Button deleteButton = requireView().findViewById(R.id.delete);
        deleteButton.setOnClickListener(v -> viewModel.getMainDisplay().deleteButtonPush());
    }
}
