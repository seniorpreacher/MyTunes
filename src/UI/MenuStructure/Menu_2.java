package UI.MenuStructure;

import BE.Song;
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

        items.add(new MenuItem("Play a Song", "p", new Callable<Menu_2>() {
            public Menu_2 call() throws Exception {
                MyTunes.musicPlayer.stop();
                MyTunes.musicPlayer.setSong(new Song("title", 1,1,"Heroes - Opening Theme.mp3",20));

                Thread t = new Thread(MyTunes.musicPlayer);
                t.start();

                return new Menu_2();
            }
        }));

        items.add(new MenuItem("Start a PlayList", "l", new Callable<Menu_2>() {
            public Menu_2 call() throws Exception {
                MyTunes.musicPlayer.stop();
                
                ArrayList<String> songs = new ArrayList<>();
                songs.add("songs/Heroes - Opening Theme.mp3");
                songs.add("songs/Heroes - Opening Theme.mp3");
                //MyTunes.musicPlayer.setSongs(songs);

                Thread t = new Thread(MyTunes.musicPlayer);
                t.start();

                return new Menu_2();
            }
        }));

        items.add(new MenuItem("Stop", "s", new Callable<Menu_2>() {
            public Menu_2 call() throws Exception {
                MyTunes.musicPlayer.stop();

                return new Menu_2();
            }
        }));

        items.add(new MenuItem("Info from current", "i", new Callable<Menu_2>() {
            public Menu_2 call() throws Exception {
                Song current = MyTunes.musicPlayer.getPlayed();
                if(current != null){
                    Menu.Message("Title: " + current.getTitle());
                    Menu.Message("Artist: " + current.getArtistName());
                }else{
                    Menu.Message("Currently we're not playing any songs");
                }

                return new Menu_2();
            }
        }));

        return items;
    }
}
