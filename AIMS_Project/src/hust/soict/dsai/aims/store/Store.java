package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Store {
    private final ObservableList<Media> itemsInStore = FXCollections.observableArrayList();

    //    private int qty;
    public Store() {

    }

    public void addMedia(Media dvd) {
        itemsInStore.add(dvd);
    }

    public void removeMedia(Media dvd) {
        itemsInStore.remove(dvd);
    }

    public void addMedia(Media... mediaList) {
        for (Media m : mediaList) {
            this.addMedia(m);
        }
        System.out.println("Add new items successful!");
    }

    public void printStore() {
        System.out.println("Items in store:");
        for (Media m : itemsInStore) {
            System.out.println(m.toString());
        }
    }

    public Media searchStore(String title) {
        for (Media media : itemsInStore) {
            if (Objects.equals(media.getTitle(), title))
                return media;
        }
        return null;
    }

    public ObservableList<Media> getItemsInStore() {
        return this.itemsInStore;
    }
}
