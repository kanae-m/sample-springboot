package com.example.samplespringboot.controllers;

import com.example.samplespringboot.models.Task;
import com.example.samplespringboot.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    private final TaskRepository taskRepository;

    public ApiController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping(value = "/todos")
    public List<Task> taskList() {
        return taskRepository.findAll();
    }

    @GetMapping(value = "/todo/{id}")
    public Optional<Task> retrieve(@PathVariable Long id) {
        return taskRepository.findById(id);
    }

    @PostMapping(value = "/todo")
    public ResponseEntity<Task> newTask(@RequestBody Task task) {
        Task result = taskRepository.save(task);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = "/todo/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping(value = "/todo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

}
