package DAL;

import BE.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DBManager class.
 *
 * Manages the whole database.
 *
 * @author Anthony
 */
public class DBManager {

    private SQLServerDataSource dataSource = new SQLServerDataSource();

    /**
     * Constructor.
     *
     * Makes the connection and catches the exception in case there is a
     * connection problem.
     */
    public DBManager() {
        try {
            Connection conn;
            dataSource.setUser("java");
            dataSource.setPassword("java");
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("MyTunes");
            dataSource.setInstanceName("SQLEXPRESS");
            conn = dataSource.getConnection();
            conn.close();
            //System.out.println("We got a connection"); 
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    /**
     * Makes a query to the database and constructs an ArrayList full of Song 
     * instances with the information that it got from the database.
     * 
     * @return an ArrayList with all the songs from the database.
     */
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> sonList = new ArrayList<>();
        try {
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
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sonList;
    }
    
    /**
     * Makes a query to the database and constructs an ArrayList full of Artists 
     * instances with the information that it got from the database.
     * 
     * @return an ArrayList with all the artists from the database.
     */
    public ArrayList<Artist> getAllArtists() {
        ArrayList<Artist> artList = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement artQue = conn.prepareStatement("SELECT * FROM Artist");
            ResultSet artRes = artQue.executeQuery();

            while (artRes.next()) {
                int id = artRes.getInt("ID");
                String name = artRes.getString("Name");
                artList.add(new Artist(id, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artList;
    }

    /**
     * Makes a query to the database and constructs an ArrayList full of Category 
     * instances with the information that it got from the database.
     * 
     * @return an ArrayList with all the categories from the database.
     */    
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> catList = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement catQue = conn.prepareStatement("SELECT * FROM Category");
            ResultSet catRes = catQue.executeQuery();

            while (catRes.next()) {
                int id = catRes.getInt("ID");
                String name = catRes.getString("Category");
                catList.add(new Category(id, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return catList;
    }

    /**
     * Makes a query to the database and constructs an ArrayList full of Playlist 
     * instances with the information that it got from the database.
     * 
     * @return an ArrayList with all the playlists from the database.
     */
    public ArrayList<Playlist> getAllPlaylists() {
        ArrayList<Playlist> plaList = new ArrayList<>();
        try {
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
        } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plaList;
    }

    /**
     * Takes in a ID number and makes a query to the database. Then constructs a 
     * Song instance based on the query results.
     * 
     * @param iden ID number of the specific Song you're looking for.
     * @return a specific Song instance.
     */
    public Song getSongByID(int iden) {
        Song son = null;
        try {
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
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return son;
    }
    
    /**
     * Takes in a ID number and makes a query to the database. Then constructs 
     * an Artist instance based on the query results.
     * 
     * @param iden ID number of the specific Artist you're looking for.
     * @return a specific Artist instance.
     */
    public Artist getArtistByID(int iden) {
        Artist art = null;
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement sonQue = conn.prepareStatement("SELECT * FROM Artist WHERE id = ?");
            sonQue.setInt(1, iden);
            ResultSet artRes = sonQue.executeQuery();

            artRes.next();
            int id = artRes.getInt("ID");
            String name = artRes.getString("Name");
            art = new Artist(id, name);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return art;
    }

    /**
     * Takes in a ID number and makes a query to the database. Then constructs a 
     * Category instance based on the query results.
     * 
     * @param iden ID number of the specific Category you're looking for.
     * @return a specific Category instance.
     */    
    public Category getCategoryByID(int iden) {
        Category cat = null;
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement catQue = conn.prepareStatement("SELECT * FROM Category WHERE id = ?");
            catQue.setInt(1, iden);
            ResultSet catRes = catQue.executeQuery();

            catRes.next();
            int id = catRes.getInt("ID");
            String name = catRes.getString("Category");
            cat = new Category(id, name);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

    /**
     * Takes in a ID number and makes a query to the database. Then constructs a 
     * Playlist instance based on the query results.
     * 
     * @param iden ID number of the specific Playlist you're looking for.
     * @return a specific Playlist instance.
     */    
    public Playlist getPlaylistByID(int iden) {
        Playlist pla = null;
        try {
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
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pla;
    }  
}
