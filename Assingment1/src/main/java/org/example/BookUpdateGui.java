package org.example;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class BookUpdateGui extends JFrame {

    JTextField id, name, author, publication, price, qty, y, m, d;

    public BookUpdateGui() {
        setTitle("Update Book");
        setSize(400, 400);
        setLayout(new GridLayout(9, 2));

        id = addField("Book ID");
        name = addField("Name");
        author = addField("Author");
        publication = addField("Publication");
        price = addField("Price");
        qty = addField("Quantity");
        y = addField("Year");
        m = addField("Month");
        d = addField("Day");

        JButton updateBtn = new JButton("Update");
        add(updateBtn);

        updateBtn.addActionListener(e -> updateBook());

        setVisible(true);
    }

    private JTextField addField(String label) {
        add(new JLabel(label));
        JTextField t = new JTextField();
        add(t);
        return t;
    }

    private void updateBook() {
        try {
            Book b = new Book(
                    Integer.parseInt(id.getText()),
                    name.getText(),
                    author.getText(),
                    publication.getText(),
                    LocalDate.of(
                            Integer.parseInt(y.getText()),
                            Integer.parseInt(m.getText()),
                            Integer.parseInt(d.getText())),
                    Float.parseFloat(price.getText()),
                    Integer.parseInt(qty.getText())
            );

            boolean updated = BookFileUtil.updateBook(b);
            JOptionPane.showMessageDialog(this,
                    updated ? "Updated Successfully" : "Book Not Found");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input");
        }
    }
}
