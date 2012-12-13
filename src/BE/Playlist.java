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

    /**
     * Constructor for the Playlist entity.
     *
     * @param id the ID that we get from the DB to create the entity.
     * @param name the name of our Playlist
     * @param createdOn the date when the Playlist was created.
     */
    public Playlist(int id, String name, long createdOn) {
        this.id = id;
        this.name = name;
        this.createdOn.setTime(createdOn);
    }

    /**
     * Constructor for the Playlist entity, where only just a name is given. It
     * is used when creating temporary playlists to play them in the
     * musicplayer.
     *
     * @param name the name of our Playlist entity.
     */
    public Playlist(String name) {
        this.name = name;
    }
/**
 * Getter for the ID.
 * @return ID
 */
    public int getId() {
        return id;
    }
/**
 * Getter for the Name.
 * @return name
 */
    public String getName() {
        return name;
    }
/**
 * Getter for the creation date.
 * @return createdOn
 */
    public Date getCreatedOn() {
        return createdOn;
    }
/**
 * Formats the Date into a format like it's stored in the DB.
 * @param createdOn the date that will be formatted
 * @return reportDate, the formatted date, which can be printed out.
 */
    public String formatDateToString(Date createdOn) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reportDate = df.format(createdOn);
        return reportDate;
    }

    /**
     * Prints out the Playlist entity in a nicer way.
     * @return a string, in the following format: ID. Name - Date
     */
    @Override
    public String toString() {
        return getId() + ". " + getName() + " - " + formatDateToString(getCreatedOn());
    }

    /**
     * Getter for the songs inside the playlist.
     * @return songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Setter for the temporary playlist. Adds a specific song to the playlist.
     * @param songs the song that will be added.
     */
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
