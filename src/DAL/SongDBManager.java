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

        return sonList;
    }

    /**
     * Takes in a ID number and makes a query to the database. Then constructs a
     * Song instance based on the query results.
     *
     * @param iden ID number of the specific Song you're looking for.
     * @return a specific Song instance.
     */
    public Song getSongByID(int iden) throws SQLException {
        Song son = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT * FROM Song WHERE id = ?");
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
        return son;
    }
}
