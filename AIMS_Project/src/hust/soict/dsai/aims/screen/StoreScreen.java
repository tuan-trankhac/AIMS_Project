package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StoreScreen extends JFrame {
    private Cart cart;
    private Store store;
    protected static JFrame storeScreen;
    protected static JFrame cartScreen;
    protected static JFrame addBookScreen;
    protected static JFrame addCDScreen;
    protected static JFrame addDVDScreen;
    protected JFrame currentScreen;

    public void setStoreScreen(JFrame storeScreen) {
        this.storeScreen = storeScreen;
    }

    public void setCartScreen(JFrame cartScreen) {
        this.cartScreen = cartScreen;
    }

    public void setAddBookScreen(JFrame addBookScreen) {
        this.addBookScreen = addBookScreen;
    }

    public void setAddCDScreen(JFrame addCDScreen) {
        this.addCDScreen = addCDScreen;
    }

    public void setAddDVDScreen(JFrame addDVDScreen) {
        this.addDVDScreen = addDVDScreen;
    }

    public void setCurrentScreen(JFrame currentScreen) {
        this.currentScreen = currentScreen;
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(new menuListener());
        smUpdateStore.add(addBook);
        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(new menuListener());
        smUpdateStore.add(addCD);
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(new menuListener());
        smUpdateStore.add(addDVD);


        menu.add(smUpdateStore);
        JMenuItem viewStoreOpt = new JMenuItem("View Store");
        viewStoreOpt.addActionListener(new menuListener());
        menu.add(viewStoreOpt);
        JMenuItem viewCartOpt = new JMenuItem("View Cart");
        viewCartOpt.addActionListener(new menuListener());
        menu.add(viewCartOpt);


        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart = new JButton("View cart");
        cart.setPreferredSize(new Dimension(100, 50));
        cart.setMaximumSize(new Dimension(100, 50));

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));
        ObservableList<Media> mediaInStore = store.getItemsInStore();

        for (Media media : mediaInStore) {
            MediaStore cell = new MediaStore(media, this.cart);
            center.add(cell);
        }

        return center;
    }
    public StoreScreen(){

    }
    public StoreScreen(Store store, Cart cart) {
        super();
        setStoreScreen(this);
        setCurrentScreen(this);
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        store.getItemsInStore().addListener(
                (ListChangeListener<Media>) change -> {
                    BorderLayout layout = (BorderLayout) cp.getLayout();
                    cp.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                    cp.add(createCenter(), BorderLayout.CENTER);
                    cp.repaint();
                    cp.revalidate();
                }
        );
    }

    public class menuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String menu = e.getActionCommand();
            if (menu.equals("View Store") && !storeScreen.equals(currentScreen)) {
                storeScreen.setVisible(true);
                currentScreen.setVisible(false);
            } else if (menu.equals("View Cart") && !cartScreen.equals(currentScreen)) {
                cartScreen.setVisible(true);
                currentScreen.setVisible(false);
            } else if (menu.equals("Add Book") && !addBookScreen.equals(currentScreen)) {
                addBookScreen.setVisible(true);
                currentScreen.setVisible(false);
            } else if (menu.equals("Add CD") && !addCDScreen.equals(currentScreen)) {
                addCDScreen.setVisible(true);
                currentScreen.setVisible(false);
            } else if (menu.equals("Add DVD") && !addDVDScreen.equals(currentScreen)) {
                addDVDScreen.setVisible(true);
                currentScreen.setVisible(false);
            }
        }
    }
}
