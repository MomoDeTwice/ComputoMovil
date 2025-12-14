package com.fic.cursoandroid2025g4.controller;

import android.content.Context;
import android.util.Log;

import com.fic.cursoandroid2025g4.model.Task;
import com.fic.cursoandroid2025g4.model.TaskDao;
import com.fic.cursoandroid2025g4.model.TaskDatabase;


import java.util.List;

public class TaskController {

    private final TaskDao taskDao;
    private final HistoryController historyController;

    public TaskController(Context context) {
        TaskDatabase database = TaskDatabase.getInstance(context);
        taskDao = database.taskDao();
        historyController = new HistoryController(context);
    }

    //Create a book
    public boolean addTask(String taskTittle, String taskDescription, String createdAt ,String isCompleted){
        try{
            Task task = new Task();
            task.taskTittle = taskTittle;
            task.taskDescription = taskDescription;
            task.createdAt = createdAt;
            task.isCompleted = isCompleted;
            taskDao.insert(task);
            Log.i("TASK_SAVE","La tarea se almacenó correctamente");
            
            // Registrar en historial
            historyController.insertarAccion("insert_task", "Tarea creada: " + taskTittle);
            
            return true;

        }catch (Exception e){
            Log.e("TASK_ERROR",e.getMessage());
            return false;
        }

    }

    //Get all tasks
    public List<Task> getAllTasks(){
        return taskDao.getAllTasks();
    }

    //Update book
    public void updateTask(Task task){
        taskDao.update(task);
        // Registrar en historial
        historyController.insertarAccion("update_task", "Tarea actualizada: " + task.taskTittle);
    }

    //Delete book
    public void deleteTask(Task task){
        taskDao.delete(task);
        // Registrar en historial
        historyController.insertarAccion("delete_task", "Tarea eliminada: " + task.taskTittle);
    }
}
