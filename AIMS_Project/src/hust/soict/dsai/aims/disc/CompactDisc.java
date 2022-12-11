package hust.soict.dsai.aims.disc;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<Track>();

    public String getArtist() {
        return artist;
    }

    public CompactDisc(int length, String director, int id, String title, String category, float cost, String artist) {
        super(length, director, id, title, category, cost);
        this.artist = artist;
    }

    public CompactDisc() {

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
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
        System.out.println("------------------------");
        for (Track e: tracks) {
            e.play();
        }
        System.out.println("------------------------");
    }
}
