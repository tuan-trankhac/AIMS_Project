package hust.soict.dsai.aims.screen;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.Playable;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CartScreenController {
    private Cart cart;

    private Store store;
    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Label lbTotalCost;

    @FXML
    private MenuItem addBook;

    @FXML
    private MenuItem addCD;

    @FXML
    private MenuItem addDVD;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TextField tfFilter;

    @FXML
    private MenuItem viewStore;

    public CartScreenController(Cart cart, Store store) {
        super();
        this.cart = cart;
        this.store = store;
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        cart.removeMedia(media);
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        cart.emptyCart();
        lbTotalCost.setText(String.format("%.2f", cart.totalCost()) + "$");

        JFrame f = new JFrame();
        for (Frame fr : Frame.getFrames()) {
            if (fr.getTitle().equals("Cart")) {
                f = (JFrame) fr;
                break;
            }
        }
        JDialog d = new JDialog(f, "Successfully place order!", true);
        JButton b = new JButton("Cancel");
        d.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                d.setVisible(false);
            }
        });
        d.setSize(300, 100);
        d.add(new JLabel("Click to exit"));
        c.insets = new Insets(0, 10, 0, 0);
        d.add(b, c);
        f.setLocationRelativeTo(null);
        d.setLocationRelativeTo(f);
        d.setVisible(true);
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        JFrame f = new JFrame();
        for (Frame fr : Frame.getFrames()) {
            if (fr.getTitle().equals("Cart")) {
                f = (JFrame) fr;
                break;
            }
        }

        Media m = tblMedia.getSelectionModel().selectedItemProperty().get();

        try {
            ((Playable) m).play();

            JDialog d = new JDialog(f, m.getTitle() + " is now being played...", true);
            JButton b = new JButton("Cancel");
            d.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    d.setVisible(false);
                }
            });
            d.add(new JLabel("Click here to escape"));
            c.insets = new Insets(0, 10, 0, 0);
            d.add(b, c);
            d.setSize(300, 100);
            d.setLocationRelativeTo(f);
            d.setVisible(true);
        } catch (PlayerException ex) {
            JOptionPane.showMessageDialog(f, ex.getMessage(), "Illegal media length", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @FXML
    void addBookPressed(ActionEvent event) {
        Frame[] arr = Frame.getFrames();
        for (Frame f : arr) {
            f.setVisible(f.getTitle().equals("Add Book To Store"));
        }
    }

    @FXML
    void addCDPressed(ActionEvent event) {
        Frame[] arr = Frame.getFrames();
        for (Frame f : arr) {
            f.setVisible(f.getTitle().equals("Add CD To Store"));
        }
    }

    @FXML
    void addDVDPressed(ActionEvent event) {
        Frame[] arr = Frame.getFrames();
        for (Frame f : arr) {
            f.setVisible(f.getTitle().equals("Add DVD To Store"));
        }
    }

    @FXML
    void viewStorePressed(ActionEvent event) {
        Frame[] arr = Frame.getFrames();
        for (Frame f : arr) {
            f.setVisible(f.getTitle().equals("Store"));
        }
    }

    @FXML
    private void initialize() {

        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        tblMedia.setItems(this.cart.getItemOrdered());

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observableValue, Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        }
                    }
                }
        );
        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });
        tblMedia.setItems(this.cart.getItemOrdered());

        updateTotalCost();
        tblMedia.getItems().addListener(new ListChangeListener<Media>() {
            @Override
            public void onChanged(Change<? extends Media> change) {
                updateTotalCost();
            }
        });
    }

    private void showFilteredMedia(String value) {
        cart.filterCart(value, radioBtnFilterId.isSelected());
    }

    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        btnPlay.setVisible(media instanceof Playable);
    }

    void updateTotalCost() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lbTotalCost.setText(String.format("%.2f", cart.totalCost()) + "$");
            }
        });
    }

}

