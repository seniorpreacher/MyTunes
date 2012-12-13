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
    private String categoryName;
    private Boolean isExists;

    /**
     * Constructor for the Song entity. It only has 5 parameters and we don't assign an Artist NAME and a Category NAME. We don't have an ID parameter, because we usually pass this entity to the DB and the DB sets the ID automatically.
     * 
     * @param title Title of the song.
     * @param artistId ID of the Artist that's assigned to it.
     * @param categoryId ID of the Category that's assigned to it.
     * @param fileName The filename with extension of the song if it exists locally.
     * @param duration The duration of the song in seconds.
     */
    public Song(String title, int artistId, int categoryId, String 
            fileName, int duration) {
        this.title = title;
        this.artistId = artistId;
        this.categoryId = categoryId;
        this.fileName = fileName;
        this.duration = duration;
    }

    /**
     * Constructor for the Song entity. It has an additional parameter, ID, which is set when we get data from the DB and create an entity out of it. It still doesn't have Artist and Category NAME added to it.
     * 
     * @param id ID, which we get from the DB.
     * @param title Title of the song.
     * @param artistId ID of the Artist that's assigned to it.
     * @param categoryId ID of the Category that's assigned to it.
     * @param fileName The filename with extension of the song if it exists locally.
     * @param duration The duration of the song in seconds.
     */
    public Song(int id, String title, int artistId, int categoryId, String 
            fileName, int duration) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.categoryId = categoryId;
        this.fileName = fileName;
        this.duration = duration;
    }
    
    /**
     * 
     * @param id ID, which we get from the DB.
     * @param title Title of the song.
     * @param artistId ID of the Artist that's assigned to it.
     * @param categoryId ID of the Category that's assigned to it.
     * @param fileName The filename with extension of the song if it exists locally.
     * @param duration The duration of the song in seconds.
     * @param artistName The name of the Artist assigned to the song, which we get from the Artist table.
     * @param categoryName The name of the Category assigned to the song, which we get from the Category table.
     */
      public Song(int id, String title, int artistId, int categoryId, String 
            fileName, int duration, String artistName, String categoryName) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.categoryId = categoryId;
        this.fileName = fileName;
        this.duration = duration;
        this.artistName = artistName;
        this.categoryName = categoryName;
    }
/**
 * Getter for the ID.
 * @return ID
 */
    public int getId() {
        return id;
    }
/**
 * Getter for the Title.
 * @return title
 */
    public String getTitle() {
        return title;
    }
/**
 * Getter for Artist ID.
 * @return artistId
 */
    public int getArtistId() {
        return artistId;
    }
/**
 * Getter for Category ID.
 * @return categoryId
 */
    public int getCategoryId() {
        return categoryId;
    }
/**
 * Getter for the Filename.
 * @return fileName
 */
    public String getFileName() {
        return fileName;
    }
/**
 * Getter for the duration.
 * @return duration
 */
    public int getDuration() {
        return duration;
    }
    /**
     * Getter for the Artist Name.
     * @return artistName
     */
    public String getArtistName() {
        return artistName;
    }
    /**
     * Getter for the Category Name.
     * @return categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }
    /**
     * Prints out the entity in a nicer way.
     * @return a string, in the format: ID. Title - Duration
     */
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

    /**
     * @return the isExists
     */
    public Boolean getIsExists() {
        return isExists;
    }

    /**
     * @param isExists the isExists to set
     */
    public void setIsExists(Boolean isExists) {
        this.isExists = isExists;
    }
}
