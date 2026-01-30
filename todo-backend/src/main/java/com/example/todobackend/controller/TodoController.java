package com.example.todobackend.controller;

import com.example.todobackend.model.Todo;
import com.example.todobackend.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;// PostMapping, GetMapping, PutMapping, DeleteMapping, PathVariable
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo)
    {
        Todo savedTodo = todoRepository.save(todo); // saves task

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 , static → returns BodyBuilder
                .body(savedTodo);  // instance → builds ResponseEntity<Todo>
    }

    // View all To-Do items
    @GetMapping
    public List<Todo> getAllTodos()
    {
        return todoRepository.findAll(); // returns a list of tasks
    }

    // View a single To-Do item
    @GetMapping("/{id}")
    public Todo getSingleTodo(@PathVariable Long id)
    {
        // 1. Ask the repository for a Todo with this id
        // Returns Optional<Todo> — might be empty if not found
        return todoRepository.findById(id)
                // 2️⃣. if Optional is empty, throw a proper HTTP 404 error, else return Todo
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, // HTTP status code (404)
                        "Todo not found"      // Message returned to the client
                ));
    }

    // Update a To-Do item
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo)
    {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Todo not found"
                ));

        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setDescription(updatedTodo.getDescription());
        existingTodo.setCompleted(updatedTodo.isCompleted());

        return todoRepository.save(existingTodo);
    }

    // Delete a To-Do item
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id)
    {
        Todo todoToDelete = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Todo not found"
                ));

        todoRepository.delete(todoToDelete); // delete Todo
    }
}


