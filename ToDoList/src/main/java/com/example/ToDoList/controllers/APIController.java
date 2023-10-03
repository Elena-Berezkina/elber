package com.example.ToDoList.controllers;

import com.example.ToDoList.model.Task;
import com.example.ToDoList.repository.TaskRepository;
import com.example.ToDoList.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class APIController {

    TaskRepository tR;
    TaskService tS;

    public APIController(TaskRepository tR, TaskService tS) {
        this.tR = tR;
        this.tS = tS;
    }
    @GetMapping("/time")
    public LocalDateTime printDateTime() {
        return LocalDateTime.now();
    }

    @PostMapping("/")
    public ResponseEntity <Integer> createTask(@RequestParam String title, @RequestParam String desc) {
        Task task = tS.createTask(title, desc);
        Task taskToSave = tR.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional task = tR.findById(id);
        if(task == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @GetMapping("/")
    public List<String> getAllTasks() {
        return tS.getAllTasks();
    }

    @PatchMapping("/tasks/{id}/{isDone}/{title}/{description}")
    public ResponseEntity updateTask(@PathVariable int id, @PathVariable boolean newIsDone, @PathVariable String newTitle, @PathVariable String newDescription) {
      Optional task = tR.findById(id);
      if(task == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); }
      Task taskToUpdate = (Task) task.get();
      if(taskToUpdate.isDone() != newIsDone) { tS.updateTaskStatus(taskToUpdate, newIsDone); }
      if(!taskToUpdate.getTitle().equals(newTitle) && newTitle != null) { tS.updateTaskTitle(taskToUpdate, newTitle); }
      if(!taskToUpdate.getDescription().equals(newDescription) && newDescription != null) {tS.updateTaskDescription(taskToUpdate, newDescription); }
      tR.save(taskToUpdate);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        if(!tR.existsById(id)) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); }
        tR.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);

    }






}
