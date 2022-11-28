package hust.soict.dsai.aims.disc;

public class DigitalVideoDisc {
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;
    // instance attribute
    private int id;

    public int getId() {
        return id;
    }

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
    public String getTitle() {
        return title;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public DigitalVideoDisc(){
    }
    public DigitalVideoDisc(String title) {
        ++nbDigitalVideoDiscs;
        this.id = (int) (Math.random() * 10000);
        this.title = title;
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        ++nbDigitalVideoDiscs;
        this.id = (int) (Math.random() * 10000);
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        ++nbDigitalVideoDiscs;
        this.id = (int) (Math.random() * 10000);
        this.title = title;
        this.category = category;
        this.director = director;
        this.cost = cost;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        ++nbDigitalVideoDiscs;
        id = (int) (Math.random() * 10000);
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public void toStringCl() {
        System.out.println( "DVD - " + title + " - "+ category + " - "+ director  +" - "+ length +": " + cost +" $");
    }
}
