package com.example.todoapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {


    @PrimaryKey
    private Long id;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "password")
    private String password;


    public User(){}



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
