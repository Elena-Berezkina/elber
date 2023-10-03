package com.example.ToDoList.services;

import com.example.ToDoList.model.Task;
import com.example.ToDoList.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

TaskRepository tR;
Task newTask;


    public TaskService(TaskRepository tR) {
        this.tR = tR;
    }

    public Task createTask(String title, String description) {
       newTask = new Task();
        newTask.setCreationTime(LocalDateTime.now());
        newTask.setDone(false);
        newTask.setTitle(title);
        newTask.setDescription(description);
        return newTask;
    }

    public List<String> getAllTasks() {
        List <Task> tasks = tR.findAll();
        List<String> tasksToString = new ArrayList<>();
        for(Task t : tasks) {
            tasksToString.add(t.toString());
        }
        return tasksToString;

    }

    public Task updateTaskStatus(Task task, boolean newStatus) {
        if (task.isDone() != newStatus) {
            task.setDone(newStatus);
            tR.save(task);
        }
        return task;
    }

    public Task updateTaskTitle(Task task, String newTitle) {
        if (!task.getTitle().equals(newTitle)) {
            task.setTitle(newTitle);
            tR.save(task);
        }
        return task;
    }

    public Task updateTaskDescription(Task task, String newDesc) {
        if (!task.getDescription().equals(newDesc)) {
            task.setDescription(newDesc);
            tR.save(task);
        }
        return task;
    }








}
