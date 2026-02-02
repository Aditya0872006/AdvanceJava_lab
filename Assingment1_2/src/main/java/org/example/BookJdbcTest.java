package org.example;

import java.time.LocalDate;

public class BookJdbcTest {

    public static void main(String[] args) {

        Book book = new Book(
                1,
                "Advanced Java",
                "Herbert Schildt",
                "McGraw Hill",
                LocalDate.of(2023, 5, 12),
                650,
                2
        );

        // 👇 THIS LINE calls the method
        BookDAO.insertBook(book);
    }
}
