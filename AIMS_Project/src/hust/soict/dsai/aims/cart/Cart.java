package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.MediaComparatorByCostTitle;
import hust.soict.dsai.aims.media.MediaComparatorByTitleCost;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javax.naming.LimitExceededException;
import java.util.*;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    //    private DigitalVideoDisc[] itemsOrdered = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
    FilteredList<Media> filteredList = new FilteredList<>(itemsOrdered, m -> true);
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public void addMedia(Media disc) throws LimitExceededException {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            System.out.println("This cart is full!");
            throw new LimitExceededException("ERROR: The number of media has reached its limit!");
        } else {
            if (itemsOrdered.contains((disc))) {
                System.out.println("This media is already added!");
                throw new LimitExceededException("ERROR: This media is already added!");
            } else {
                itemsOrdered.add(disc);
                System.out.println("Successfully add!");
            }
        }
    }

    public void addMedia(Media[] mediaList) throws LimitExceededException {
        if (itemsOrdered.size() + mediaList.length >= MAX_NUMBERS_ORDERED) {
            System.out.println("Not enough space to add!");
            throw new LimitExceededException("ERROR: The number of media has reached its limit!");
        } else {
            for (Media m : mediaList) {
                try {
                    this.addMedia(m);
                } catch (LimitExceededException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Successfully add!");
        }
    }

    public void addMedia(Media dvd1, Media dvd2) throws LimitExceededException {
        if (itemsOrdered.size() + 2 >= MAX_NUMBERS_ORDERED) {
            System.out.println("Not enough space left to add 2 items!");
            throw new LimitExceededException("ERROR: the number of media has reached its limit!");
        } else {
            try {
                this.addMedia(dvd1);
            } catch (LimitExceededException e) {
                throw new RuntimeException(e);
            }
            try {
                this.addMedia(dvd2);
            } catch (LimitExceededException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Add items successful!");
        }
    }

    public void searchCart(int ID) {
        if (ID <= 0) {
            System.out.println("Invalid ID!");
        } else {
            List<Media> result = new ArrayList<Media>();

            for (Media media : this.filteredList) {
                if (media.isMatch(ID)) {
                    result.add(media);
                }
            }
            if (result.size() > 0) {
                System.out.println("Results:");
                for (Media media : result) {
                    System.out.println(media.toString());
                }
            } else {
                System.out.println("DVD not found!");
            }
        }
    }

    public void searchCart(String title) {
        List<Media> result = new ArrayList<Media>();

        for (Media m : this.filteredList) {
            if (m.isMatch(title)) {
                result.add(m);
            }
        }
        if (result.size() > 0) {
            System.out.println("Result:");
            for (Media m : result) {
                System.out.println(m.toString());
            }
        } else {
            System.out.println("DVD not found!");
        }
    }


    public void removeMedia(Media disc) {
        if (this.itemsOrdered.size() == 0) {
            System.out.println("The cart is empty!");
        } else {
            if (this.itemsOrdered.contains(disc)) {
                if (this.itemsOrdered.remove(disc)) {
                    System.out.println("Successfully remove!");
                } else {
                    System.out.println("Remove failed!");
                }
            } else {
                System.out.println("This item not found!");
            }
        }
    }

    public float totalCost() {
        float sum = 0.0f;
        for (Media i : itemsOrdered) {
            sum += i.getCost();
        }
        return sum;
    }

    public void printCart() {
        if (this.filteredList.size() != 0) {
            for (Media m : filteredList) {
                System.out.println(m.toString());
            }
        } else System.out.println("This cart is empty!");
        System.out.println("Total cost: " + this.totalCost());
    }

    public void sortByTitleCost() {
        this.itemsOrdered.sort(COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        this.itemsOrdered.sort(COMPARE_BY_COST_TITLE);
    }

    public int getNumberOfItemsOrder() {
        return this.itemsOrdered.size();
    }

    public Media getMedia(String title) {
        for (Media media : this.itemsOrdered) {
            if (Objects.equals(media.getTitle(), title)) {
                return media;
            }
        }
        return null;
    }

    public Media getMedia(int id) {
        for (Media media : this.itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public void emptyCart() {
        this.itemsOrdered.clear();
    }

    public ObservableList<Media> getItemOrdered() {
        return filteredList;
    }

    public void filterCart(String title, boolean type) {
        if (title == null || title.length() == 0) {
            filteredList.setPredicate(m -> true);
        } else {
            if (type) {
                try {
                    int idValue = Integer.parseInt(title);
                    filteredList.setPredicate(m -> m.getId() == idValue);
                } catch (NumberFormatException e) {
                    System.out.println("The id value is invalid!");
                }
            } else {
                filteredList.setPredicate(m -> m.getTitle().contains(title));
            }
        }
    }

    public int getNumberOfOrderedItems() {
        return this.filteredList.size();
    }

    public Media getOneMedia(String title) {
        for (Media m : this.filteredList) {
            if (m.isMatch(title)) {
                return m;
            }
        }
        return null;
    }

    public Media getOneMedia(int id) {
        for (Media m : this.filteredList) {
            if (m.isMatch(id)) {
                return m;
            }
        }
        return null;
    }
}
