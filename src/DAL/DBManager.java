/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
            dataSource.setDatabaseName("Aircademy");
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

    public ArrayList<Song> getAllSongs() throws SQLException {
        Connection conn = dataSource.getConnection();
        ArrayList<Song> sonList = new ArrayList<>();

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

    public ArrayList<Artist> getAllArtists() throws SQLException {
        Connection conn = dataSource.getConnection();
        ArrayList<Artist> artList = new ArrayList<>();

        PreparedStatement artQue = conn.prepareStatement("SELECT * FROM Artist");
        ResultSet artRes = artQue.executeQuery();

        while (artRes.next()) {
            int id = artRes.getInt("ID");
            String name = artRes.getString("Name");
            artList.add(new Artist(id, name));
        }
        return artList;
    }

    public ArrayList<Category> getAllCategories() throws SQLException {
        Connection conn = dataSource.getConnection();
        ArrayList<Category> catList = new ArrayList<>();

        PreparedStatement catQue = conn.prepareStatement("SELECT * FROM Category");
        ResultSet catRes = catQue.executeQuery();

        while (catRes.next()) {
            int id = catRes.getInt("ID");
            String name = catRes.getString("Category");
            catList.add(new Category(id, name));
        }
        return catList;
    }

    public ArrayList<Playlist> getAllPlaylists() throws SQLException {
        Connection conn = dataSource.getConnection();
        ArrayList<Playlist> plaList = new ArrayList<>();

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

    public Song getSongByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();
        Song son;

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

    public Artist getArtistByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();
        Artist art;

        PreparedStatement sonQue = conn.prepareStatement("SELECT * FROM Artist WHERE id = ?");
        sonQue.setInt(1, iden);
        ResultSet artRes = sonQue.executeQuery();

        artRes.next();
        int id = artRes.getInt("ID");
        String name = artRes.getString("Name");
        art = new Artist(id, name);

        return art;
    }

    public Category getCategoryByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();
        Category cat;

        PreparedStatement catQue = conn.prepareStatement("SELECT * FROM Category WHERE id = ?");
        catQue.setInt(1, iden);
        ResultSet catRes = catQue.executeQuery();

        catRes.next();
        int id = catRes.getInt("ID");
        String name = catRes.getString("Category");
        cat = new Category(id, name);

        return cat;
    }

    public Playlist getPlaylistByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();
        Playlist pla;

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
