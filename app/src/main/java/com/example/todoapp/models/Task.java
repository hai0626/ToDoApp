package com.example.todoapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {


    @PrimaryKey
    private Long id;
    @ColumnInfo(name = "name_task")
    private String nameTask;
    @ColumnInfo(name = "description_task")
    private String descriptionTask;
    @ColumnInfo(name = "mark_task")
    private String isMarkTask;

    public Task(){}

    public Task(String nameTask, String descriptionTask, String isMark) {
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.isMarkTask = isMark;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public String getIsMarkTask() {
        return isMarkTask;
    }

    public void setIsMarkTask(String mark) {
        isMarkTask = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
