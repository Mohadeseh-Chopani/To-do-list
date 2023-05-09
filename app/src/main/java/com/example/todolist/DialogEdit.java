package com.example.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.todolist.Models.Data_task;

public class DialogEdit extends DialogFragment {

    EditText input_task;
    TextView add,cancel;
    EditActionOverDatabase editActionOverDatabase;

   Data_task data;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editActionOverDatabase=(EditActionOverDatabase) context;

        if(getArguments() != null){
            data=getArguments().getParcelable("data");
        }

        if(data==null)
            dismiss();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog, null, false);
        builder.setView(view);

        input_task=view.findViewById(R.id.input_task);
        add=view.findViewById(R.id.action_add);
        cancel=view.findViewById(R.id.action_cancel);

        add.setText(R.string.text_edit);

        input_task.setText(data.getTask_title());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_task.length()>0){
                    data.setTask_title(input_task.getText().toString());
                    editActionOverDatabase.editTaskFromDialog(data);
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

    interface EditActionOverDatabase{
        void editTaskFromDialog(Data_task data_task);

    }
}
