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
public class RestApiFragment extends Fragment {


    public RestApiFragment() {
        // Required empty public constructor
    }

    public static RestApiFragment newInstance() {

        Bundle args = new Bundle();

        RestApiFragment fragment = new RestApiFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_api, container, false);
    }

}
