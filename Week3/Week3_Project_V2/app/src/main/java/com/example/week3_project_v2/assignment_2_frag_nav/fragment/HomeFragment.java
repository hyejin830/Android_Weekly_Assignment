package com.example.week3_project_v2.assignment_2_frag_nav.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.week3_project_v2.R;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private final static String HOME_KEY = "HOME";
    private int buttonInt;


    private Button homeFragmentButton;
    private OnFragmentPushListener onFragmentPushListener;


    public HomeFragment() {
        // Required empty public constructor

    }

    public static HomeFragment newInstance(int instance) {

        Bundle args = new Bundle();
        args.putInt(HOME_KEY, instance);

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onFragmentPushListener = (OnFragmentPushListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            buttonInt = getArguments().getInt(HOME_KEY);
        }

        Log.d("HomeFragment", buttonInt + "");
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeFragmentButton = view.findViewById(R.id.btn_home_fragment);
        homeFragmentButton.setText(buttonInt + "번째 프래그먼트");
        homeFragmentButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home_fragment:
                onFragmentPushListener.onFragmentPushed();
                break;
        }
    }


    public interface OnFragmentPushListener {
        void onFragmentPushed();
    }
}
