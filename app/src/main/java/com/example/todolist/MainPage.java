package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.todolist.Adapter.Tasks_adapter;
import com.example.todolist.Database.DataDao;
import com.example.todolist.Database.Database_holder;
import com.example.todolist.Models.Data_task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainPage extends AppCompatActivity implements DialogAdd.ActionOverDatabase, DialogEdit.EditActionOverDatabase,Tasks_adapter.TaskClickListener{

    RecyclerView recyclerView;
    FloatingActionButton btn_add;
    EditText editText_search;
    ImageView btn_search;
    Tasks_adapter tasks_adapter;
    DataDao dataDao;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_main);

        btn_add=findViewById(R.id.btn_add);
        recyclerView=findViewById(R.id.recyclerview);
        editText_search=findViewById(R.id.edit_search);
        btn_search=findViewById(R.id.btn_search);


        dataDao=Database_holder.getDatabase(this).getDataDao();


        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        tasks_adapter=new Tasks_adapter(this);
        recyclerView.setAdapter(tasks_adapter);


        List<Data_task> tasks=dataDao.getTaskList();
        tasks_adapter.add_tasks(tasks);



        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        if(s.length()>0){
                            List<Data_task> tasks=dataDao.searchTask(s.toString());
                            tasks_adapter.search(tasks);
                        }else {
                            List<Data_task> tasks = dataDao.getTaskList();
                            tasks_adapter.search(tasks);
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

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

    @Override
    public void removeTaskFromDialog(Data_task data_task) {
        int result=dataDao.deleteTask(data_task);
        if(result>0){
            tasks_adapter.delete(data_task);
        }
    }

    @Override
    public void editTask(Data_task data_task) {
        DialogEdit dialogEdit=new DialogEdit();
        Bundle bundle=new Bundle();
        bundle.putParcelable("data",data_task);
        dialogEdit.setArguments(bundle);
        dialogEdit.show(getSupportFragmentManager(),null);
    }

    @Override
    public void is_complete(Data_task data_task) {
        dataDao.updateTask(data_task);
    }

    @Override
    public void editTaskFromDialog(Data_task data_task) {
        int result = dataDao.updateTask(data_task);
        if (result > 0) {
            tasks_adapter.update(data_task);
        }
    }
}