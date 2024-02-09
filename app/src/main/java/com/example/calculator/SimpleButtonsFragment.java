package com.example.calculator;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SimpleButtonsFragment extends Fragment {

    private CalculatorViewModel viewModel;
    private Double savedOperand;

    public SimpleButtonsFragment () {
        super(R.layout.fragment_simple_buttons);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);
    }

    private void executeOperation(MathOperation operation) {

    }
}
