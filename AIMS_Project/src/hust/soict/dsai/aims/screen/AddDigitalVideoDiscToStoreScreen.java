package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen{
    private JTextField lengthField;
    private JTextField directorField;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        setAddDVDScreen(this);
        setCurrentScreen(this);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(dvdInputField()), BorderLayout.CENTER);

        setTitle("Add DVD To Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    JPanel dvdInputField() {
        JPanel childP = new JPanel();
        childP.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

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

        JLabel enterDirector = new JLabel("Enter director:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15, 0, 0, 27);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        childP.add(enterDirector, c);
        directorField = new JTextField(16);
        c.insets = new Insets(15, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        childP.add(directorField, c);

        JLabel enterLength = new JLabel("Enter length:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15, 0, 0, 27);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        childP.add(enterLength, c);
        lengthField = new JTextField(16);
        c.insets = new Insets(15, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        childP.add(lengthField, c);

        JButton submitBtn = new JButton("Submit");
        c.insets = new Insets(15, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        childP.add(submitBtn, c);
        submitBtn.addActionListener(new submitAddDVD());

        return childP;
    }

    public class submitAddDVD implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String category = categoryField.getText();
            float cost = Float.parseFloat(costField.getText());
            int length = Integer.parseInt(lengthField.getText());
            String director = directorField.getText();

            store.addMedia(new DigitalVideoDisc(
                    title,
                    category,
                    director,
                    length,
                    cost
            ));

            this.clearFields();

            JDialog d = new JDialog(AddDigitalVideoDiscToStoreScreen.this, "Add DVD to store successful!", true);
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
            d.setLocationRelativeTo(AddDigitalVideoDiscToStoreScreen.this);
            d.setVisible(true);
        }

        public void clearFields() {
            titleField.setText("");
            categoryField.setText("");
            costField.setText("");
            directorField.setText("");
            lengthField.setText("");
        }
    }
}
