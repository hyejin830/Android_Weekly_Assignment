package com.example.week3_project_v2.assignment_1_additional;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<CharSequence> fromActivitytext = new MutableLiveData<>();
    private MutableLiveData<CharSequence> fromFragmentText = new MutableLiveData<>();

    public void setFromActivityText(CharSequence input){
        // 1)
        Log.d("hj_test","SharedViewModel - setFromActivityText");
        fromActivitytext.setValue(input);
    }

    public LiveData<CharSequence> getFromActivityText(){
        Log.d("hj_test","SharedViewModel - getFromActivityText");
        return fromActivitytext;
    }

    public void setFromFragmentText(CharSequence input) {
        Log.d("hj_test","SharedViewModel - setFromFragmentText");

        fromFragmentText.setValue(input);}

    public LiveData<CharSequence> getFromFragmentText(){
        Log.d("hj_test","SharedViewModel - getFromFragmentText");

        return fromFragmentText;}
}
