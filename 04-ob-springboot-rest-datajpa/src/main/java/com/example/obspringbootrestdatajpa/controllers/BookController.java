package com.example.obspringbootrestdatajpa.controllers;
import com.example.obspringbootrestdatajpa.entities.Book;
import com.example.obspringbootrestdatajpa.repositories.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }
    //CRUD sobre la entidad book

    // Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAll(@RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        return repository.findAll();
    }
    // Buscar según su id
    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> findOne(@PathVariable Long id) {
        Optional<Book> bookOpt = repository.findById(id);
        if (bookOpt.isPresent()) {
            return ResponseEntity.ok(bookOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Crear un nuevo libro en base de datos
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }
    // Actualizar libro existente en base de datos

    // Borrar un libro en base de datos
}
