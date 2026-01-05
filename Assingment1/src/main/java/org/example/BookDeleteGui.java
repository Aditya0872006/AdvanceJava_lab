
package org.example;

import javax.swing.*;

public class BookDeleteGui extends JFrame {

    public BookDeleteGui() {
        setTitle("Delete Book");
        setSize(300, 150);
        setLayout(null);

        JLabel lbl = new JLabel("Enter Book ID:");
        lbl.setBounds(30, 20, 100, 25);
        add(lbl);

        JTextField id = new JTextField();
        id.setBounds(140, 20, 100, 25);
        add(id);

        JButton delBtn = new JButton("Delete");
        delBtn.setBounds(90, 60, 100, 30);
        add(delBtn);

        delBtn.addActionListener(e -> {
            boolean deleted = BookFileUtil.deleteBook(Integer.parseInt(id.getText()));
            JOptionPane.showMessageDialog(this,
                    deleted ? "Deleted Successfully" : "Book Not Found");
        });

        setVisible(true);
    }
}
