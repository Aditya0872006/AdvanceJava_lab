package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    /* ---------- INSERT ---------- */
    public static void insertBook(Book book) {
        String sql = "INSERT INTO Book VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthorNames());
            ps.setString(4, book.getPublication());
            ps.setDate(5, Date.valueOf(book.getDateOfPublication()));
            ps.setFloat(6, book.getPriceOfBook());
            ps.setInt(7, book.getTotalQuantityToOrder());
            ps.setFloat(8, book.getTotalCost());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ---------- UPDATE ---------- */
    public static boolean updateBook(Book book) {
        String sql = """
            UPDATE Book SET
            bookName=?, authorNames=?, publication=?, dateOfPublication=?,
            priceOfBook=?, totalQuantityToOrder=?, totalCost=?
            WHERE bookId=?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthorNames());
            ps.setString(3, book.getPublication());
            ps.setDate(4, Date.valueOf(book.getDateOfPublication()));
            ps.setFloat(5, book.getPriceOfBook());
            ps.setInt(6, book.getTotalQuantityToOrder());
            ps.setFloat(7, book.getTotalCost());
            ps.setInt(8, book.getBookId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ---------- DELETE ---------- */
    public static boolean deleteBook(int id) {
        String sql = "DELETE FROM Book WHERE bookId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ---------- FETCH ALL ---------- */
    public static List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("bookId"),
                        rs.getString("bookName"),
                        rs.getString("authorNames"),
                        rs.getString("publication"),
                        rs.getDate("dateOfPublication").toLocalDate(),
                        rs.getFloat("priceOfBook"),
                        rs.getInt("totalQuantityToOrder")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ---------- SEARCH ---------- */
    public static List<Book> searchBooks(String key) {
        List<Book> list = new ArrayList<>();
        String sql = """
            SELECT * FROM Book
            WHERE bookName LIKE ? OR authorNames LIKE ? OR publication LIKE ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String k = "%" + key + "%";
            ps.setString(1, k);
            ps.setString(2, k);
            ps.setString(3, k);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("bookId"),
                        rs.getString("bookName"),
                        rs.getString("authorNames"),
                        rs.getString("publication"),
                        rs.getDate("dateOfPublication").toLocalDate(),
                        rs.getFloat("priceOfBook"),
                        rs.getInt("totalQuantityToOrder")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
