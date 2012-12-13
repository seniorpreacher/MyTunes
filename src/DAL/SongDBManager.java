package DAL;

import BE.Song;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Song database manager class.
 *
 * It contains all the methods that deals with the Song table inside the
 * database.
 * 
 * @author Anthony
 */
public class SongDBManager extends DBManager {

    /**
     * The constructor for the Song database manager class.
     * 
     * @throws SQLException Exception, because it deals with the database.
     * @throws IOException Exception, because the database manager uses a
     * configuration file to load it's settings.
     */
    public SongDBManager() throws SQLException, IOException {
        super();
    }

    /**
     * Gets all the Songs from the database table called Song with Artist and
     * Category name.
     *
     * Makes a query to the database and constructs an ArrayList full of Song
     * instances with the information that it got from the database.
     *
     * @return an ArrayList with all the songs from the database.
     * @throws SQLException Exception, because it deals with the database.
     */
    public ArrayList<Song> getAllSongs() throws SQLException {
        ArrayList<Song> sonList = new ArrayList<>();
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT Song.*, Artist.Name as ArtistName, Category.Category as CategoryName FROM Song INNER JOIN Artist ON Artist.ID = Song.ArtistID INNER JOIN Category ON Category.ID = Song.CategoryID WHERE Song.ArtistID = Artist.ID");
        ResultSet sonRes = sonQue.executeQuery();

        while (sonRes.next()) {
            int id = sonRes.getInt("ID");
            String title = sonRes.getString("Title");
            int artistId = sonRes.getInt("ArtistID");
            int categoryId = sonRes.getInt("CategoryID");
            String fileName = sonRes.getString("FileName");
            int duration = sonRes.getInt("Duration");
            String artistName = sonRes.getString("ArtistName");
            String categoryName = sonRes.getString("CategoryName");
            sonList.add(new Song(id, title, artistId, categoryId, fileName, duration, artistName, categoryName));
        }

        conn.close();
        return sonList;
    }

    /**
     * Gets a specific Song entity out of the database table Song with Artist
     * and Category name.
     *
     * Takes in a ID number and makes a query to the database. Then constructs a
     * Song instance based on the query results.
     *
     * @param iden ID number of the specific Song you're looking for.
     * @return a specific Song instance.
     * @throws SQLException Exception, because it deals with the database.
     */
    public Song getSongByID(int iden) throws SQLException {
        Song son = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT Song.*, Artist.Name as ArtistName, Category.Category as CategoryName FROM Song INNER JOIN Artist ON Artist.ID = Song.ArtistID INNER JOIN Category ON Category.ID = Song.CategoryID WHERE Song.ID = ? AND Song.ArtistID = Artist.ID");
        sonQue.setInt(1, iden);
        ResultSet sonRes = sonQue.executeQuery();

        sonRes.next();
        int id = sonRes.getInt("ID");
        String title = sonRes.getString("Title");
        int artistId = sonRes.getInt("ArtistID");
        int categoryId = sonRes.getInt("CategoryID");
        String fileName = sonRes.getString("FileName");
        int duration = sonRes.getInt("Duration");
        String artistName = sonRes.getString("ArtistName");
        String categoryName = sonRes.getString("CategoryName");
        son = new Song(id, title, artistId, categoryId, fileName, duration, artistName, categoryName);

        conn.close();
        return son;
    }

    /**
     * Inserts and stores a song into the database table called 'Song'.
     *
     * @param son a song entity that's passed to the method by the UI.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void insertSong(Song son) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("INSERT INTO Song (Title, ArtistID, CategoryID, FileName, Duration) VALUES (?, ?, ?, ?, ?)");
        sonQue.setString(1, son.getTitle());
        sonQue.setInt(2, son.getArtistId());
        sonQue.setInt(3, son.getCategoryId());
        sonQue.setString(4, son.getFileName());
        sonQue.setInt(5, son.getDuration());
        sonQue.executeUpdate();

        conn.close();
    }

    /**
     * Get the last element inside the Song table with Artist and Category name.
     *
     * Gets the last entry from the table 'Artist' and makes an entity out of
     * it.
     *
     * @return a new Artist entity based on the last entry in the table.
     * @throws SQLException Exception, because it deals with the database.
     */
    public Song getLastSong() throws SQLException {
        Song son = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT Song.*, Artist.Name as ArtistName, Category.Name as CategoryName FROM Song INNER JOIN Artist ON Artist.ID = Song.ArtistID INNER JOIN Category ON Category.ID = Song.CategoryID WHERE Song.ID = MAX(ID) AND Song.ArtistID = Artist.ID");
        ResultSet sonRes = sonQue.executeQuery();

        sonRes.next();
        int id = sonRes.getInt("ID");
        String title = sonRes.getString("Title");
        int artistId = sonRes.getInt("ArtistID");
        int categoryId = sonRes.getInt("CategoryID");
        String fileName = sonRes.getString("FileName");
        int duration = sonRes.getInt("Duration");
        String artistName = sonRes.getString("ArtistName");
        String categoryName = sonRes.getString("CategoryName");
        son = new Song(id, title, artistId, categoryId, fileName, duration, artistName, categoryName);

        conn.close();
        return son;
    }

