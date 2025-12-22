package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookFileUtil {

    private static final String FILE_NAME = "Books.dat";

    public static void saveBook(Book book) {
        List<Book> books = readBooks();
        books.add(book);

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(books);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> readBooks() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (List<Book>) ois.readObject();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
