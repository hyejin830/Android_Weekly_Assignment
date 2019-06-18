package com.example.week4_project.assignment01;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.week4_project.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public RecyclerViewHolder(@NonNull View v) {
        super(v);
        textView = v.findViewById(R.id.item_text);
    }


}