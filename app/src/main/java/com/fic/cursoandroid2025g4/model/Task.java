package com.fic.cursoandroid2025g4.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "taskTittle")
    public String taskTittle;

    @ColumnInfo(name = "taskDescription")
    public String taskDescription;

    @ColumnInfo(name = "createdAt")
    public String createdAt;

    @ColumnInfo(name = "isCompleted")
    public String isCompleted;

}
