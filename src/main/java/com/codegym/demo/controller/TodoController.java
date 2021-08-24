package com.codegym.demo.controller;

import com.codegym.demo.model.Todo;
import com.codegym.demo.service.todo.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
@CrossOrigin("*")
public class TodoController {
    @Autowired
    private ITodoService todoService;

    @GetMapping
    public ResponseEntity<Iterable<Todo>> findAll() {
        List<Todo> todoList = (List<Todo>) todoService.findAll();
        if (todoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Todo todo) {
        todoService.save(todo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getOne(@PathVariable Long id) {
        Optional<Todo> todo = todoService.findById(id);
        if (!todo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Todo todo) {
        Optional<Todo> todoOptional = todoService.findById(id);
        if (!todoOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todo.setId(id);
        todoService.save(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Todo> todo = todoService.findById(id);
        if (!todo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
