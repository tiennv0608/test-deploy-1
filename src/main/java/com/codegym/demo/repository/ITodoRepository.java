package com.codegym.demo.repository;

import com.codegym.demo.model.Todo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITodoRepository extends PagingAndSortingRepository<Todo, Long> {
}
