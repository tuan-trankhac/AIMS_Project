package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField authorField;

    public AddBookToStoreScreen(Store store) {
        super(store);
        setAddBookScreen(this);
        setCurrentScreen(this);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(bookInputField()), BorderLayout.CENTER);

        setTitle("Add Book To Store");
        setSize(1024, 768);
        setVisible(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    JPanel bookInputField() {
        JPanel childP = new JPanel();
        childP.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        // Enter title row
        JLabel enterTitle = new JLabel("Enter title:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 25);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        childP.add(enterTitle, c);
        titleField = new JTextField(16);
        c.insets = new Insets(0, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        childP.add(titleField, c);

        // Enter category row
        JLabel enterCategory = new JLabel("Enter category:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15, 0, 0, 25);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        childP.add(enterCategory, c);
        categoryField = new JTextField(16);
        c.insets = new Insets(15, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        childP.add(categoryField, c);


        JLabel enterCost = new JLabel("Enter cost:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15, 0, 0, 25);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        childP.add(enterCost, c);
        costField = new JTextField(16);
        c.insets = new Insets(15, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        childP.add(costField, c);

        JLabel enterAuthor = new JLabel("Enter author:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15, 0, 0, 36);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        childP.add(enterAuthor, c);
        authorField = new JTextField(16);
        c.insets = new Insets(15, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        childP.add(authorField, c);

        JButton submitBtn = new JButton("Submit");
        c.insets = new Insets(15, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        childP.add(submitBtn, c);
        submitBtn.addActionListener(new submitAddBook());

        return childP;
    }

    public class submitAddBook implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String category = categoryField.getText();
            float cost = Float.parseFloat(costField.getText());
            String[] authors = authorField.getText().split(",");

            store.addMedia(new Book(
                    title,
                    category,
                    cost,
                    authors
            ));

            this.clearFields();

            JDialog d = new JDialog(AddBookToStoreScreen.this, "Add Book to store successful!", true);
            JButton b = new JButton("Cancel");
            d.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d.setVisible(false);
                }
            });
            d.add(new JLabel("Click here to escape"));
            c.insets = new Insets(0, 10, 0, 0);
            d.add(b, c);
            d.setSize(300, 100);
            d.setLocationRelativeTo(AddBookToStoreScreen.this);
            d.setVisible(true);
        }

        public void clearFields() {
            titleField.setText("");
            categoryField.setText("");
            authorField.setText("");
            costField.setText("");
        }
    }
}
