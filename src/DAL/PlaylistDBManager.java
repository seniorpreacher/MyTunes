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
     * @throws SQLException
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
     * @throws SQLException
     */
    public Playlist getPlaylistByID(int iden) throws SQLException {
        Playlist pla = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("SELECT * FROM Playlist WHERE ID = ?");
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

    /**
     * Inserts and stores a new playlist into the database table called
     * 'Playlist'.
     *
     * @param pla a playlist entity that's passed to the method by the UI.
     * @throws SQLException
     */
    public void insertPlaylist(Playlist pla) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("INSERT INTO Playlist VALUES (?, ?, ?)");
        plaQue.setString(2, pla.getName());
        plaQue.setTimestamp(3, new Timestamp(pla.getCreatedOn().getTime()));
        plaQue.executeUpdate();

        conn.close();
    }

    /**
     * Gets the last entry from the table 'Artist' and makes an entity out of
     * it.
     *
     * @return a new Artist entity based on the last entry in the table.
     * @throws SQLException
     */
    public Playlist getLastPlaylist() throws SQLException {
        Playlist pla = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("SELECT * FROM Playlist WHERE ID = MAX(ID)");
        ResultSet plaRes = plaQue.executeQuery();

        plaRes.next();
        int id = plaRes.getInt("ID");
        String name = plaRes.getString("Name");
        Timestamp createdOnDB = plaRes.getTimestamp("Created");
        java.util.Date createdOnL = createdOnDB;
        long createdOn = createdOnL.getTime();
        pla = new Playlist(id, name, createdOn);

        conn.close();
        return pla;
    }
    
    /**
     * Removes the playlist we specify by ID from the table called 'Playlist'.
     *
     * @param iden the ID of the playlist we want to remove.
     * @throws SQLException
     */    
    public void removePlaylistByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("DELETE FROM Playlist WHERE ID = ?");
        plaQue.setInt(1, iden);
        plaQue.executeQuery();

        conn.close();
    }
}
