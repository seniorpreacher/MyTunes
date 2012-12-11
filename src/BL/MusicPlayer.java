package BL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer implements Runnable{
    private Player player;

    public void stop() {
        player.close();
    }
    
    @Override
    public void run() {
        try {
            player.play();
        } catch (JavaLayerException ex) {
            
        }
    }
    public void setSong(String path)throws FileNotFoundException, JavaLayerException {
        FileInputStream fis = new FileInputStream(path);
        player = new Player(fis);
    }
}
