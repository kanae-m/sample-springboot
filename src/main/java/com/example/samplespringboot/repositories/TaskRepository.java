package com.example.samplespringboot.repositories;

import com.example.samplespringboot.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
