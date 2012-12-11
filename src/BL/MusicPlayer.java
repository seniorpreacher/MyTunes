package BL;

import BE.Song;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer implements Runnable {

    private Player player;
    private Song current;
    private ArrayList<Song> currentPlayList;

    public void stop() {
        if (player != null) {
            player.close();
        }
    }

    @Override
    public void run() {
        try {
            player.play();
        } catch (JavaLayerException ex) {
        }
    }
    
    public Song getPlayed(){
        return isPlaying() ? current : null;
    }
    
    public boolean isPlaying(){
        return player != null ? true : false;
    }

    public void setSong(Song song) throws FileNotFoundException, JavaLayerException {
        current = song;
        FileInputStream fis = new FileInputStream("songs/" + song.getFileName());
        player = new Player(fis);
    }

    public void setSongs(ArrayList<Song> songs) throws FileNotFoundException, JavaLayerException {
        FileInputStream fis = new FileInputStream("songs/" + songs.get(0));
        player = new Player(fis);
    }
}
