package com.example.todolist.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.todolist.Models.Data_task;

@Database(version = 1,exportSchema = true,entities = {Data_task.class})
public abstract class Database_holder extends RoomDatabase {

    private static Database_holder database_holder;

    public Database_holder getDatabase(Context context){
        if(database_holder==null){
            database_holder= Room.databaseBuilder(context.getApplicationContext(),Database_holder.class,"task_db").
                    allowMainThreadQueries()
                    .build();
        }
        return database_holder;
    }

    public abstract DataDao getDataDao();

    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }
}
