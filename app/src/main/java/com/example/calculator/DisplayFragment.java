package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class DisplayFragment extends Fragment {

    private CalculatorViewModel viewModel;
    private TextView displayView, historyView, memoryView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayView = requireView().findViewById(R.id.main_display);
        historyView = requireView().findViewById(R.id.history);
        memoryView = requireView().findViewById(R.id.memory);

        this.viewModel = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);

        Observer<String> displayObserver = createObserver(displayView);
        viewModel.getCurrentDisplay().observe(requireActivity(), displayObserver);

        Observer<String> historyObserver = createObserver(historyView);
        viewModel.getHistory().observe(requireActivity(), historyObserver);

        Observer<String> memoryObserver = createObserver(memoryView);
        viewModel.getMemory().observe(requireActivity(), memoryObserver);
    }

    private Observer<String> createObserver(TextView view) {
        return new Observer<String>() {
            @Override
            public void onChanged(String str) {
                view.setText(str);
            }
        };
    }
}
