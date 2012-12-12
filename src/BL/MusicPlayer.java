package BL;

import BE.Song;
import UI.Menu;
import UI.MyTunes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.Control;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer implements Runnable {

    private Player player;
    private Song current;
    private int pausePosition = 0;
    private ArrayList<Song> currentPlayList;

    public void stop() {
        if (player != null) {
            player.close();
            pausePosition = 0;
            current = null;
        }
    }

    @Override
    public void run() {
        try {
            for (Song song : currentPlayList) {
                current = song;
                try {
                    FileInputStream fis = new FileInputStream("songs/" + song.getFileName());
                    player = new Player(fis);
                    while (player.play(pausePosition++)) {
                        //Menu.Message(Integer.toString(pausePosition));
                    }
                } catch (FileNotFoundException e) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } catch (JavaLayerException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pause() {
        if (player != null) {
            //pausePosition = player.getPosition();
            player.close();
        }
    }

    public void resume() throws FileNotFoundException, JavaLayerException {
        if (current != null) {
            MyTunes.musicPlayer.setSong(current);
        }
    }

    public Song getPlayed() {
        return isPlaying() ? current : null;
    }

    public boolean isPlaying() {
        return !player.isComplete();
    }

    public void setSong(Song song) throws FileNotFoundException, JavaLayerException {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(song);
        currentPlayList = songs;
    }

    public void setSongs(ArrayList<Song> songs) throws FileNotFoundException, JavaLayerException {
        currentPlayList = songs;
    }
}
