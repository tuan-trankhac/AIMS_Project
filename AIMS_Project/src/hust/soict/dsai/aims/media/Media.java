package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.disc.CompactDisc;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;

    public Media(){

    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public String toString() {
        return "Media: " +
                "id - " + this.getId() + " | " +
                "title - " + this.getTitle() + " | " +
                "category - " + this.getCategory() + " | " +
                "cost - " + this.getCost() + " | " +
                ".";
    }
    public boolean isMatch(String title) {
        return this.title.toLowerCase().contains(title.toLowerCase());
    }

    public boolean isMatch(int id) {
        return (this.id == id);
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Media) {
            try {
                String title = ((Media) o).getTitle();
                if (this.isMatch(title)) return true;
            } catch (NullPointerException | ClassCastException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<Media> media = new ArrayList<Media>();
        CompactDisc cd = new CompactDisc(10, "abc", 100, "STM", "POP", (float) 10.2, "Ariana");
        media.add(cd);
        DigitalVideoDisc dvd = new DigitalVideoDisc("Made You look");
        media.add(dvd);
        Book book = new Book();
        media.add(book);
        for (Media m : media){
            System.out.println(m.toString());
        }
    }
}
