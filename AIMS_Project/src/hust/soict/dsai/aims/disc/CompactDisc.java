package hust.soict.dsai.aims.disc;

import hust.soict.dsai.aims.exception.PlayerException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private static int num = 0;
    private List<Track> tracks = new ArrayList<Track>();

    public String getArtist() {
        return artist;
    }

    public CompactDisc(int length, String director, int id, String title, String category, float cost, String artist) {
        super(length, director, id, title, category, cost);
        this.setId(++num);
        this.artist = artist;
    }
    public CompactDisc() {
        this.setId(++num);
    }

    public CompactDisc(String title, String director, String artist) {
        super(director);
        this.setId(++num);
        this.setTitle(title);
        this.artist = artist;
    }

    public CompactDisc(String title, int length, String director, String artist) {
        super(length, director);
        this.setTitle(title);
        this.setId(++num);
        this.artist = artist;
    }

    public CompactDisc(String title, String category, float cost, String director, String artist) {
        super(title, category, cost, director);
        this.artist = artist;
    }


    public void addTrack(Track track) {
        int i = tracks.indexOf(track);
        if (i == -1) {
            tracks.add(track);
            System.out.println("Successfully add!");
        } else {
            System.out.println("This track is already existed");
        }
    }

    public void removeTrack(Track track) {
        int i = tracks.indexOf(track);
        if (i == -1) {
            System.out.println("This track is not exist");
        } else {
            tracks.remove(track);
            System.out.println("Successfully remove!");
        }
    }

    public int getLength() {
        int sum = 0;
        for (Track e : tracks) {
            sum += e.getLength();
        }
        return sum;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            Iterator<Track> iter = tracks.iterator();
            Track nextTrack;
            while (iter.hasNext()) {
                nextTrack = iter.next();
                nextTrack.play();
            }
        } else {
            throw new PlayerException("ERROR: CD's length is non-positive!");
        }
        for (Track t : this.tracks) {
            try {
                t.play();
            } catch (PlayerException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String toString() {
        return "Compact disc: " +
                this.getId() + " | " +
                "artist - " + this.getArtist() + " | " +
                "length - " + this.getLength() + " | " +
                "director - " + this.getDirector() + " | " +
                "title - " + this.getTitle() + " | " +
                "category - " + this.getCategory() + " | " +
                "cost - " + this.getCost() +
                ".";
    }
}
