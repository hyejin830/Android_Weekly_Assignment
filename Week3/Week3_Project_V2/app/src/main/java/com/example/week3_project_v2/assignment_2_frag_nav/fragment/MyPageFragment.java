package com.example.week3_project_v2.assignment_2_frag_nav.fragment;


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
public class MyPageFragment extends Fragment {


    public MyPageFragment() {
        // Required empty public constructor
    }

    public static MyPageFragment newInstance(int instance) {

        Bundle args = new Bundle();
        args.putInt("MYPAGE",instance);

        MyPageFragment fragment = new MyPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_page, container, false);
    }

}
