package BL;

import BE.Playlist;
import DAL.PlaylistDBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Manager class for the Category entity.
 *
 * @author Anthony
 */
public class PlaylistManager {

    private PlaylistDBManager DBM;

    /**
     * Constructor for the PlaylistManager class.
     *
     * @throws SQLException Exception, because it deals with the database
     * manager.
     * @throws IOException Exception, because the database manager uses a
     * configuration file to load it's settings.
     */
    public PlaylistManager() throws SQLException, IOException {
        DBM = new PlaylistDBManager();
    }

    /**
     * Gets all the Playlists.
     *
     * @return an ArrayList full of Playlists.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public ArrayList<Playlist> getAllPlaylists() throws SQLException {
        return DBM.getAllPlaylists();
    }

    /**
     * Gets a specific Playlist
     *
     * @param id the ID of the desired Playlist entity.
     * @return a single Playlist entity from the database.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public Playlist getPlaylistById(int id) throws SQLException {
        return DBM.getPlaylistByID(id);
    }

    /**
     * Search between the Playlists by a specified keyword.
     *
     * Makes an ArrayList for the results and sets them inside it if the
     * specified keyword is partially appears in the Playlists.
     *
     * @param search the keyword by which we search.
     * @return an ArrayList full of the results/of Playlist.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     * @throws IOException Exception
     */
    public ArrayList<Playlist> getPlaylist(String search) throws SQLException, IOException {
        SongManager sm = new SongManager();
        ArrayList<Playlist> playlists = DBM.getPlaylist(search);
        for (Playlist pl : playlists) {
            pl.setSongs(sm.getSongsFromPlaylist(pl.getId()));
        }
        return playlists;
    }

    /**
     * Add a Playlist entity to the database.
     *
     * @param pla a Playlist entity which we wish to add.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void addPlaylist(Playlist pla) throws SQLException {
        DBM.insertPlaylist(pla);
    }

    /**
     * Remove a specific Playlist from the database.
     *
     * @param id the ID of the selected playlist which we want to remove.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void removePlaylist(int id) throws SQLException {
        DBM.removePlaylistByID(id);
    }

    /**
     * Insert a song into a Playlist.
     *
     * By knowing the parameters, it inserts a specific song into a specific
     * playlist by creating a new entry in the PlaylistSong table.
     *
     * @param playlistId the ID of the playlist where we want to put our song.
     * @param songId the ID of the song which we want to move.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void insertSongToPlaylist(int playlistId, int songId) throws SQLException {
        DBM.insertSongToPlaylist(playlistId, songId);
    }

    /**
     * Remove a song from a specific playlist.
     *
     * @param playlistId the ID of the playlist we choose.
     * @param songId the ID of the song we wish to remove.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void removeSongFromPlaylist(int playlistId, int songId) throws SQLException {
        DBM.removeSongFromPlaylist(playlistId, songId);
    }

    /**
     * Move a song up or down a position in a selected playlist.
     *
     * @param playlistId the ID of the playlist where we want to reorder songs.
     * @param songId the ID of the song which we wish to remove.
     * @param newPos -1 for moving up on a playlist and +1 for moving down
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void reorderPlaylist(int playlistId, int songId, int newPos) throws SQLException {
        if (isItPossibleToMove(playlistId, songId, newPos)) {
            DBM.moveSong(playlistId, songId, newPos);
        }
    }

    /**
     * Checks whether or not it is possible to move a song in a playlist.
     *
     * It get a integer array from the Data Access Layer which contains the
     * current, maximum and minimum position of the desired song in a selected
     * playlist and checks whether or not it is possible to move it by checking
     * if the current position is the maximum or the minimum.
     *
     * @param playlistId the ID of the playlist where we want to reorder.
     * @param songId the ID of the song which we want to move.
     * @param newPos -1 if we want to move up or +1 if we want to move down our
     * song in the playlist.
     * @return True if it's possible to move the song; False if it's not.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public boolean isItPossibleToMove(int playlistId, int songId, int newPos) throws SQLException {
        int[] results = DBM.getSeqAndMaxSeq(playlistId, songId, newPos);
        int currentSeqNum = results[0];
        int maximumSeqNum = results[1];
        int minimumSeqNum = results[2];

        if (newPos == -1) {
            if (currentSeqNum != minimumSeqNum) {
                return true;
            } else {
                return false;
            }
        } else if (newPos == 1) {
            if (currentSeqNum != maximumSeqNum) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
