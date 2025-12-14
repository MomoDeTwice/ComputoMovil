package com.fic.cursoandroid2025g4.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    void insertHistory(History history);

    @Query("SELECT * FROM history ORDER BY created_At DESC")
    List<History> getAllHistory();

    @Query("SELECT * FROM history WHERE accion = :action ORDER BY created_At DESC")
    List<History> getHistoryByAction(String action);

    @Query("SELECT * FROM history where created_At like :date || '%'  ORDER BY created_At DESC")
    List<History> getHistoryByDate(String date);

    @Query("SELECT * FROM history WHERE accion = :action AND created_At like :date || '%' ORDER BY created_At DESC")
    List<History> getHistoryByActionAndDate(String action, String date);
}
