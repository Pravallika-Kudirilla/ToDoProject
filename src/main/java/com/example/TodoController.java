package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
public class TodoController {

    private final List<Todo> todos = new ArrayList<>();

    private int nextId = 1;

    // GET - Get all tasks
    @GetMapping
    public List<Todo> getTodos() {
        return todos;
    }

    // POST - Add a task
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {

        todo.setId(nextId++);
        todo.setCompleted(false);

        todos.add(todo);

        return todo;
    }

    // PUT - Update a task
    @PutMapping("/{id}")
    public Todo updateTodo(
            @PathVariable int id,
            @RequestBody Todo updatedTodo) {

        for (Todo todo : todos) {

            if (todo.getId() == id) {

                todo.setTask(updatedTodo.getTask());
                todo.setCompleted(updatedTodo.isCompleted());

                return todo;
            }
        }

        return null;
    }

    // DELETE - Delete a task
    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable int id) {

        boolean removed = todos.removeIf(todo -> todo.getId() == id);

        if (removed) {
            return "Task deleted successfully";
        }

        return "Task not found";
    }

    // PUT - Mark task as completed
    @PutMapping("/{id}/complete")
    public Todo completeTodo(@PathVariable int id) {

        for (Todo todo : todos) {

            if (todo.getId() == id) {

                todo.completeTask();

                return todo;
            }
        }

        return null;
    }
}