package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
    private List<String> authors = new ArrayList<String>();

    public Book() {

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
