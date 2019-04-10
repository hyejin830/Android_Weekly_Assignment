package com.example.week3_project_v2.assignment_2_frag_nav.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week3_project_v2.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderListFragment extends Fragment {


    public OrderListFragment() {
        // Required empty public constructor
    }

    public static OrderListFragment newInstance(int instance) {

        Bundle args = new Bundle();
        args.putInt("ORDERLIST", instance);

        OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_list, container, false);
    }

}
