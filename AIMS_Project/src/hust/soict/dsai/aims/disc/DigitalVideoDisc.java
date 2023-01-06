package hust.soict.dsai.aims.disc;

import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;

public class DigitalVideoDisc extends Disc implements Playable {
//    private String director;
//    private int length;
//    public void setId(int id) {
//        this.id = id;
//    }

    // class attribute
    private static int nbDigitalVideoDiscs = 0;

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    //    public static void setNbDigitalVideoDiscs(int nbDigitalVideoDiscs) {
//        hust.soict.dsai.aims.disc.DigitalVideoDisc.nbDigitalVideoDiscs = nbDigitalVideoDiscs;
//    }
    //
    public DigitalVideoDisc() {
    }

    public DigitalVideoDisc(String title) {
        ++nbDigitalVideoDiscs;
        setId((int) (Math.random() * 10000));
        setTitle(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        ++nbDigitalVideoDiscs;
        setId((int) (Math.random() * 10000));
        setTitle(title);
        setCategory(category);
        setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        ++nbDigitalVideoDiscs;
        setId((int) (Math.random() * 10000));
        setTitle(title);
        setCategory(category);
        setDirector(director);
        setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        ++nbDigitalVideoDiscs;
        setId((int) (Math.random() * 10000));
        setTitle(title);
        setCategory(category);
        setDirector(director);
        setLength(length);
        setCost(cost);
    }

    public String toString() {
        return "DigitalVideoDisc: " +
                this.getId() + " | " +
                "title - " + this.getTitle() + " | " +
                "category - " + this.getCategory() + " | " +
                "director - " + this.getDirector() + " | " +
                "length - " + this.getLength() + " | " +
                "cost - " + this.getCost() + "$.";

    }

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }

    }
}
