package com.example.todoapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todoapp.Database;
import com.example.todoapp.dao.TaskDao;
import com.example.todoapp.dao.UserDao;
import com.example.todoapp.models.Task;

import java.util.List;

public class UsersRepository {

    private Database database;
    private UserDao userDao;


    public UsersRepository(Application application) {
        database = Database.getDatabase(application);
        userDao = database.userDao();
    }

    public LiveData<List<Task>> getTaskList() {
        return database.taskDao().getAll();
    }

    public void checkLogin(final String username, final String password) {
        new AsyncTask<Void, Void, Void>(

        ) {
            @Override
            protected Void doInBackground(Void... voids) {
                database.userDao().findByUser(username, password);
                return null;
            }
        }.execute();
    }

}
