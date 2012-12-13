package BL;

import BE.Song;
import DAL.SongDBManager;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Manager class for the Category entity.
 *
 * @author Anthony
 */
public class SongManager {

    private SongDBManager DBM;

    /**
     * Constructor for the PlaylistManager class.
     *
     * @throws SQLException Exception, because it deals with the database
     * manager.
     * @throws IOException Exception, because the database manager uses a
     * configuration file to load it's settings.
     */
    public SongManager() throws SQLException, IOException {
        DBM = new SongDBManager();
    }

    /**
     * Gets all the Songs.
     *
     * @return an ArrayList full of Songs.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public ArrayList<Song> getAllSongs() throws SQLException {
        return DBM.getAllSongs();
    }

    /**
     * Gets a specific Song entity.
     *
     * @param id the ID of the entity which we want to select.
     * @return a single Song entity.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public Song getSongById(int id) throws SQLException {
        return DBM.getSongByID(id);
    }

    /**
     * Adds a new Song entity to the database.
     *
     * @param son the Song entity which we wish to add.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void addSong(Song son) throws SQLException {
        DBM.insertSong(son);
    }

    /**
     * Removes a selected Song.
     *
     * @param id the ID of the song we wish to remove.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void removeSong(int id) throws SQLException {
        DBM.removeSongByID(id);
    }

    /**
     * Updates a selected Song entity.
     *
     * @param song the modified Song entity which will replace the old Song.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void updateSong(Song song) throws SQLException {
        DBM.updateSong(song);
    }

    /**
     * Search between all the Songs.
     *
     * @param term the keyword by which we search.
     * @return an ArrayList full of songs that match fully or partially our
     * search.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public ArrayList<Song> searchSongs(String term) throws SQLException {
        return DBM.searchSongs(term);
    }

    /**
     * Gets a song from a selected playlist.
     *
     * @param playlistId the ID of the playlist which we want to show
     * @return an ArrayList full of Songs from that specific Playlist.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public ArrayList<Song> getSongsFromPlaylist(int playlistId) throws SQLException {
        return DBM.getSongsFromPlaylist(playlistId);
    }

    /**
     * Checks all the Songs if they exist locally or not.
     *
     * Gets all the songs from the database, stores it inside a variable, then
     * it uses all the Song entities to create File instances, which will be
     * checked by their filename if they exist or not. After that it creates a
     * new empty ArrayList of booleans out of the values that the File.exist()
     * returns.
     *
     * @return checkedList - an ArrayList full of booleans which represent
     * whether or not a file exists.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public ArrayList<Boolean> checkAllSongs() throws SQLException {
        ArrayList<Boolean> checkedList = new ArrayList<>();
        ArrayList<Song> allSongs = DBM.getAllSongs();
        for (Song son : allSongs) {
            File song = new File(DBM.PATH + son.getFileName());
            checkedList.add(song.exists());
        }
        return checkedList;
    }

    /**
     * Sets the instance variable of the songs according of the check results.
     *
     * @return an ArrayList full of Song where the isExists field of each song
     * is set True or False.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public ArrayList<Song> checkedSongs() throws SQLException {
        ArrayList<Song> songs = getAllSongs();
        ArrayList<Boolean> checks = checkAllSongs();

        for (int i = 0; i < songs.size(); i++) {
            songs.get(i).setIsExists(checks.get(i));
        }
        return songs;
    }
}