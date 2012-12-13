package BL;

import BE.Song;
import DAL.SongDBManager;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class SongManager {

    private SongDBManager DBM;

    public SongManager() throws SQLException, IOException {
        DBM = new SongDBManager();
    }

    public ArrayList<Song> getAllSongs() throws SQLException {
        return DBM.getAllSongs();
    }

    public Song getSongById(int id) throws SQLException {
        return DBM.getSongByID(id);
    }

    public void addSong(Song son) throws SQLException {
        DBM.insertSong(son);
    }

    public void removeSong(int id) throws SQLException {
        DBM.removeSongByID(id);
    }

    public void updateSong(Song song) throws SQLException {
        DBM.updateSong(song);
    }

    public ArrayList<Song> searchSongs(String term) throws SQLException {
        return DBM.searchSongs(term);
    }

    public ArrayList<Song> getSongsFromPlaylist(int playlistId) throws SQLException {
        return DBM.getSongsFromPlaylist(playlistId);
    }

    /**
     * Gets all the songs from the database, stores it inside a variable, then
     * it uses all the Song entities to create File instances, which will be
     * checked by their filename if they exist or not. After that it creates a
     * new empty ArrayList of booleans out of the values that the File.exist()
     * returns.
     *
     * @return checkedList - an ArrayList full of booleans which represent
     * whether or not a file exists.
     * @throws SQLException
     */
    public ArrayList<Boolean> checkAllSongs() throws SQLException {
        ArrayList<Boolean> checkedList = new ArrayList<>();
        ArrayList<Song> allSongs = DBM.getAllSongs();
        for (Song son : allSongs) {
            File song = new File("songs/" + son.getFileName());
            checkedList.add(song.exists());
        }
        return checkedList;
    }
    
    public ArrayList<Song> checkedSongs() throws SQLException {
        ArrayList<Song> songs = getAllSongs();
        ArrayList<Boolean> checks = checkAllSongs();
        
        for (int i = 0; i < songs.size(); i++) {
            songs.get(i).setIsExists(checks.get(i));
        }
        return songs;
    }
}