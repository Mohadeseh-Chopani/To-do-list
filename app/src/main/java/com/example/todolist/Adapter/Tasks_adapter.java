package com.example.todolist.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Models.Data_task;
import com.example.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class Tasks_adapter extends RecyclerView.Adapter<Tasks_adapter.Tasks_viewHolder> {

    List<Data_task>Tasks=new ArrayList<>();
    public TaskClickListener taskClickListener;

    public Tasks_adapter(TaskClickListener taskClickListener){
        this.taskClickListener=taskClickListener;
    }

    public void add(Data_task task){
        Tasks.add(0,task);
        notifyItemInserted(0);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void add_tasks(List<Data_task> tasks){
        this.Tasks.addAll(tasks);
        notifyDataSetChanged();
    }

    public void delete(Data_task task){
        for (int i = 0; i <Tasks.size() ; i++) {
            if(Tasks.get(i).getId()==task.getId()){
                Tasks.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }

    public void update(Data_task task){
        for (int i = 0; i<Tasks.size() ; i++) {
                if (Tasks.get(i).getId() == task.getId()) {
                    Tasks.set(i, task);
                    notifyItemChanged(i);
                }
        }
    }

    public void search(List<Data_task> task){
        Tasks=task;
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

        CheckBox isselected;
        ImageView btn_delete;

        public Tasks_viewHolder(@NonNull View itemView) {
            super(itemView);

            isselected=itemView.findViewById(R.id.checkbox);
            btn_delete=itemView.findViewById(R.id.btn_delete);

        }

        public void getTask(Data_task data_task){

            isselected.setText(data_task.getTask_title());
            isselected.setChecked(data_task.isIs_selected());

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                    builder.setTitle("Delete Task");
                    builder.setMessage(R.string.delete_task).setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    taskClickListener.removeTaskFromDialog(data_task);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    taskClickListener.editTask(data_task);
                    return false;
                }
            });

            isselected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if(isselected.isPressed()) {
                       data_task.setIs_selected(isChecked);
                       taskClickListener.is_complete(data_task);
                   }
                }
            });
        }
    }

    public interface TaskClickListener{
        void removeTaskFromDialog(Data_task data_task);
        void editTask(Data_task data_task);
        void is_complete(Data_task data_task);
    }
}
