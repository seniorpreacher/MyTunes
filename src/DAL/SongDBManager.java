package DAL;

import BE.Song;
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
public class SongDBManager extends DBManager {

    public SongDBManager() throws SQLException, IOException {
        super();
    }

    /**
     * Makes a query to the database and constructs an ArrayList full of Song
     * instances with the information that it got from the database.
     *
     * @return an ArrayList with all the songs from the database.
     * @throws SQLException
     */
    public ArrayList<Song> getAllSongs() throws SQLException {
        ArrayList<Song> sonList = new ArrayList<>();
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT * FROM Song");
        ResultSet sonRes = sonQue.executeQuery();

        while (sonRes.next()) {
            int id = sonRes.getInt("ID");
            String title = sonRes.getString("Title");
            int artistId = sonRes.getInt("ArtistID");
            int categoryId = sonRes.getInt("CategoryID");
            String fileName = sonRes.getString("FileName");
            int duration = sonRes.getInt("Duration");
            sonList.add(new Song(id, title, artistId, categoryId, fileName, duration));
        }

        conn.close();
        return sonList;
    }

    /**
     * Takes in a ID number and makes a query to the database. Then constructs a
     * Song instance based on the query results.
     *
     * @param iden ID number of the specific Song you're looking for.
     * @return a specific Song instance.
     * @throws SQLException
     */
    public Song getSongByID(int iden) throws SQLException {
        Song son = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT * FROM Song WHERE ID = ?");
        sonQue.setInt(1, iden);
        ResultSet sonRes = sonQue.executeQuery();

        sonRes.next();
        int id = sonRes.getInt("ID");
        String title = sonRes.getString("Title");
        int artistId = sonRes.getInt("ArtistID");
        int categoryId = sonRes.getInt("CategoryID");
        String fileName = sonRes.getString("FileName");
        int duration = sonRes.getInt("Duration");
        son = new Song(id, title, artistId, categoryId, fileName, duration);

        conn.close();
        return son;
    }

    /**
     * Inserts and stores a song into the database table called 'Song'.
     *
     * @param son a song entity that's passed to the method by the UI.
     * @throws SQLException
     */
    public void insertSong(Song son) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("INSERT INTO Song VALUES (?, ?, ?, ?, ?, ?)");
        sonQue.setString(2, son.getTitle());
        sonQue.setInt(3, son.getArtistId());
        sonQue.setInt(4, son.getCategoryId());
        sonQue.setString(5, son.getFileName());
        sonQue.setInt(6, son.getDuration());
        sonQue.executeUpdate();

        conn.close();
    }

    /**
     * Gets the last entry from the table 'Artist' and makes an entity out of
     * it.
     *
     * @return a new Artist entity based on the last entry in the table.
     * @throws SQLException
     */
    public Song getLastSong() throws SQLException {
        Song son = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT * FROM Song WHERE ID = MAX(ID)");
        ResultSet sonRes = sonQue.executeQuery();

        sonRes.next();
        int id = sonRes.getInt("ID");
        String title = sonRes.getString("Title");
        int artistId = sonRes.getInt("ArtistID");
        int categoryId = sonRes.getInt("CategoryID");
        String fileName = sonRes.getString("FileName");
        int duration = sonRes.getInt("Duration");
        son = new Song(id, title, artistId, categoryId, fileName, duration);

        conn.close();
        return son;
    }

    /**
     * Removes the song we specify by ID from the table called 'Song'.
     *
     * @param iden the ID of the song we want to remove.
     * @throws SQLException
     */
    public void removeSongByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("DELETE FROM Song WHERE ID = ?");
        sonQue.setInt(1, iden);
        sonQue.executeQuery();

        conn.close();
    }

    /**
     * Updates an already existing song in the database based on the song ID.
     * If nothing is entered we shouldn't change that column.
     * 
     * @param iden the ID of the song you want to change.
     * @param title the NEW title for the song.
     * @param artistId the NEW artistId which was chosen for the song.
     * @param categoryId the NEW categoryId which was chosen for the song.
     * @param fileName the NEW filename for the song.
     * @param duration the NEW duration for the song.
     * @throws SQLException 
     */
    public void updateSong(int iden, String title, int artistId, int categoryId, String fileName, int duration) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("UPDATE Song SET Title = ?, ArtistID = ?, CategoryID = ?, FileName = ?, Duration = ? WHERE ID = ?");
        sonQue.setString(1, title);
        sonQue.setInt(2, artistId);
        sonQue.setInt(3, categoryId);
        sonQue.setString(4, fileName);
        sonQue.setInt(5, duration);
        sonQue.setInt(6, iden);
        sonQue.executeQuery();

        conn.close();
    }
}
