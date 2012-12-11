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
    private String artistName;

    public Song(String title, int artistId, int categoryId, String 
            fileName, int duration) {
        this.title = title;
        this.artistId = artistId;
        this.categoryId = categoryId;
        this.fileName = fileName;
        this.duration = duration;
    }

    public Song(int id, String title, int artistId, int categoryId, String 
            fileName, int duration) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.categoryId = categoryId;
        this.fileName = fileName;
        this.duration = duration;
    }
    
      public Song(int id, String title, int artistId, int categoryId, String 
            fileName, int duration, String artistName) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.categoryId = categoryId;
        this.fileName = fileName;
        this.duration = duration;
        this.artistName = artistName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getFileName() {
        return fileName;
    }

    public int getDuration() {
        return duration;
    }
    
    public String getArtistName() {
        return artistName;
    }
    
    @Override
    public String toString() {
        return getId() + ". " + getTitle() + " - " + getDuration();
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param artistId the artistId to set
     */
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
