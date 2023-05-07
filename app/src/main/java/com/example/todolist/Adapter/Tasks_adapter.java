package com.example.todolist.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Models.Data_task;
import com.example.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class Tasks_adapter extends RecyclerView.Adapter<Tasks_adapter.Tasks_viewHolder> {
    List<Data_task>Tasks=new ArrayList<>();


    public void add(Data_task task){
        Tasks.add(task);
        notifyItemInserted(0);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void add_tasks(List<Data_task> tasks){
        this.Tasks.addAll(tasks);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Tasks_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
       return new Tasks_viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tasks_viewHolder holder, int position) {
       holder.getTask(Tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return Tasks.size();
    }

    public class Tasks_viewHolder extends RecyclerView.ViewHolder{

        TextView title;
        CheckBox isselected;
        ImageView btn_edit,btn_delete;

        public Tasks_viewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.text_task);
            isselected=itemView.findViewById(R.id.checkbox);
            btn_edit=itemView.findViewById(R.id.btn_edit);
            btn_delete=itemView.findViewById(R.id.btn_delete);
        }

        public void getTask(Data_task data_task){
            title.setText(data_task.getTask_title());
        }
    }
}
