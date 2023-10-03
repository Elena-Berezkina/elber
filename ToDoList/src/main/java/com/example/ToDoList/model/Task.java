package com.example.ToDoList.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
int id;
LocalDateTime creationTime;
boolean isDone;
String title;
String description;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", creationTime=" + creationTime +
                ", isDone=" + isDone +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
