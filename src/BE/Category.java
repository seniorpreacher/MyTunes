package BE;

/**
 *
 * This is a category entity of the corresponding table from our database. We
 * use it to construct elements of that type.
 *
 * @author Anthony
 */
public class Category {

    private int id;
    private String name;

    /**
     * Constructor for the Category.
     *
     * @param id the ID that we get from the DB.
     * @param name the name of our category
     */
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for the ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Prints out the entity in a nicer way.
     *
     * @return a string, that consists of ID - Name
     */
    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
