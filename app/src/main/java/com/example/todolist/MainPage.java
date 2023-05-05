package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.todolist.Adapter.Tasks_adapter;

public class MainPage extends AppCompatActivity {

    RecyclerView recyclerView;
    Tasks_adapter tasks_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_main);

        recyclerView=findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        tasks_adapter=new Tasks_adapter();
        recyclerView.setAdapter(tasks_adapter);
    }
}