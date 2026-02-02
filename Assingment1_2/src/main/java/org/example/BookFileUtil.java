
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

    public static List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();
        List<Book> books = readBooks();

        for (Book b : books) {
            if (String.valueOf(b.getBookId()).equals(keyword) ||
                    b.getBookName().equalsIgnoreCase(keyword) ||
                    b.getAuthorNames().equalsIgnoreCase(keyword) ||
                    b.getPublication().equalsIgnoreCase(keyword)) {

                result.add(b);
            }
        }
        return result;
    }
    public static boolean updateBook(Book updatedBook) {
        List<Book> books = readBooks();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == updatedBook.getBookId()) {
                books.set(i, updatedBook);
                saveAll(books);
                return true;
            }
        }
        return false;
    }
    public static boolean deleteBook(int bookId) {
        List<Book> books = readBooks();

        boolean removed = books.removeIf(b -> b.getBookId() == bookId);

        if (removed) {
            saveAll(books);
        }
        return removed;
    }

    private static void saveAll(List<Book> books) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
