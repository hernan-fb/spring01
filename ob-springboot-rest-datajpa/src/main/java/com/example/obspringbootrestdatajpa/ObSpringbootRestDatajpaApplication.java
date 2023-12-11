package com.example.obspringbootrestdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObSpringbootRestDatajpaApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ObSpringbootRestDatajpaApplication.class, args);
        BookRepository repository = context.getBean(BookRepository.class);
        //CRUD

        //Crear libro
        Book book1 = new Book(null,"Homo deus", "Yuval Noah", 450, 29.99,LocalDate.of(2018,12,1),true);
        Book book2 = new Book(null,"Homo sapiens", "Yuval Noah", 450, 19.99, LocalDate.of(2013,12,1),true);
        //almacenar libro
        repository.save(book1);
        repository.save(book2);

        //recuperar todos los libros
        System.out.println("numero de libros: "+repository.findAll().size());
        //borrar un libro

        repository.deleteById(1L);

        System.out.println("numero de libros: "+repository.count());
    }

}
