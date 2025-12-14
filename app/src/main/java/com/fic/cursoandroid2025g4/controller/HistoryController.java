package com.fic.cursoandroid2025g4.controller;

import android.content.Context;
import android.util.Log;

import com.fic.cursoandroid2025g4.model.History;
import com.fic.cursoandroid2025g4.model.HistoryDao;
import com.fic.cursoandroid2025g4.model.TaskDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryController {

    private final HistoryDao historyDao;

    public HistoryController(Context context){
        TaskDatabase taskDatabase = TaskDatabase.getInstance(context);
        historyDao = taskDatabase.historyDao();
    }


    //insertar una accion en el historial
    public boolean insertarAccion(String accion, String details){
        if (accion == null || accion.isEmpty()){
            return false;
        }

        try{
            History history = new History();
            history.action = accion;
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            history.createdAt = fecha.format(new Date());
            history.details = details != null ? details : "";
            historyDao.insertHistory(history);
            Log.i("HISTORY_SAVE","La history se ha creado correctamente");
            return true;
        } catch (Exception e){
            Log.e("HISTORY_ERROR", "Error saving history: " + e.getMessage());
            return false;
        }
    }

    //obtener todo el historial
    public List<History> getAllHistory(){
        return historyDao.getAllHistory();
    }


    //obtener el tipo de accion mediante un filtro
    public List<History> getHistoryByAction(String accion){
        return historyDao.getHistoryByAction(accion);
    }

    //obtener el historial por fecha
    public List<History> getHistoryByDate(String date){
        return historyDao.getHistoryByDate(date);
    }

    //obtener historial por accion y fecha
    public List<History> getHistoryByActionAndDate(String action, String date){
        return historyDao.getHistoryByActionAndDate(action, date);
    }

}
