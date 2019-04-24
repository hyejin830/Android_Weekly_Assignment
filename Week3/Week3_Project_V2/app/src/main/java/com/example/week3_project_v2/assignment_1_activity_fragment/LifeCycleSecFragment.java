package com.example.week3_project_v2.assignment_1_activity_fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week3_project_v2.R;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeCycleSecFragment extends Fragment {


    public LifeCycleSecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_cycle_sec, container, false);
    }

}
