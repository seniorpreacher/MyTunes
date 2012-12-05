package BL;

import BE.Artist;
import DAL.ArtistDBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class ArtistManager {
    
    private ArtistDBManager DBM;

    public ArtistManager() throws SQLException, IOException {
        DBM = new ArtistDBManager();
    }
    
    public ArrayList<Artist> getAllArtists() throws SQLException {
        return DBM.getAllArtists();
    }
    
    public Artist getArtistById(int id) throws SQLException {
        return DBM.getArtistByID(id);
    }
}
