package UI.MenuStructure;

import BL.MusicPlayer;
import UI.Menu;
import UI.MenuItem;
import UI.MyTunes;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Menu_2 extends Menu {

    public Menu_2() throws Exception {
        super(create(), "Control");
    }

    private static ArrayList<MenuItem> create() {
        ArrayList<MenuItem> items = new ArrayList<>();
        
        items.add(new MenuItem("Play a song", "p", new Callable<Menu_2>(){
            public Menu_2 call() throws Exception{
                MyTunes.musicPlayer.setSong("songs/Heroes - Opening Theme.mp3");
                Thread t = new Thread(MyTunes.musicPlayer);
                t.start();
        
                return new Menu_2();
            }
        }));
        
        items.add(new MenuItem("Stop", "s", new Callable<Menu_2>(){
            public Menu_2 call() throws Exception{
                MyTunes.musicPlayer.stop();
                
                return new Menu_2();
            }
        }));
        
        return items;
    }
}
