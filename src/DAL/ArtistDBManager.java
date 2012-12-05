package DAL;

import BE.Artist;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class ArtistDBManager extends DBManager {

    public ArtistDBManager() throws SQLException, IOException {
        super();
    }

    /**
     * Makes a query to the database and constructs an ArrayList full of Artists
     * instances with the information that it got from the database.
     *
     * @return an ArrayList with all the artists from the database.
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
        return artList;
    }

    /**
     * Takes in a ID number and makes a query to the database. Then constructs
     * an Artist instance based on the query results.
     *
     * @param iden ID number of the specific Artist you're looking for.
     * @return a specific Artist instance.
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
        return art;
    }
}
