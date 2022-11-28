package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;

import java.util.Objects;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private DigitalVideoDisc[] itemsOrdered = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered;

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(int qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered <= MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered++;
            System.out.println("The disc has been added!");
        } else {
            System.out.println("The cart is almost full!");
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
        for (DigitalVideoDisc i : dvdList) {
            this.addDigitalVideoDisc(i);
        }
    }

    public void searchByID(int ID) {
        int check = 0;
       for(int i = 0; i < qtyOrdered; i++){
           if(ID == itemsOrdered[i].getId()){
               check++;
               itemsOrdered[i].toStringCl();
           }
       }
        if (check == 0) System.out.println("DVD not found");
    }

    public void searchByTitle(String title) {
        int check = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            if (Objects.equals(itemsOrdered[i].getTitle(), title)) {
                itemsOrdered[i].toStringCl();
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
    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        this.addDigitalVideoDisc(dvd1);
        this.addDigitalVideoDisc(dvd2);
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].equals(disc)) {
                System.out.println("Remove succeed!");
                if (qtyOrdered - 1 - i >= 0) System.arraycopy(itemsOrdered, i + 1, itemsOrdered, i, qtyOrdered - 1 - i);
                qtyOrdered--;
                return;
            }
        }
        System.out.println("The disc not found!");
    }

    public float totalCost() {
        float sum = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            sum += itemsOrdered[i].getCost();
        }
        return sum;
    }

    public void print() {
        for (int i = 0; i < qtyOrdered; i++) {
            itemsOrdered[i].toStringCl();
        }
        System.out.println("Total cost: " + totalCost());
    }
}
