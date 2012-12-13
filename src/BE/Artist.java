package BE;

/**
 * Artist class.
 *
 * This is an artist entity of the corresponding table from our database. We use
 * it to construct elements of that type.
 *
 * @author Anthony
 */
public class Artist {

    private int id;
    private String name;

    /**
     * The constructor or the Artist class.
     *
     * @param id the ID we get from the DB to create an entity.
     * @param name the name of the Artist.
     */
    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for the ID
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
     * Prints out the entity inn a nicer way.
     *
     * @return string, that consists of ID - NAME.
     */
    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
