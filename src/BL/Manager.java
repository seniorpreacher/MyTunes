package BL;

import BE.*;
import DAL.DBManager;
import java.util.ArrayList;

/**
 * Manager
 *
 * This class manages the access between the UI and the Database manager. This
 * is the logic of the whole application.
 *
 * @author Anthony
 */
public class Manager {

    private DBManager DBM = new DBManager();

    public Song getSongById(int id) {
        return DBM.getSongByID(id);
    }

    public Artist getArtistById(int id) {
        return DBM.getArtistByID(id);
    }

    public Category getCategoryById(int id) {
        return DBM.getCategoryByID(id);
    }

    public Playlist getPlaylistById(int id) {
        return DBM.getPlaylistByID(id);
    }

    public ArrayList<Song> getAllSongs() {
        return DBM.getAllSongs();
    }

    public ArrayList<Artist> getAllArtists() {
        return DBM.getAllArtists();
    }

    public ArrayList<Category> getAllCategories() {
        return DBM.getAllCategories();
    }

    public ArrayList<Playlist> getAllPlaylists() {
        return DBM.getAllPlaylists();
    }
}
