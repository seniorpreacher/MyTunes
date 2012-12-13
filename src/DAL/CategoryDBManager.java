package DAL;

import BE.Category;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Category database manager class.
 *
 * It contains all the methods that deals with the Category table inside the
 * database.
 *
 * @author Anthony
 */
public class CategoryDBManager extends DBManager {

    /**
     * The constructor for the Artist DB Manager class.
     *
     * @throws SQLException Exception, because it deals with the database.
     * @throws IOException Exception, because the database manager uses a
     * configuration file to load it's settings.
     */
    public CategoryDBManager() throws SQLException, IOException {
        super();
    }

    /**
     * Gets all the Categories out of the database table called Category.
     *
     * Makes a query to the database and constructs an ArrayList full of
     * Category instances with the information that it got from the database.
     *
     * @return an ArrayList with all the categories from the database.
     * @throws SQLException Exception, because it deals with the database.
     */
    public ArrayList<Category> getAllCategories() throws SQLException {
        ArrayList<Category> catList = new ArrayList<>();
        Connection conn = dataSource.getConnection();

        PreparedStatement catQue = conn.prepareStatement("SELECT * FROM Category");
        ResultSet catRes = catQue.executeQuery();

        while (catRes.next()) {
            int id = catRes.getInt("ID");
            String name = catRes.getString("Category");
            catList.add(new Category(id, name));
        }

        conn.close();
        return catList;
    }

    /**
     * Gets a specific Category entity out of the database table called
     * Category.
     *
     * Takes in a ID number and makes a query to the database, then it
     * constructs a Category instance based on the query results.
     *
     * @param iden ID number of the specific Category you're looking for.
     * @return a specific Category instance.
     * @throws SQLException Exception, because it deals with the database.
     */
    public Category getCategoryByID(int iden) throws SQLException {
        Category cat = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement catQue = conn.prepareStatement("SELECT * FROM Category WHERE ID = ?");
        catQue.setInt(1, iden);
        ResultSet catRes = catQue.executeQuery();

        catRes.next();
        int id = catRes.getInt("ID");
        String name = catRes.getString("Category");
        cat = new Category(id, name);

        conn.close();
        return cat;
    }

    /**
     * Inserts and stores a new category into the database table called
     * 'Category'.
     *
     * @param cat a category entity that's passed to the method by the UI.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void insertCategory(Category cat) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement catQue = conn.prepareStatement("INSERT INTO Category VALUES (?, ?)");
        catQue.setString(2, cat.getName());
        catQue.executeUpdate();

        conn.close();
    }

    /**
     * Gets the last entry from the table 'Artist' and makes an entity out of
     * it.
     *
     * @return a new Artist entity based on the last entry in the table.
     * @throws SQLException Exception, because it deals with the database.
     */
    public Category getLastCategory() throws SQLException {
        Category cat = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement catQue = conn.prepareStatement("SELECT * FROM Category WHERE ID = MAX(ID)");
        ResultSet catRes = catQue.executeQuery();

        catRes.next();
        int id = catRes.getInt("ID");
        String name = catRes.getString("Category");
        cat = new Category(id, name);

        conn.close();
        return cat;
    }

    /**
     * Removes the category we specify by ID from the table called 'Category'.
     *
     * @param iden the ID of the category we want to remove.
     * @throws SQLException Exception, because it deals with the database.
     */
    public void removeCategoryByID(int iden) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement catQue = conn.prepareStatement("DELETE FROM Category WHERE ID = ?");
        catQue.setInt(1, iden);
        catQue.executeUpdate();

        conn.close();
    }
}