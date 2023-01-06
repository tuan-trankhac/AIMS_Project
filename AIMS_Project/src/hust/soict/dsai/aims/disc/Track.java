package hust.soict.dsai.aims.disc;

import hust.soict.dsai.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public  Track(){

    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing Track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: Track's length is not positive!");
        }

    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Track a){
            return this.title.equals(a.title) && this.length == a.length;
        }
        return false;
    }
}
