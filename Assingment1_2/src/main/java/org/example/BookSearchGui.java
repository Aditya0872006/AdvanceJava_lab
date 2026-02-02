package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookSearchGui extends JFrame {

    JTextField search = new JTextField();
    DefaultTableModel model;

    public BookSearchGui() {
        setTitle("Search Book");
        setSize(700, 300);

        model = new DefaultTableModel(
                new String[]{"ID","Name","Author","Publication","Price"}, 0);

        JButton btn = new JButton("Search");
        btn.addActionListener(e -> search());

        add(search, "North");
        add(new JScrollPane(new JTable(model)), "Center");
        add(btn, "South");

        setVisible(true);
    }

    private void search() {
        model.setRowCount(0);
        for (Book b : BookDAO.searchBooks(search.getText())) {
            model.addRow(new Object[]{
                    b.getBookId(),
                    b.getBookName(),
                    b.getAuthorNames(),
                    b.getPublication(),
                    b.getPriceOfBook()
            });
        }
    }
}
