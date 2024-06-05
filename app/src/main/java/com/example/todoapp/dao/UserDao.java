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
public interface UserDao {




//    @Query("SELECT * FROM task WHERE id IN (:userIds)")
//    List<Task> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE user_name = :username AND " +
            "password = :password LIMIT 1")
    Task findByUser(String username, String password);



}
