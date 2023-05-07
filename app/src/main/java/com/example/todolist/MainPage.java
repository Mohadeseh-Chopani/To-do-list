package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.todolist.Adapter.Tasks_adapter;
import com.example.todolist.Database.DataDao;
import com.example.todolist.Database.Database_holder;
import com.example.todolist.Models.Data_task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainPage extends AppCompatActivity implements ActionOverDatabase{

    RecyclerView recyclerView;
    FloatingActionButton btn_add;
    Tasks_adapter tasks_adapter;
    DataDao dataDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_main);

        btn_add=findViewById(R.id.btn_add);
        recyclerView=findViewById(R.id.recyclerview);



        dataDao=Database_holder.getDatabase(this).getDataDao();



        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        tasks_adapter=new Tasks_adapter();
        recyclerView.setAdapter(tasks_adapter);


        List<Data_task> tasks=dataDao.getTaskList();
        tasks_adapter.add_tasks(tasks);


        DialogAdd dialogAdd=new DialogAdd();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd.show(getSupportFragmentManager(),null);
            }
        });
    }

    @Override
    public void addTaskFromDialog(Data_task data_task) {
        long result = dataDao.addTask(data_task);
        if (result != 0) {
            data_task.setId((int) result);
            tasks_adapter.add(data_task);
        }
    }
}