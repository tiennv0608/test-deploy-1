package com.codegym.demo.service.todo;

import com.codegym.demo.model.Todo;
import com.codegym.demo.repository.ITodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService implements ITodoService {

    @Autowired
    private ITodoRepository todoRepository;

    @Override
    public Iterable<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void remove(Long id) {
        todoRepository.deleteById(id);
    }
}
