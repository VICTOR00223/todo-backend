package com.example.todobackend.controller;

import com.example.todobackend.model.Todo;
import com.example.todobackend.repository.TodoRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;// PostMapping, GetMapping, PutMapping, DeleteMapping, PathVariable
import java.util.List;


@RestController
@RequestMapping("/todos")
public class TodoController
{
    private final TodoRepository todoRepository; //This line does NOT create an object.
                                                // That field exists so the controller has a place to store the TodoRepository instance that Spring injects.

    public TodoController(TodoRepository todoRepository)//so Spring can give your controller the repository it needs
    {
        this.todoRepository = todoRepository;
    }

    // Create a To-Do item
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo)
    {
        return todoRepository.save(todo); // saves task
    }

    // View all To-Do items
    @GetMapping
    public List<Todo> getAllTodos()
    {
        return todoRepository.findAll(); // returns a list of tasks
    }

    // View a single To-Do item
    @GetMapping("/{id}")
    public Todo getsingleTodo()
    {
        
    }

    // Update a To-Do item
    @PutMapping("/{id}")
    public void updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo)
    {
    }

    // Delete a To-Do item
    @DeleteMapping("/{id}")
    public void deleteTodo() {
    }
}


