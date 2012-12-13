package BL;

import BE.Song;
import UI.MyTunes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Music player class.
 *
 * @author Daniel
 */
public class MusicPlayer implements Runnable {

    private Player player;
    private Song current;
    private Boolean isPaused;
    private ArrayList<Song> currentPlayList;

    /**
     * The play method which is the most important part of it.
     *
     * Note: We had to catch the exceptions because the JLayer didn't let us to
     * throw it up to the UI and the catch it.
     */
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
                    System.out.println(e.getMessage());
                }
            }
        } catch (JavaLayerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * The stop method that stops the current song.
     *
     * In case the song is paused first it restarts it thread, because otherwise
     * it doesn't work.
     */
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

    /**
     * The pause function.
     *
     * Suspends the song-playing thread and sets the isPaused variable to True.
     */
    public void pause() {
        if (player != null) {
            MyTunes.playerThread.suspend();
            isPaused = true;
        }
    }

    /**
     * The pause function.
     *
     * Re-enables the thread and sets the isPaused variable to False.
     */
    public void resume() {
        if (isPaused) {
            MyTunes.playerThread.resume();
            isPaused = false;
        }
    }

    /**
     * Gets back the song currently in play.
     *
     * Checks if whether the player(thread) is paused or not. If yes, it
     * re-enables the thread and stores the song currently in play into a
     * variable, if no, then it just return the song.
     *
     * @return ret The song currently in play or null.
     */
    public Song getPlayed() {
        if (isPaused) {
            MyTunes.playerThread.resume();
            Song ret = isPlaying() ? current : null;
            MyTunes.playerThread.suspend();
            return ret;
        }
        return isPlaying() ? current : null;
    }

    /**
     * Is the player playing something or not.
     *
     * A simple method that return a boolean for the state of the player.
     *
     * @return True in case the player is currently playing or False if the
     * player doesn't exist.
     */
    public boolean isPlaying() {
        return player == null ? false : !player.isComplete();
    }

    /**
     * Sets a song into the temporary playlist.
     *
     * This method is used when we only play a single song instead of a
     * playlist, and that way it puts that song into a temporary playlist which
     * gets played afterwards.
     *
     * @param song the song we want to set into the temp playlist.
     */
    public void setSong(Song song) throws FileNotFoundException, JavaLayerException {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(song);
        currentPlayList = songs;
        isPaused = false;
    }

    /**
     * Sets multiple songs into a temporary playlist.
     *
     * This method is used when we want to play a playlist. Then it takes the
     * playlist and sets it into a temporary one which we use to play in our
     * player.
     *
     * @param songs
     */
    public void setSongs(ArrayList<Song> songs) throws FileNotFoundException, JavaLayerException {
        currentPlayList = songs;
        isPaused = false;
    }
}
