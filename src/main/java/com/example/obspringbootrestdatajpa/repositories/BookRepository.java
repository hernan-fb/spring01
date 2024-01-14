package com.example.obspringbootrestdatajpa.repositories;

import com.example.obspringbootrestdatajpa.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
