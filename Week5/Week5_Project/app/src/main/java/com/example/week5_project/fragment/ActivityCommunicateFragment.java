package com.example.week5_project.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week5_project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityCommunicateFragment extends Fragment {


    public ActivityCommunicateFragment() {
        // Required empty public constructor
    }

    public static ActivityCommunicateFragment newInstance() {

        Bundle args = new Bundle();

        ActivityCommunicateFragment fragment = new ActivityCommunicateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_communicate, container, false);
    }

}
