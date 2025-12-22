package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BookListGui extends JFrame {

    public BookListGui() {

        setTitle("All Books");
        setSize(800, 300);

        String[] cols = {
                "ID", "Name", "Authors", "Publication",
                "Date", "Price", "Qty", "Total Cost"
        };

        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        List<Book> books = BookFileUtil.readBooks();

        for (Book b : books) {
            model.addRow(new Object[]{
                    b.getBookId(),
                    b.getBookName(),
                    b.getAuthorNames(),
                    b.getPublication(),
                    b.getDateOfPublication(),
                    b.getPriceOfBook(),
                    b.getTotalQuantityToOrder(),
                    b.getTotalCost()
            });
        }

        add(new JScrollPane(table));
        setVisible(true);
    }
}
