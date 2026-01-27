package com.example.todobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository; // for JpaRepository
import org.springframework.stereotype.Repository;          // optional, good practice
import com.example.todobackend.model.Todo;             // your Todo entity

@Repository // Spring can automatically detect interfaces that extend JpaRepository, good but optional
public interface TodoRepository extends JpaRepository<Todo, Long> {}

//Todo, Long == Entity class, type of the primary key
//With this interface we get full CRUD access to the database
//save(), findById(), findAll(), deleteById(), count()