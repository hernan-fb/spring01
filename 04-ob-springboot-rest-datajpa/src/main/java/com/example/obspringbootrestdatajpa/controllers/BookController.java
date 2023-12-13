package com.example.obspringbootrestdatajpa.controllers;
import com.example.obspringbootrestdatajpa.entities.Book;
import com.example.obspringbootrestdatajpa.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private BookRepository repository;
    private final Logger log = LoggerFactory.getLogger(BookController.class);
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
        //functional style:
        //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Crear un nuevo libro en base de datos
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        if (book.getId() != null) {
            log.warn("Trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book bookCreated = repository.save(book);
        return ResponseEntity.ok(bookCreated);
    }
    // Actualizar libro existente en base de datos

    @PutMapping("/api/book")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        if (book.getId() == null) {
            log.warn("Trying to update a book without id");
            return ResponseEntity.badRequest().build();
        }
        if (!repository.existsById(book.getId())) {
            log.warn("Trying to update a non-existing book");
            return ResponseEntity.badRequest().build();
        }
        Book bookCreated = repository.save(book);
        return ResponseEntity.ok(bookCreated);
    }
    // Borrar un libro en base de datos
    @DeleteMapping("/api/book/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            log.warn("Trying to delete a non-existing book");
            return ResponseEntity.badRequest().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {
        // if (repository.count() > 0) {
        log.info("REST Request for deleting ALL books");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
        // }

    }
}
