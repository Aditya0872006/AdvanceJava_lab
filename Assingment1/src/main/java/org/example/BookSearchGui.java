package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BookSearchGui extends JFrame {

    JTextField searchField;
    DefaultTableModel model;

    public BookSearchGui() {
        setTitle("Search Book");
        setSize(800, 300);
        setLayout(null);

        JLabel lbl = new JLabel("Search (ID / Name / Author / Publication):");
        lbl.setBounds(20, 20, 300, 25);
        add(lbl);

        searchField = new JTextField();
        searchField.setBounds(320, 20, 200, 25);
        add(searchField);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(540, 20, 100, 25);
        add(searchBtn);

        String[] cols = {"ID", "Name", "Author", "Publication", "Date", "Price", "Qty", "Total"};
        model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        add(new JScrollPane(table)).setBounds(20, 60, 740, 180);

        searchBtn.addActionListener(e -> search());

        setVisible(true);
    }

    private void search() {
        model.setRowCount(0);
        List<Book> books = BookFileUtil.searchBooks(searchField.getText());

        for (Book b : books) {
            model.addRow(new Object[]{
                    b.getBookId(), b.getBookName(), b.getAuthorNames(),
                    b.getPublication(), b.getDateOfPublication(),
                    b.getPriceOfBook(), b.getTotalQuantityToOrder(), b.getTotalCost()
            });
        }
    }
}
