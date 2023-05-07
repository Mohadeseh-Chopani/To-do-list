package com.example.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todolist.Models.Data_task;

import java.util.Objects;


public class DialogAdd extends DialogFragment {

    EditText input_task;
    TextView add,cancel;
    ActionOverDatabase actionOverDatabase;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        actionOverDatabase=(ActionOverDatabase) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_add, null, false);
        builder.setView(view);

        input_task=view.findViewById(R.id.input_task);
        add=view.findViewById(R.id.action_add);
        cancel=view.findViewById(R.id.action_cancel);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_task.length()>0){
                    Data_task data=new Data_task();
                    data.setTask_title(input_task.getText().toString());
                    actionOverDatabase.addTaskFromDialog(data);
                    dismiss();
                }
                else {
                    AlertDialog.Builder error_empty=new AlertDialog.Builder(getContext());
                    error_empty.setMessage(R.string.error_empty).setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dismiss();
                                }
                            });
                    AlertDialog dialog= error_empty.create();
                    dialog.show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return  builder.create();
    }
    interface ActionOverDatabase{
        void addTaskFromDialog(Data_task data_task);
    }
}