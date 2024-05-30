package com.example.todoapp.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todoapp.dao.TaskDao;
import com.example.todoapp.models.Task;
import com.example.todoapp.repository.TasksRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends ViewModel {


    private TasksRepository tasksRepository;
    private LiveData<List<Task>> tasksList;

    public void init(Application application) {

        tasksRepository = new TasksRepository(application);
        tasksList = tasksRepository.getTaskList();
    }

    public LiveData<List<Task>> getAllTasks(){
        return tasksRepository.getTaskList();
    }

    public void insertTask(Task task){
        tasksRepository.insertTask(task);
    }

    public void deleteTask(Task task){
        tasksRepository.deleteTask(task);
    }
    public void updateTask(Task task){
        tasksRepository.updateTask(task);
    }


}
