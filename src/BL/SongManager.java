package BL;

import BE.Song;
import DAL.SongDBManager;
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
}
