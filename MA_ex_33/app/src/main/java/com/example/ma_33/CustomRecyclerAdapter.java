package com.example.ma_33;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder> {

    private final List<String> names;

    public CustomRecyclerAdapter(List<String> names) {
        this.names = names;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView largeTextView;
        public TextView smallTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            largeTextView = itemView.findViewById(R.id.textViewLarge);
            smallTextView = itemView.findViewById(R.id.textViewSmall);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.largeTextView.setText(names.get(position));
        holder.smallTextView.setText("кот");
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}