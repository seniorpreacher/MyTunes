package BE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * This is a playlist entity of the corresponding table from our database. We
 * use it to construct elements of that type.
 *
 * @author Anthony
 */
public class Playlist {

    private int id;
    private String name;
    private Date createdOn = new Date();
    private ArrayList<Song> songs;

    public Playlist(int id, String name, long createdOn) {
        this.id = id;
        this.name = name;
        this.createdOn.setTime(createdOn);
    }

    public Playlist(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public String formatDateToString(Date createdOn) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reportDate = df.format(createdOn);
        return reportDate;
    }

    @Override
    public String toString() {
        return getId() + ". " + getName() + " - " + formatDateToString(getCreatedOn());
    }

    /**
     * @return the songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * @param songs the songs to set
     */
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
    
}
