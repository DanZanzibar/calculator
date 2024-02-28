//******************************************************************************
//  ExtraButtonsFragment.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class represents the fragment of the app displaying the extra buttons
//  only visible when in landscape orientation.
//******************************************************************************
package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ExtraButtonsFragment extends Fragment {

    private CalculatorViewModel viewModel;

    private final UnaryOperation SQUARE_ROOT = new UnaryOperation("\u221A", Math::sqrt);
    private final BinaryOperation X_TO_THE_Y = new BinaryOperation("^", Math::pow);
    private final UnaryOperation E_TO_THE_X = new UnaryOperation("e^", Math::exp);
    private final UnaryOperation ONE_OVER_X = new UnaryOperation("1/", a -> 1 / a);
    private final UnaryOperation LOGARITHM = new UnaryOperation("log", Math::log10);
    private final UnaryOperation NATURAL_LOG = new UnaryOperation("ln", Math::log);
    private final UnaryOperation TEN_TO_THE_X = new UnaryOperation("10^", a -> Math.pow(10, a));

    // The below Operations are not declared final as they get defined when the calculator
    // mode is switched between degrees and radians.
    private UnaryOperation sine = new UnaryOperation("sin", a -> Math.sin(Math.toRadians(a)));
    private UnaryOperation cosine = new UnaryOperation("cos", a -> Math.cos(Math.toRadians(a)));
    private UnaryOperation tangent = new UnaryOperation("tan", a -> Math.tan(Math.toRadians(a)));
    private UnaryOperation inverseSine = new UnaryOperation("sin-1", a -> Math.toDegrees(Math.asin(a)));
    private UnaryOperation inverseCosine = new UnaryOperation("cos-1", a -> Math.toDegrees(Math.acos(a)));
    private UnaryOperation inverseTangent = new UnaryOperation("tan-1", a -> Math.toDegrees(Math.atan(a)));

    //--------------------------------------------------------------------------
    //  A constructor for the class that handles inflation of the layout.
    //--------------------------------------------------------------------------
    public ExtraButtonsFragment() {
        super(R.layout.fragment_extra_buttons);
    }

    //--------------------------------------------------------------------------
    //  This method handles setting up the ViewModel reference and all the
    //  buttons.
    //--------------------------------------------------------------------------
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);

        Button memoryRecallButton = requireView().findViewById(R.id.memory_recall);
        memoryRecallButton.setOnClickListener(v -> viewModel.memoryRecallButtonPush());

        Button memoryPlusButton = requireView().findViewById(R.id.memory_plus);
        memoryPlusButton.setOnClickListener(v -> viewModel.memoryPlusButtonPush());

        Button memoryMinusButton = requireView().findViewById(R.id.memory_minus);
        memoryMinusButton.setOnClickListener(v -> viewModel.memoryMinusButtonPush());

        Button memoryClearButton = requireView().findViewById(R.id.memory_clear);
        memoryClearButton.setOnClickListener(v -> viewModel.memoryClearButtonPush());

        Button squareRootButton = requireView().findViewById(R.id.square_root);
        squareRootButton.setOnClickListener(v -> viewModel.unaryButtonPush(SQUARE_ROOT));

        Button xToTheYButton = requireView().findViewById(R.id.x_to_the_y);
        xToTheYButton.setOnClickListener(v -> viewModel.binaryButtonPush(X_TO_THE_Y));

        Button eToTheXButton = requireView().findViewById(R.id.e_to_the_x);
        eToTheXButton.setOnClickListener(v -> viewModel.unaryButtonPush(E_TO_THE_X));

        Button naturalNumberButton = requireView().findViewById(R.id.the_natural_number);
        naturalNumberButton.setOnClickListener(v -> viewModel.constantButtonPush(Math.E));

        Button oneOverXButton = requireView().findViewById(R.id.one_over_x);
        oneOverXButton.setOnClickListener(v -> viewModel.unaryButtonPush(ONE_OVER_X));

        Button logButton = requireView().findViewById(R.id.log);
        logButton.setOnClickListener(v -> viewModel.unaryButtonPush(LOGARITHM));

        Button naturalLogButton = requireView().findViewById(R.id.natural_log);
        naturalLogButton.setOnClickListener(v -> viewModel.unaryButtonPush(NATURAL_LOG));

        Button piButton = requireView().findViewById(R.id.pi);
        piButton.setOnClickListener(v -> viewModel.constantButtonPush(Math.PI));

        Button tenToTheX = requireView().findViewById(R.id.ten_to_the_x);
        tenToTheX.setOnClickListener(v -> viewModel.unaryButtonPush(TEN_TO_THE_X));

        Button sineButton = requireView().findViewById(R.id.sine);
        sineButton.setOnClickListener(v -> viewModel.unaryButtonPush(sine));

        Button cosineButton = requireView().findViewById(R.id.cosine);
        cosineButton.setOnClickListener(v -> viewModel.unaryButtonPush(cosine));

        Button tangentButton = requireView().findViewById(R.id.tangent);
        tangentButton.setOnClickListener(v -> viewModel.unaryButtonPush(tangent));

        Button inverseSineButton = requireView().findViewById(R.id.inverse_sine);
        inverseSineButton.setOnClickListener(v -> viewModel.unaryButtonPush(inverseSine));

        Button inverseCosineButton = requireView().findViewById(R.id.inverse_cosine);
        inverseCosineButton.setOnClickListener(v -> viewModel.unaryButtonPush(inverseCosine));

        Button inverseTangentButton = requireView().findViewById(R.id.inverse_tangent);
        inverseTangentButton.setOnClickListener(v -> viewModel.unaryButtonPush(inverseTangent));

        Button degreeRadiansButton = requireView().findViewById(R.id.degree_radians);
        degreeRadiansButton.setOnClickListener(v -> this.degreeRadiansButtonPush());
    }

    //--------------------------------------------------------------------------
    //  This method is invoked when the degrees/radians button is pressed and
    //  redefines the trig functions accordingly.
    //--------------------------------------------------------------------------
    private void degreeRadiansButtonPush() {
        Button degreeRadiansButton = requireView().findViewById(R.id.degree_radians);

        String currentSetting = (String) degreeRadiansButton.getText();

        if (currentSetting.equals("Deg")) {
            sine = new UnaryOperation("sin", Math::sin);
            cosine = new UnaryOperation("cos", Math::cos);
            tangent = new UnaryOperation("tan", Math::tan);
            inverseSine = new UnaryOperation("sin-1", Math::asin);
            inverseCosine = new UnaryOperation("cos-1", Math::acos);
            inverseTangent = new UnaryOperation("tan-1", Math::atan);
            degreeRadiansButton.setText("Rad");
        }
        else {
            sine = new UnaryOperation("sin", a -> Math.sin(Math.toRadians(a)));
            cosine = new UnaryOperation("cos", a -> Math.cos(Math.toRadians(a)));
            tangent = new UnaryOperation("tan", a -> Math.tan(Math.toRadians(a)));
            inverseSine = new UnaryOperation("sin-1", a -> Math.toDegrees(Math.asin(a)));
            inverseCosine = new UnaryOperation("cos-1", a -> Math.toDegrees(Math.acos(a)));
            inverseTangent = new UnaryOperation("tan-1", a -> Math.toDegrees(Math.atan(a)));
            degreeRadiansButton.setText("Deg");
        }
    }
}
