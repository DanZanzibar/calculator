//******************************************************************************
//  DisplayFragment.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 4
//  This class represents the fragment of the app displaying the numerical
//  values, memory indicator, and history.
//******************************************************************************
package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class DisplayFragment extends Fragment {


    //--------------------------------------------------------------------------
    //  A constructor for the class - handles layout inflation.
    //--------------------------------------------------------------------------
    public DisplayFragment() {
        super(R.layout.fragment_display);
    }

    //--------------------------------------------------------------------------
    //  This method is used to set up the reference to the ViewModel and set
    //  up the Observers so that the calculator is always displaying the correct
    //  MutableLiveData String value.
    //--------------------------------------------------------------------------
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView displayView = requireView().findViewById(R.id.main_display);
        TextView historyView = requireView().findViewById(R.id.history);
        TextView memoryView = requireView().findViewById(R.id.memory);

        CalculatorViewModel viewModel = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);

        Observer<String> displayObserver = createObserver(displayView);
        viewModel.getMainDisplay().getDisplayLiveData().observe(getViewLifecycleOwner(), displayObserver);

        Observer<String> historyObserver = createObserver(historyView);
        viewModel.getHistory().getHistoryLiveData().observe(getViewLifecycleOwner(), historyObserver);

        Observer<String> memoryObserver = createObserver(memoryView);
        viewModel.getMemory().getMemoryDisplay().observe(requireActivity(), memoryObserver);
    }

    //--------------------------------------------------------------------------
    //  This helper method sets up an Observer for a given TextView.
    //--------------------------------------------------------------------------
    private Observer<String> createObserver(TextView view) {
        return new Observer<String>() {
            @Override
            public void onChanged(String str) {
                view.setText(str);
            }
        };
    }
}
