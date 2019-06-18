package com.example.week4_project.assignment01;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week4_project.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<String> dataSet;

    public RecyclerViewAdapter(ArrayList<String> myDataSet) {
        dataSet = myDataSet;
    }


    //create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.textView.setText(dataSet.get(i));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void addAtPosition(int position, String text) {
        if (position > dataSet.size()) {
            position = dataSet.size();
        }

        dataSet.add(position, text);
        notifyItemInserted(position);
    }

    public void removeAtPosition(int position) {
        if (position < dataSet.size()) {
            dataSet.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void move(int fromPosition, int toPosition) {
        final String text = dataSet.get(fromPosition);
        dataSet.remove(fromPosition);
        dataSet.add(toPosition, text);
        notifyItemMoved(fromPosition, toPosition);
    }


}
