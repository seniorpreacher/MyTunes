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
}
