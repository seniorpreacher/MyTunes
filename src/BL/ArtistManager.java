package BL;

import BE.Artist;
import DAL.ArtistDBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Manager class for the Artist entity.
 *
 * @author Anthony
 */
public class ArtistManager {

    private ArtistDBManager DBM;

    /**
     * Constructor for the ArtistManager class.
     *
     * @throws SQLException Exception, because it deals with the database
     * manager.
     * @throws IOException Exception, because the database manager uses a
     * configuration file to load it's settings.
     */
    public ArtistManager() throws SQLException, IOException {
        DBM = new ArtistDBManager();
    }

    /**
     * Gets all the Artist from the DB and stores it inside an ArrayList.
     *
     * @return ArrayList full of Artist.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public ArrayList<Artist> getAllArtists() throws SQLException {
        return DBM.getAllArtists();
    }

    /**
     * Gets a single Artist from the DB by a specified ID.
     *
     * @param id The ID of the artist we want to select and return.
     * @return An Artist entity
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public Artist getArtistById(int id) throws SQLException {
        return DBM.getArtistByID(id);
    }

    /**
     * Adds a new Artist to the DB by passing an entity to the DB manager.
     *
     * @param art The Artist entity which we want to pass to the DB manager.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void addArtist(Artist art) throws SQLException {
        DBM.insertArtist(art);
    }

    /**
     * Removes an Artist from the DB by passing an ID number to the DB manager.
     *
     s* @param id the ID of the Artist we want to pass on to the DB manager
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void removeArtist(int id) throws SQLException {
        DBM.removeArtistByID(id);
    }
}
