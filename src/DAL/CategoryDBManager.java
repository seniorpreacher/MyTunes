package DAL;

import BE.Category;
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
public class CategoryDBManager extends DBManager {

    public CategoryDBManager() throws SQLException, IOException {
        super();
    }

    /**
     * Makes a query to the database and constructs an ArrayList full of
     * Category instances with the information that it got from the database.
     *
     * @return an ArrayList with all the categories from the database.
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
        return catList;
    }

    /**
     * Takes in a ID number and makes a query to the database. Then constructs a
     * Category instance based on the query results.
     *
     * @param iden ID number of the specific Category you're looking for.
     * @return a specific Category instance.
     */
    public Category getCategoryByID(int iden) throws SQLException {
        Category cat = null;
        Connection conn = dataSource.getConnection();

        PreparedStatement catQue = conn.prepareStatement("SELECT * FROM Category WHERE id = ?");
        catQue.setInt(1, iden);
        ResultSet catRes = catQue.executeQuery();

        catRes.next();
        int id = catRes.getInt("ID");
        String name = catRes.getString("Category");
        cat = new Category(id, name);
        return cat;
    }
}
