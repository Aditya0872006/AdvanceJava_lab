package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookListGui extends JFrame {

    public BookListGui() {
        setTitle("All Books");
        setSize(800, 300);

        String[] cols = {"ID","Name","Author","Publication","Price","Qty"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        for (Book b : BookDAO.getAllBooks()) {
            model.addRow(new Object[]{
                    b.getBookId(),
                    b.getBookName(),
                    b.getAuthorNames(),
                    b.getPublication(),
                    b.getPriceOfBook(),
                    b.getTotalQuantityToOrder()
            });
        }

        add(new JScrollPane(new JTable(model)));
        setVisible(true);
    }
}
