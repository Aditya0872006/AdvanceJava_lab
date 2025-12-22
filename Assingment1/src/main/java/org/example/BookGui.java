package org.example;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class BookGui extends JFrame {

    JTextField id, name, author, publication, price, qty, year, month, day;

    public BookGui() {

        setTitle("Book Entry Form");
        setSize(500, 450);
        setLayout(new GridLayout(10, 2, 10, 10));
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

        JButton saveBtn = new JButton("Save Book");
        JButton viewBtn = new JButton("View All Books");

        add(saveBtn);
        add(viewBtn);

        saveBtn.addActionListener(e -> saveBook());
        viewBtn.addActionListener(e -> new BookListGui());

        setVisible(true);
    }

    private JTextField addField(String label) {
        add(new JLabel(label));
        JTextField field = new JTextField();
        add(field);
        return field;
    }

    private void saveBook() {
        try {
            Book book = new Book(
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

            BookFileUtil.saveBook(book);
            JOptionPane.showMessageDialog(this, "Book Saved Successfully");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookGui::new);
    }
}
