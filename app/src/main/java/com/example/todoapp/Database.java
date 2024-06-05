package com.example.todoapp;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todoapp.dao.TaskDao;
import com.example.todoapp.dao.UserDao;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.User;

@androidx.room.Database(entities = {Task.class, User.class}, version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract TaskDao taskDao();
    public abstract UserDao userDao();
    public static volatile Database INSTANCE;

    public static Database getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class,"mydb").build();
                }
            }
        }
        return INSTANCE;
    }
}
