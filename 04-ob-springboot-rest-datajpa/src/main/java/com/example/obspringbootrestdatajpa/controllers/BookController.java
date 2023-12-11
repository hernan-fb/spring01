package com.example.obspringbootrestdatajpa.controllers;
import com.example.obspringbootrestdatajpa.entities.Book;
import com.example.obspringbootrestdatajpa.repositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }
    //CRUD sobre la entidad book

    // Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAll() {
        return repository.findAll();
    }
    // Buscar según su id

    // Crear un nuevo libro en base de datos

    // Actualizar libro existente en base de datos

    // Borrar un libro en base de datos
}
