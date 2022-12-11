package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;

import java.util.ArrayList;
import java.util.Objects;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();
//    private int qty;

    public void addMedia(Media dvd) {
       itemsInStore.add(dvd);
    }

    public void removeMedia(Media dvd) {
       itemsInStore.remove(dvd);
    }


    public void printStore(){
        for(Media media : itemsInStore){
            System.out.println(media);
        }
    }

    public Media searchStore(String title){
        for (Media media : itemsInStore){
            if (Objects.equals(media.getTitle(), title))
                return media;
        }
        return null;
    }
}
