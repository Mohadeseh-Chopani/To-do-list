package com.example.todolist.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="task_db")
public class Data_task implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public boolean isIs_selected() {
        return is_selected;
    }

    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
    }

    private String task_title;
    private boolean is_selected;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.task_title);
        dest.writeByte(this.is_selected ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.task_title = source.readString();
        this.is_selected = source.readByte() != 0;
    }

    public Data_task() {
    }

    protected Data_task(Parcel in) {
        this.id = in.readInt();
        this.task_title = in.readString();
        this.is_selected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Data_task> CREATOR = new Parcelable.Creator<Data_task>() {
        @Override
        public Data_task createFromParcel(Parcel source) {
            return new Data_task(source);
        }

        @Override
        public Data_task[] newArray(int size) {
            return new Data_task[size];
        }
    };
}
