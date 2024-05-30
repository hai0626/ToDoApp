package com.example.todoapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todoapp.Database;
import com.example.todoapp.dao.TaskDao;
import com.example.todoapp.models.Task;

import java.util.List;

public class TasksRepository {

    private Database database;
    private TaskDao taskDao;
    private LiveData<List<Task>> taskList;


    public TasksRepository(Application application) {
        database = Database.getDatabase(application);
        taskDao = database.taskDao();
        taskList = taskDao.getAll();
    }

    public LiveData<List<Task>> getTaskList(){
        return database.taskDao().getAll();
    }

    public void insertTask(final Task task){
        new AsyncTask<Void, Void, Void>(

        ) {
            @Override
            protected Void doInBackground(Void... voids) {
                database.taskDao().insertTask(task);
                return null;
            }
        }.execute();
    }

    public void updateTask(final Task task){
        new AsyncTask<Void, Void, Void>(

        ) {
            @Override
            protected Void doInBackground(Void... voids) {
                if(task != null) database.taskDao().updateTask(task);
                return null;
            }
        }.execute();
    }

    public void deleteTask(final Task task){
        new AsyncTask<Void, Void, Void>(

        ) {
            @Override
            protected Void doInBackground(Void... voids) {
                if(task != null) database.taskDao().deleteTask(task);
                return null;
            }
        }.execute();
    }
}
