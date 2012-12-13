package BL;

import BE.Category;
import DAL.CategoryDBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Manager class for the Category entity.
 * 
 * @author Anthony
 */
public class CategoryManager {
    
    private CategoryDBManager DBM;

    /**
     * Constructor for the CategoryManager class.
     * 
     * @throws SQLException Exception, because it deals with the database
     * manager.
     * @throws IOException Exception, because the database manager uses a
     * configuration file to load it's settings.
     */
    public CategoryManager() throws SQLException, IOException {
        DBM = new CategoryDBManager();
    }
    
    /**
     * Gets all the Categories.
     * 
     * @return an ArrayList full of Categories.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public ArrayList<Category> getAllCategories() throws SQLException {
        return DBM.getAllCategories();
    }
    
    /**
     * Gets a single Category.
     * 
     * @param id the ID by which we select the Category from the database.
     * @return a single Category entity
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public Category getCategoryById(int id) throws SQLException {
        return DBM.getCategoryByID(id);
    }
    
    /**
     * Adds a new Category.
     * 
     * @param cat a Category entity we want to add to the database.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void addCategory(Category cat) throws SQLException {
        DBM.insertCategory(cat);
    }
    
    /**
     * Removes a specific Category.
     * @param id the ID of the Category we want to remove from the database.
     * @throws SQLException Exception, because it deals with the database
     * manager.
     */
    public void removeCategory(int id) throws SQLException {
        DBM.removeCategoryByID(id);
    }
}
