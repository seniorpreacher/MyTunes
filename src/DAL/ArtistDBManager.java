package DAL;

import BE.Artist;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Artist database manager class.
 *
 * It contains all the methods that deals with the Artist table inside the
 * database.
 *
 * @author Anthony
 */
public class ArtistDBManager extends DBManager {

    /**
     * The constructor for the Artist DB Manager class.
     *
     * @throws SQLException Exception, because it deals with the database.
     * @throws IOException Exception, because the database manager uses a
     * configuration file to load it's settings.
     */
    public ArtistDBManager() throws SQLException, IOException {
        super();
    }

    /**
     * Gets all the Artists out of the database table called Artist.
     *
     * Makes a query to the database and constructs an ArrayList full of Artists
     * instances with the information that it got from the database.
     *
     * @return an ArrayList with all the artists from the database.
     * @throws SQLException Exception, because it deals with the database.
     */
    public ArrayList<Artist> getAllArtists() throws SQLException {
        ArrayList<Artist> artList = new ArrayList<>();
        Connection conn = dataSource.getConnection();

        PreparedStatement artQue = conn.prepareStatement("SELECT * FROM Artist");
        ResultSet artRes = artQue.executeQuery();

        while (artRes.next()) {
            int id = artRes.getInt("ID");
            String name = artRes.getString("Name");
            artList.add(new Artist(id, name));
        }

        conn.close();
        return artList;
    }

    /**
     * Gets a specific Artist entity out of the database table Artist.
     *
     * Takes in a ID number and makes a query to the database, then it
     * constructs an Artist instance based on the query results.
     *
     * @param iden ID number of the specific Artist you're looking for.
     * @return a specific Artist instance.
     * @throws SQLException Exception, because it deals with the database.
     */
    public Artist getArtistByID(int iden) throws SQLException {
        Artist art = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT * FROM Artist WHERE id = ?");
        sonQue.setInt(1, iden);
        ResultSet artRes = sonQue.executeQuery();

        artRes.next();
        int id = artRes.getInt("ID");
        String name = artRes.getString("Name");
        art = new Artist(id, name);

        conn.close();
        return art;
    }

    /**
     * Inserts and stores a new artist into the database table called 'Artist'.
     *
     * @param art an artist entity that's passed to the method by the UI.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void insertArtist(Artist art) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement artQue = conn.prepareStatement("INSERT INTO Artist VALUES (?, ?)");
        artQue.setString(2, art.getName());
        artQue.executeUpdate();

        conn.close();
    }

    /**
     * Gets the last entry from the table 'Artist' and makes an entity out of
     * it.
     *
     * @return a new Artist entity based on the last entry in the table.
     * @throws SQLException Exception, because it deals with the database.
     */
    public Artist getLastArtist() throws SQLException {
        Artist art = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement artQue = conn.prepareStatement("SELECT * FROM Artist WHERE ID = MAX(ID)");
        ResultSet artRes = artQue.executeQuery();

        artRes.next();
        int id = artRes.getInt("ID");
        String name = artRes.getString("Name");
        art = new Artist(id, name);

        conn.close();
        return art;
    }

    /**
     * Removes the artist we specify by ID from the table called 'Artist'.
     *
     * @param iden the ID of the artist we want to remove.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void removeArtistByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement artQue = conn.prepareStatement("DELETE FROM Artist WHERE ID = ?");
        artQue.setInt(1, iden);
        artQue.executeUpdate();

        conn.close();
    }
}
