package com.example.todoapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.models.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getAll();

    @Insert
    void insertTask(Task task);

//    @Query("SELECT * FROM task WHERE id IN (:userIds)")
//    List<Task> loadAllByIds(int[] userIds);

//    @Query("SELECT * FROM task WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    Task findByName(String first, String last);


    @Delete
    void deleteTask(Task task);
    @Update
    void updateTask(Task task);
}
