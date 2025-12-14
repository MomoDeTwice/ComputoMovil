package com.fic.cursoandroid2025g4.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "history")
public class History {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "history_id" )
    public int historyId;

    @ColumnInfo(name = "accion")
    public String action;

    @ColumnInfo(name = "created_At")
    public String createdAt;

    @ColumnInfo(name = "details")
    public String details;
}
