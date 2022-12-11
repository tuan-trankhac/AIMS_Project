package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.disc.CompactDisc;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.MediaComparatorByCostTitle;
import hust.soict.dsai.aims.media.MediaComparatorByTitleCost;

import java.util.*;

public class Cart{
    public static final int MAX_NUMBERS_ORDERED = 20;
    //    private DigitalVideoDisc[] itemsOrdered = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

//    private int qtyOrdered;

//    public int getQtyOrdered() {
//        return qtyOrdered;
//    }

//    public void setQtyOrdered(int qtyOrdered) {
//        this.qtyOrdered = qtyOrdered;
//    }

    public void addMedia(Media disc) {
        int i = itemsOrdered.indexOf(disc);
        if (i != -1) {
            System.out.println("This media is already added!");
        } else {
            if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
                itemsOrdered.add(disc);
                System.out.println("Successfully add!");
            } else {
                System.out.println("This cart is full!");
            }
        }
    }

//    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
//        for (DigitalVideoDisc i : dvdList) {
//            this.addDigitalVideoDisc(i);
//        }
//    }

    public void searchByID(int ID) {
        int check = 0;
        for (Media i : itemsOrdered) {
            if (ID == i.getId()) {
                check++;
                System.out.println(i.toString());
            }
        }
        if (check == 0) System.out.println("DVD not found");
    }

    public void searchByTitle(String title) {
        int check = 0;
        for (Media i : itemsOrdered) {
            if (Objects.equals(i.getTitle(), title)) {
                System.out.println(i.toString());
                check++;
            }
        }
        if (check == 0) System.out.println("DVD not found");
    }

    //        public void addDigitalVideoDisc(hust.soict.dsai.aims.disc.DigitalVideoDisc ... dvd){
//            for (hust.soict.dsai.aims.disc.DigitalVideoDisc i : dvd) {
//                addDigitalVideoDisc(i);
//            }
//    }
    public void addMedia(Media dvd1, Media dvd2) {
        this.addMedia(dvd1);
        this.addMedia(dvd2);
    }

    public void removeMedia(Media disc) {
        int i = itemsOrdered.indexOf(disc);
        if (i == -1) {
            System.out.println("The disc not found!");
        } else {
            itemsOrdered.remove(disc);
            System.out.println("Successfully remove!");
        }
    }

    public float totalCost() {
        float sum = 0;
        for (Media i : itemsOrdered) {
            sum += i.getCost();
        }
        return sum;
    }

    public void printCart() {
        for (Media i : itemsOrdered) {
            System.out.println(i.toString());
        }
        System.out.println("Total cost: " + totalCost());
    }
    public void sortByTitleCost() {
        this.itemsOrdered.sort(COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        this.itemsOrdered.sort(COMPARE_BY_COST_TITLE);
    }

    public int getNumberOfItemsOrder(){
        return this.itemsOrdered.size();
    }

    public Media getMedia(String title){
        for (Media media : this.itemsOrdered){
            if (Objects.equals(media.getTitle(), title)){
                return media;
            }
        }
        return null;
    }

    public Media getMedia(int id){
        for (Media media : this.itemsOrdered){
            if (media.getId()==id){
                return media;
            }
        }
        return null;
    }

    public void emptyCart(){
        this.itemsOrdered.clear();
    }
}
