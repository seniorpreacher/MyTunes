package DAL;

import BE.Playlist;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class PlaylistDBManager extends DBManager {

    public PlaylistDBManager() throws SQLException, IOException {
        super();
    }

    /**
     * Makes a query to the database and constructs an ArrayList full of
     * Playlist instances with the information that it got from the database.
     *
     * @return an ArrayList with all the playlists from the database.
     */
    public ArrayList<Playlist> getAllPlaylists() throws SQLException {
        ArrayList<Playlist> plaList = new ArrayList<>();
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("SELECT * FROM Playlist");
        ResultSet plaRes = plaQue.executeQuery();

        while (plaRes.next()) {
            int id = plaRes.getInt("ID");
            String name = plaRes.getString("Name");
            Timestamp createdOnDB = plaRes.getTimestamp("Created");
            java.util.Date createdOnL = createdOnDB;
            long createdOn = createdOnL.getTime();
            plaList.add(new Playlist(id, name, createdOn));
        }
        return plaList;
    }

    /**
     * Takes in a ID number and makes a query to the database. Then constructs a
     * Playlist instance based on the query results.
     *
     * @param iden ID number of the specific Playlist you're looking for.
     * @return a specific Playlist instance.
     */
    public Playlist getPlaylistByID(int iden) throws SQLException {
        Playlist pla = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("SELECT * FROM Playlist WHERE id = ?");
        plaQue.setInt(1, iden);
        ResultSet plaRes = plaQue.executeQuery();

        plaRes.next();
        int id = plaRes.getInt("ID");
        String name = plaRes.getString("Name");
        Timestamp createdOnDB = plaRes.getTimestamp("Created");
        java.util.Date createdOnL = createdOnDB;
        long createdOn = createdOnL.getTime();
        pla = new Playlist(id, name, createdOn);
        return pla;
    }
}
