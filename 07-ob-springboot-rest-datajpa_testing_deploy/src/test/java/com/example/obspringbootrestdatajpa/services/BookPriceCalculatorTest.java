package com.example.obspringbootrestdatajpa.services;

import com.example.obspringbootrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {
@Test
    void calculatePriceTest() {
    Book book = new Book(1L, "El Señor de los anillos","JRTolkien", 1000, 49.99, LocalDate.now(), true);
    BookPriceCalculator calculator = new BookPriceCalculator();

    double price = calculator.calculatePrice(book);

    System.out.println(price);
    assertTrue(price > 0);
    assertEquals(57.98, price, 0.001);
}
}