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
     * Removes the playlist we specify by ID from the table called 'Playlist'.
     *
     * @param iden the ID of the playlist we want to remove.
     * @throws SQLException
     */
    public void removePlaylistByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("DELETE FROM Playlist WHERE ID = ?");
        plaQue.setInt(1, iden);
        plaQue.executeUpdate();

        conn.close();
    }

    /**
     *
     * @param playlistIden
     * @param songIden
     */
    public void insertSongToPlaylist(int playlistIden, int songIden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("INSERT INTO PlaylistSong VALUES (?, ?, (SELECT MAX(SeqNum) FROM PlaylistSong WHERE PlaylistID = ?)+1)");
        plaQue.setInt(1, playlistIden);
        plaQue.setInt(2, songIden);
        plaQue.setInt(3, playlistIden);
        plaQue.executeUpdate();

        conn.close();
    }

    /**
     *
     * @param playlistIden
     * @param songIden
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
     * 
     * 
     * @param playlistIden the ID of our selected playlist.
     * @param songIden the ID of our selected song.
     * @param newPos -1 if we want to move a song higher in a playlist and +1 if
     * we want to move it down.
     */
    public void moveSong(int playlistIden, int songIden, int newPos) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("SELECT SeqNum FROM PlaylistSong WHERE PlaylistID = ? AND SongID = ?");
        plaQue.setInt(1, playlistIden);
        plaQue.setInt(2, songIden);
        ResultSet plaRes = plaQue.executeQuery();
        plaRes.next();
        int currentSeqNum = plaRes.getInt("SeqNum");

        PreparedStatement pla2Que = conn.prepareStatement("UPDATE PlaylistSong SET SeqNum = 0 WHERE PlaylistID = ? AND SeqNum = ?; UPDATE PlaylistSong SET SeqNum = ? WHERE PlaylistID = ? AND SongID = ?; UPDATE PlaylistSong SET SeqNum = ? WHERE SeqNum = 0");
        plaQue.setInt(1, playlistIden);
        pla2Que.setInt(2, currentSeqNum + newPos);
        pla2Que.setInt(3, currentSeqNum + newPos);
        pla2Que.setInt(4, currentSeqNum);
        pla2Que.executeQuery();

        conn.close();
    }

    /**
     * 
     * @param playlistIden
     * @param songIden
     * @param newPos
     * @return
     * @throws SQLException 
     */
    public int[] getSeqAndMaxSeq(int playlistIden, int songIden, int newPos) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement plaQue = conn.prepareStatement("SELECT SeqNum FROM PlaylistSong WHERE PlaylistID = ? AND SongID = ? UNION SELECT MAX(SeqNum) FROM PlaylistSong WHERE PlaylistID = ? UNION SELECT MIN(SeqNum) FROM PlaylistSong WHERE PlaylistID = ?");
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
