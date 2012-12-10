package BL;

import BE.Playlist;
import DAL.PlaylistDBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class PlaylistManager {

    private PlaylistDBManager DBM;

    public PlaylistManager() throws SQLException, IOException {
        DBM = new PlaylistDBManager();
    }

    public ArrayList<Playlist> getAllPlaylists() throws SQLException {
        return DBM.getAllPlaylists();
    }

    public Playlist getPlaylistById(int id) throws SQLException {
        return DBM.getPlaylistByID(id);
    }

    public void addPlaylist(Playlist pla) throws SQLException {
        DBM.insertPlaylist(pla);
    }

    public void removePlaylist(int id) throws SQLException {
        DBM.removePlaylistByID(id);
    }

    public void insertSongToPlaylist(int playlistId, int songId) throws SQLException {
        DBM.insertSongToPlaylist(playlistId, songId);
    }

    public void removeSongFromPlaylist(int playlistId, int songId) throws SQLException {
        DBM.removeSongFromPlaylist(playlistId, songId);
    }

    /**
     * 
     * @param playlistId
     * @param songId
     * @param newPos -1 for moving up on a playlist and +1 for moving down
     * @throws SQLException 
     */
    public void reorderPlaylist(int playlistId, int songId, int newPos) throws SQLException {
        if (isItPossibleToMove(playlistId, songId, newPos)) {
            DBM.moveSong(playlistId, songId, newPos);
        }
    }

    /**
     * 
     * @param playlistId
     * @param songId
     * @param newPos
     * @return
     * @throws SQLException 
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
