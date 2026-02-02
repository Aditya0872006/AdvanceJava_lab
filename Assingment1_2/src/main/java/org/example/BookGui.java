package org.example;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class BookGui extends JFrame {

    JTextField id, name, author, publication, price, qty, year, month, day;

    public BookGui() {

        setTitle("Book Management System");
        setSize(550, 500);
        setLayout(new GridLayout(12, 2, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        id = addField("Book ID");
        name = addField("Book Name");
        author = addField("Author Names");
        publication = addField("Publication");
        price = addField("Price");
        qty = addField("Quantity");
        year = addField("Year");
        month = addField("Month");
        day = addField("Day");

        JButton saveBtn   = new JButton("Save Book");
        JButton viewBtn   = new JButton("View All Books");
        JButton searchBtn = new JButton("Search Book");
        JButton updateBtn = new JButton("Update Book");
        JButton deleteBtn = new JButton("Delete Book");

        add(saveBtn);
        add(viewBtn);
        add(searchBtn);
        add(updateBtn);
        add(deleteBtn);

        saveBtn.addActionListener(e -> saveBook());
        viewBtn.addActionListener(e -> new BookListGui());
        searchBtn.addActionListener(e -> new BookSearchGui());
        updateBtn.addActionListener(e -> updateBook());
        deleteBtn.addActionListener(e -> deleteBook());

        setVisible(true);
    }

    private JTextField addField(String label) {
        add(new JLabel(label));
        JTextField field = new JTextField();
        add(field);
        return field;
    }

    /* ---------------- SAVE ---------------- */
    private void saveBook() {
        try {
            BookDAO.insertBook(createBookFromInput());
            JOptionPane.showMessageDialog(this, "Book Saved to Database");
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Saving Book");
        }
    }



    /* ---------------- UPDATE ---------------- */
    private void updateBook() {
        try {
            boolean ok = BookDAO.updateBook(createBookFromInput());
            JOptionPane.showMessageDialog(this,
                    ok ? "Book Updated" : "Book Not Found");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Failed");
        }
    }


    /* ---------------- DELETE ---------------- */
    private void deleteBook() {
        try {
            int idVal = Integer.parseInt(id.getText());
            boolean ok = BookDAO.deleteBook(idVal);
            JOptionPane.showMessageDialog(this,
                    ok ? "Book Deleted" : "Book Not Found");
            if (ok) clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid ID");
        }
    }


    /* ---------------- HELPER METHODS ---------------- */
    private Book createBookFromInput() {

        return new Book(
                Integer.parseInt(id.getText()),
                name.getText(),
                author.getText(),
                publication.getText(),
                LocalDate.of(
                        Integer.parseInt(year.getText()),
                        Integer.parseInt(month.getText()),
                        Integer.parseInt(day.getText())
                ),
                Float.parseFloat(price.getText()),
                Integer.parseInt(qty.getText())
        );
    }

    private void clearFields() {
        id.setText("");
        name.setText("");
        author.setText("");
        publication.setText("");
        price.setText("");
        qty.setText("");
        year.setText("");
        month.setText("");
        day.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookGui::new);
    }
}