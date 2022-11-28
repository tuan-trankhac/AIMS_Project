package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Store {
    private DigitalVideoDisc itemsInStore[] = new DigitalVideoDisc[1000];
    private int qty;

    public void addDVD(DigitalVideoDisc dvd) {
        itemsInStore[qty] = dvd;
        ++qty;
    }

    public void removeDVD(DigitalVideoDisc dvd) {
        for (int i = 0; i < qty; ++i) {
            if (dvd == itemsInStore[i]) {
                if (qty - (i + 1) >= 0) System.arraycopy(itemsInStore, i + 1, itemsInStore, i + 1 - 1, qty - (i + 1));
                --qty;
                return;
            }
        }
    }
}
