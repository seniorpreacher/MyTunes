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
 * Playlist database manager class.
 *
 * It contains all the methods that deals with the Playlist table inside the
 * database.
 *
 * @author Anthony
 */
public class PlaylistDBManager extends DBManager {

    /**
     * The constructor for the Playlist database manager class.
     *
     * @throws SQLException Exception, because it deals with the database.
     * @throws IOException Exception, because the database manager uses a
     * configuration file to load it's settings.
     */
    public PlaylistDBManager() throws SQLException, IOException {
        super();
    }

    /**
     * Gets all the Playlists out of the database table called Playlist.
     *
     * Makes a query to the database and constructs an ArrayList full of
     * Playlist instances with the information that it got from the database.
     *
     * @return an ArrayList with all the playlists from the database.
     * @throws SQLException Exception, because it deals with the database.
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
     * Gets a specific Playlist entity out of the database table called
     * Playlist.
     *
     * Takes in a ID number and makes a query to the database, then it
     * constructs a Playlist instance based on the query results.
     *
     * @param iden ID number of the specific Playlist you're looking for.
     * @return a specific Playlist instance.
     * @throws SQLException Exception, because it deals with the database.
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
     * Searches the Playlist table for the specified name.
     *
     * Takes in a String and makes a query to the database, then it constructs a
     * Playlist instance based on the query results.
     *
     * @param search A keyword that will be used for searching.
     * @return a specific Playlist instance.
     * @throws SQLException Exception, because it deals with the database.
     */
    public ArrayList<Playlist> getPlaylist(String search) throws SQLException {
        ArrayList<Playlist> ret = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement plaQue = conn.prepareStatement("SELECT * FROM Playlist WHERE Name LIKE ?");
            plaQue.setString(1, "%" + search + "%");
            ResultSet plaRes = plaQue.executeQuery();

            while (plaRes.next()) {
                int id = plaRes.getInt("ID");
                String name = plaRes.getString("Name");
                Timestamp createdOnDB = plaRes.getTimestamp("Created");
                java.util.Date createdOnL = createdOnDB;
                long createdOn = createdOnL.getTime();

                ret.add(new Playlist(id, name, createdOn));
            }
        }
        return ret;
    }

    /**
     * Inserts and stores a new playlist into the database table called
     * 'Playlist'.
     *
     * @param pla a playlist entity that's passed to the method by the UI.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void insertPlaylist(Playlist pla) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("INSERT INTO Playlist (Name, Created) VALUES (?, ?)");
        plaQue.setString(1, pla.getName());
        plaQue.setTimestamp(2, new Timestamp(pla.getCreatedOn().getTime()));
        plaQue.executeUpdate();

        conn.close();
    }

    /**
     * Removes the playlist we specify by ID from the table called 'Playlist'.
     *
     * @param iden the ID of the playlist we want to remove.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void removePlaylistByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("DELETE FROM Playlist WHERE ID = ?");
        plaQue.setInt(1, iden);
        plaQue.executeUpdate();

        conn.close();
    }

    /**
     * Insert a specific song into a specific playlist.
     *
     * Takes the two parameters and makes an INSERT query to the PlaylistSong
     * table in the database.
     *
     * @param playlistIden the ID of the playlist where we want to insert a
     * song.
     * @param songIden the ID of the song which we want to insert into a
     * playlist.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void insertSongToPlaylist(int playlistIden, int songIden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("INSERT INTO PlaylistSong VALUES (?, ?, (SELECT ISNULL(MAX(SeqNum),0) FROM PlaylistSong WHERE PlaylistID = ?)+1)");
        plaQue.setInt(1, playlistIden);
        plaQue.setInt(2, songIden);
        plaQue.setInt(3, playlistIden);
        plaQue.executeUpdate();

        conn.close();
    }

    /**
     * Remove a specific song from a specific playlist.
     *
     * Takes the two parameters and runs a DELETE query on the PlaylistSong
     * table deleting the corresponding line.
     *
     * @param playlistIden the ID of the playlist from which we want to remove a
     * song.
     * @param songIden the ID of the song we want to remove from a specific
     * playlist.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void removeSongFromPlaylist(int playlistIden, int songIden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("DELETE FROM PlaylistSong WHERE PlaylistID = ? AND SongID = ?");
        plaQue.setInt(1, playlistIden);
        plaQue.setInt(2, songIden);
        plaQue.executeUpdate();

        conn.close();
    }

    /**
     * Moves a song on a playlist up or down a position.
     *
     * With the selected parameters first it selects and stores the current
     * position of that song. After that it updates the sequence number of the
     * song which we need to replace in order to move our song up or down and
     * sets it to 0. Then it sets our song to the desired position and moving
     * back the song we set earlier to 0 to the initial position of our song.
     *
     * @param playlistIden the ID of our selected playlist.
     * @param songIden the ID of our selected song.
     * @param newPos -1 if we want to move a song higher in a playlist and +1 if
     * we want to move it down. We take care in the UI so the method only gets
     * these two options.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void moveSong(int playlistIden, int songIden, int newPos) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("SELECT SeqNum FROM PlaylistSong WHERE PlaylistID = ? AND SongID = ?");
        plaQue.setInt(1, playlistIden);
        plaQue.setInt(2, songIden);
        ResultSet plaRes = plaQue.executeQuery();
        plaRes.next();
        int currentSeqNum = plaRes.getInt("SeqNum");

        PreparedStatement pla2Que = conn.prepareStatement("UPDATE PlaylistSong SET SeqNum = 0 WHERE PlaylistID = ? AND"
                + " SeqNum = ?; UPDATE PlaylistSong SET SeqNum = ? WHERE PlaylistID = ? AND SongID = ?;"
                + " UPDATE PlaylistSong SET SeqNum = ? WHERE SeqNum = 0");
        pla2Que.setInt(1, playlistIden);
        pla2Que.setInt(2, currentSeqNum + newPos);
        pla2Que.setInt(3, currentSeqNum + newPos);
        pla2Que.setInt(4, playlistIden);
        pla2Que.setInt(5, songIden);
        pla2Que.setInt(6, currentSeqNum);
        pla2Que.executeUpdate();

        conn.close();
    }

    /**
     * Gets the current, maximum and minimum sequence number inside a desired
     * playlist of a song.
     *
     * With the given parameters makes three different queries and with a
     * unified result it sets the integer array with the sequence numbers. This
     * array is used by the Business Logic Layer to check if it possible or not
     * to move the song.
     *
     * @param playlistIden the ID of our selected playlist.
     * @param songIden the ID of our selected song.
     * @param newPos -1 if we want to move a song higher in a playlist and +1 if
     * we want to move it down. We take care in the UI so the method only gets
     * these two options.
     * @return res An integer array which contains the current, maximum and
     * minimum sequence number of our selected song in the selected playlist.
     * @throws SQLException Exception, because it deals with the database.
     */
    public int[] getSeqAndMaxSeq(int playlistIden, int songIden, int newPos) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("SELECT SeqNum FROM PlaylistSong WHERE PlaylistID = ? AND SongID = ? UNION ALL SELECT SeqNum = MAX(SeqNum) FROM PlaylistSong WHERE PlaylistID = ? UNION ALL SELECT SeqNum = MIN(SeqNum) FROM PlaylistSong WHERE PlaylistID = ?;");
        plaQue.setInt(1, playlistIden);
        plaQue.setInt(2, songIden);
        plaQue.setInt(3, playlistIden);
        plaQue.setInt(4, playlistIden);
        ResultSet plaRes = plaQue.executeQuery();
        plaRes.next();
        int curSeqNum = plaRes.getInt(1);
        plaRes.next();
        int maxSeqNum = plaRes.getInt(1);
        plaRes.next();
        int minSeqNum = plaRes.getInt(1);
        int[] res = new int[]{curSeqNum, maxSeqNum, minSeqNum};
        return res;
    }
}
