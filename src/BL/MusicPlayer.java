package BL;

import BE.Song;
import UI.MyTunes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer implements Runnable {

    private Player player;
    private Song current;
    private Boolean isPaused;
    private ArrayList<Song> currentPlayList;

    public void stop() {
        if (player != null) {
            if (isPaused) {
                MyTunes.playerThread.resume();
            }
            player.close();
            MyTunes.playerThread.stop();
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
                    player.play();
                } catch (FileNotFoundException e) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, e);
                }
                //pausePosition = 0;
            }
        } catch (JavaLayerException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pause() {
        if (player != null) {
            MyTunes.playerThread.suspend();
            isPaused = true;
        }
    }

    public void resume() throws FileNotFoundException, JavaLayerException {
        if (isPaused) {
            MyTunes.playerThread.resume();
            isPaused = false;
        }
    }

    public Song getPlayed() {
        if (isPaused) {
            MyTunes.playerThread.resume();
            Song ret = isPlaying() ? current : null;
            MyTunes.playerThread.suspend();
            return ret;
        }
        return isPlaying() ? current : null;
    }

    public boolean isPlaying() {
        return player == null ? false : !player.isComplete();
    }

    public void setSong(Song song) throws FileNotFoundException, JavaLayerException {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(song);
        currentPlayList = songs;
        isPaused = false;
    }

    public void setSongs(ArrayList<Song> songs) throws FileNotFoundException, JavaLayerException {
        currentPlayList = songs;
        isPaused = false;
    }
}
