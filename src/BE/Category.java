package BE;

/**
 *
 * This is a category entity of the corresponding table from our database.
 * We use it to construct elements of that type.
 * 
 * @author Anthony
 */
public class Category {
    
    private int id;
    private String name;
    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