    /**
     * Removes the song we specify by ID from the table called 'Song'.
     *
     * @param iden the ID of the song we want to remove.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void removeSongByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("DELETE FROM Song WHERE ID = ?");
        sonQue.setInt(1, iden);
        sonQue.executeUpdate();

        conn.close();
    }

    /**
     * Update an already existing song in the database.
     *
     * Updates an already existing song in the database based on the song ID. If
     * nothing is entered we shouldn't change that column.
     *
     * @param iden the ID of the song you want to change.
     * @param title the NEW title for the song.
     * @param artistId the NEW artistId which was chosen for the song.
     * @param categoryId the NEW categoryId which was chosen for the song.
     * @param fileName the NEW filename for the song.
     * @param duration the NEW duration for the song.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void updateSong(Song song) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("UPDATE Song SET Title = ?, ArtistID = ?, CategoryID = ?, FileName = ?, Duration = ? WHERE ID = ?");
        sonQue.setString(1, song.getTitle());
        sonQue.setInt(2, song.getArtistId());
        sonQue.setInt(3, song.getCategoryId());
        sonQue.setString(4, song.getFileName());
        sonQue.setInt(5, song.getDuration());
        sonQue.setInt(6, song.getId());
        sonQue.executeUpdate();

        conn.close();
    }

    /**
     * Searches inside the Song and Artist table for a keyword in the names.
     *
     * Takes in a parameter which them be looked up and matched with the
     * existing data in ' Song.Title ' and ' Artist.Name '. The results will be
     * stored in an ArrayList full of Songs.
     *
     * @param term The search term which will be used for looking up data.
     * @return an ArrayList full of songs which match the search.
     * @throws SQLException Exception, because it deals with the database.
     */
    public ArrayList<Song> searchSongs(String term) throws SQLException {
        ArrayList<Song> sonList = new ArrayList<>();
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT Song.*, Artist.Name as ArtistName, Category.Category as CategoryName FROM Song INNER JOIN Artist ON Artist.ID = Song.ArtistID INNER JOIN Category ON Category.ID = Song.CategoryID WHERE Song.Title LIKE ? OR Artist.Name LIKE ?");
        sonQue.setString(1, "%" + term + "%");
        sonQue.setString(2, "%" + term + "%");
        ResultSet sonRes = sonQue.executeQuery();

        while (sonRes.next()) {
            int id = sonRes.getInt("ID");
            String title = sonRes.getString("Title");
            int artistId = sonRes.getInt("ArtistID");
            int categoryId = sonRes.getInt("CategoryID");
            String fileName = sonRes.getString("FileName");
            int duration = sonRes.getInt("Duration");
            String artistName = sonRes.getString("ArtistName");
            String categoryName = sonRes.getString("CategoryName");
            sonList.add(new Song(id, title, artistId, categoryId, fileName, duration, artistName, categoryName));
        }

        conn.close();
        return sonList;
    }

    /**
     * Lists all the songs from a selected playlist.
     *
     * @param playlistId The ID of the playlist which we want to select.
     * @return an ArrayList full of songs from that specific playlist
     * @throws SQLException Exception, because it deals with the database.
     */
    public ArrayList<Song> getSongsFromPlaylist(int playlistId) throws SQLException {
        ArrayList<Song> sonList = new ArrayList<>();
        Connection conn = dataSource.getConnection();

        PreparedStatement sonQue = conn.prepareStatement("SELECT Song.*, Artist.Name as ArtistName, Category.Category as CategoryName FROM Song INNER JOIN PlaylistSong ON PlaylistSong.SongID = Song.ID INNER JOIN Artist ON Artist.ID = Song.ArtistID INNER JOIN Category ON Category.ID = Song.CategoryID WHERE PlaylistSong.PlaylistID = ? ORDER BY PlaylistSong.SeqNum ASC");
        sonQue.setInt(1, playlistId);
        ResultSet sonRes = sonQue.executeQuery();

        while (sonRes.next()) {
            int id = sonRes.getInt("ID");
            String title = sonRes.getString("Title");
            int artistId = sonRes.getInt("ArtistID");
            int categoryId = sonRes.getInt("CategoryID");
            String fileName = sonRes.getString("FileName");
            int duration = sonRes.getInt("Duration");
            String artistName = sonRes.getString("ArtistName");
            String categoryName = sonRes.getString("CategoryName");
            sonList.add(new Song(id, title, artistId, categoryId, fileName, duration, artistName, categoryName));
        }

        conn.close();
        return sonList;
    }
}
