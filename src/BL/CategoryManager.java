package BL;

import BE.Category;
import DAL.CategoryDBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class CategoryManager {
    
    private CategoryDBManager DBM;

    public CategoryManager() throws SQLException, IOException {
        DBM = new CategoryDBManager();
    }
    
    public ArrayList<Category> getAllCategories() throws SQLException {
        return DBM.getAllCategories();
    }
    
    public Category getCategoryById(int id) throws SQLException {
        return DBM.getCategoryByID(id);
    }
}
