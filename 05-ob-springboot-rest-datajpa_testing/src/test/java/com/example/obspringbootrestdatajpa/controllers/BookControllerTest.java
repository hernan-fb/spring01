package com.example.obspringbootrestdatajpa.controllers;

import com.example.obspringbootrestdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class BookControllerTest {
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;
    /* otra manera equivalente
	@Value("${local.server.port}")
	private int port;
	*/
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
        List books = Arrays.asList(response.getBody());


    }
    @Test
    void findOne() {
        ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/book/1",Book.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
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
        Book book_to_verify = new Book(1L, "Libro nuevo", "Autor Nuevo", 550, 49.99, LocalDate.of(2022, 12, 22), true);
        assertEquals(book_to_verify, book_created);
    }
}