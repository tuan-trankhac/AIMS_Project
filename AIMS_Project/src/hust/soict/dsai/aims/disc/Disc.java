package hust.soict.dsai.aims.disc;

import hust.soict.dsai.aims.media.Media;

public class Disc extends Media {
    private int length;
    private String director;

    public void setLength(int length) {
        this.length = length;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }

    public Disc() {
    }

    public Disc(int length, String director, int id, String title, String category, float cost) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }

    public Disc(String director) {
        this.director = director;
    }
    public Disc(int length, String director) {
        this.length = length;
        this.director = director;
    }
    public Disc(String title, String category, float cost, String director) {
        super(title, category, cost);
        this.director = director;
    }
}
