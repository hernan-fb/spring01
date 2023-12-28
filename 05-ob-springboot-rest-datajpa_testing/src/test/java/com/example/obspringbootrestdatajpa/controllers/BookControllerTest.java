package com.example.obspringbootrestdatajpa.controllers;

import com.example.obspringbootrestdatajpa.entities.Book;
import com.example.obspringbootrestdatajpa.repositories.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class BookControllerTest {
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;
    private static BookRepository bookRepository;
    private
    /* otra manera equivalente
	@Value("${local.server.port}")
	private int port;
	*/
    @BeforeAll
    static void beforeAll(@Autowired ApplicationContext context) {
        Book book1 = new Book(null,"Homo deus", "Yuval Noah", 450, 29.99,LocalDate.of(2018,12,1),true);
        Book book2 = new Book(null,"Homo sapiens", "Yuval Noah", 450, 19.99, LocalDate.of(2013,12,1),true);
        //almacenar libro
        bookRepository = context.getBean(BookRepository.class);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("'Hello World' en controladores Spring Rest...")
    @Test
    void hello() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hola",String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello world... whats up!", response.getBody());
    }
    @Test
    void findAll() {
        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/books", Book[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Book> books_response = Arrays.asList(Objects.requireNonNull(response.getBody()));
        assertEquals(bookRepository.findAll(), books_response);
    }
    @Test
    void findANotExistingBook() {
        ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/book/9999",Book.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void findOneBook() {
        Long id = 1L;
        ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/book/"+id.toString(),Book.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookRepository.findById(id).orElseThrow(),response.getBody());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String json = """
                {
                    
                    "title": "Libro nuevo",
                    "author": "Autor Nuevo",
                    "pages": 550,
                    "price": 49.99,
                    "releaseDate": "2022-12-22",
                    "online": true
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Book book_created = response.getBody();
        Book book_to_verify = new Book(3L, "Libro nuevo", "Autor Nuevo", 550, 49.99, LocalDate.of(2022, 12, 22), true);
        assertEquals(book_to_verify, book_created);
    }
}