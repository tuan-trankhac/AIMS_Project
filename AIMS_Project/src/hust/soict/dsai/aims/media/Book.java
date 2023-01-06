package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
    private List<String> authors = new ArrayList<String>();
    private static int num = 0;
    private int id;
    public Book() {
        super();
    }
    public Book(String title, String category, float cost) {
        super(title, category, cost);
        num++;
        this.id = num;
    }

    public Book(String title, String category, float cost, String... authors) {
        super(title, category, cost);
        num++;
        this.id = num;
        for (String authorName : authors) {
            this.addAuthor(authorName);
        }
    }

    public void addAuthor(String authorName) {
        int i = authors.indexOf(authorName);
        if (i != -1) {
            System.out.println("This author is already existed!");
        } else {
            authors.add(authorName);
            System.out.println("Successfully add");
        }
    }

    public void removeAuthor(String authorName) {
        int i = authors.indexOf(authorName);
        if (i == -1) {
            System.out.println("This author is not exist!");
        }else {
            authors.remove(authorName);
            System.out.println("Successfully remove");
        }

    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("(Book): ").append(this.getTitle()).append(" - ").append(this.getCategory()).append(" - ");
        for (String author : authors) {
            str.append(author).append(", ");
        }
        return str.toString() + ": " + this.getCost() + "$";
    }

    public void play() {
        System.out.println("Book doesn't play");
    }

    public static void main(String[] args) {
        Book a = new Book();
        a.addAuthor("ABC");
        a.addAuthor("ABC");
        a.addAuthor("BCD");
        a.addAuthor("DCE");
        a.removeAuthor("CEF");
        a.removeAuthor("DCE");
    }
}
