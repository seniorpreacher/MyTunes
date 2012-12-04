package BE;

/**
 * Artist class.
 * 
 * This is an artist entity of the corresponding table from our database.
 * We use it to construct elements of that type.
 * 
 * @author Anthony
 */
public class Artist {
    
    private int id;
    private String name;
    
    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "";
    }
}
