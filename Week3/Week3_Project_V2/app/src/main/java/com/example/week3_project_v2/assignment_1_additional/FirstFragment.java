package com.example.week3_project_v2.assignment_1_additional;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.week3_project_v2.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {

    private SharedViewModel viewModel;

    private TextView fragmentTextView;
    private EditText firstFragmentEditText;
    private Button fragmentOkButton;


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        fragmentTextView = v.findViewById(R.id.tv_from_activity_text);
        firstFragmentEditText = v.findViewById(R.id.et_first_fragment);
        fragmentOkButton = v.findViewById(R.id.btn_ok);
        fragmentOkButton.setOnClickListener(this);

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        }

        doObserveActivityText();

    }

    private void doObserveActivityText() {
        // Activity -> Fragment
        viewModel.getFromActivityText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                Log.d("hj_test","FirstFragment - onChanged");
                fragmentTextView.setText(charSequence);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                // Fragment -> Activity
                viewModel.setFromFragmentText(firstFragmentEditText.getText());
                break;
        }
    }
}
