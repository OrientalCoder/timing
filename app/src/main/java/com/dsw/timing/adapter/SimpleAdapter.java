package com.dsw.timing.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsw.timing.R;
import com.dsw.timing.bean.Student;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
    private final String TAG = "SimpleAdapter";

    private List<Student> studentList;

    public SimpleAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder position: " + position );
        holder.textView.setText(studentList.get(position).getName());
        int res = R.drawable.dura1;
        switch (position%3) {
            case 0: res = R.drawable.dura1;break;
            case 1: res = R.drawable.katong1;break;
            case 2: res = R.drawable.katong2;break;
            default: break;
        }
        holder.imageView.setImageResource(res);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.img);
        }
    }

    public void refreshData() {
        notifyDataSetChanged();
    }
}
