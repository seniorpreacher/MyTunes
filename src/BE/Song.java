package BE;

/**
 *
 * This is a song entity of the corresponding table from our database. We use it
 * to construct elements of that type.
 *
 * @author Anthony
 */
public class Song {

    private int id;
    private String title;
    private int artistId;
    private int categoryId;
    private String fileName;
    private int duration;

    public Song(int id, String title, int artistId, int categoryId, String 
            fileName, int duration) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.categoryId = categoryId;
        this.fileName = fileName;
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        return "";
    }
}
