package com.example.week3_project_v2.assignment_1_activity_fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.week3_project_v2.R;
import com.example.week3_project_v2.assignment_1_activity_life_cycle.NewActivity;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeCycleFstFragment extends Fragment implements View.OnClickListener {

    private OnTextChangedListener mListener;

    private static final String TAG = "FragmentLifeCycle";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach()");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnTextChangedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView()");

        View view = inflater.inflate(R.layout.fragment_life_cycle_fst, container, false);

        Button doStartNewActivity = view.findViewById(R.id.do_start_new_activity_in_frag);
        Button doCommunicateWithSecFragmentButton = view.findViewById(R.id.do_communicate_with_sec_fragment);

        doStartNewActivity.setOnClickListener(this);
        doCommunicateWithSecFragmentButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach()");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.do_start_new_activity_in_frag:
                Intent startNewActivityInFragIntent = new Intent(getActivity(), NewActivity.class);
                startActivity(startNewActivityInFragIntent);
                break;
            case R.id.do_communicate_with_sec_fragment:
                mListener.onTextChanged(getString(R.string.communicate_success));
                break;
        }
    }

    public interface OnTextChangedListener {
        public void onTextChanged(String text);
    }


}