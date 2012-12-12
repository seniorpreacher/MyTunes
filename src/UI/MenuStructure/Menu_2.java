package UI.MenuStructure;

import BE.Playlist;
import BE.Song;
import BL.MusicPlayer;
import BL.PlaylistManager;
import BL.SongManager;
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
            @Override
            public Menu_2 call() throws Exception {
                MyTunes.musicPlayer.stop();
                SongManager sm = new SongManager();

                String title;
                ArrayList<Song> songs;
                do {
                    title = Menu.getInput("Song title");
                    songs = sm.searchSongs(title);
                    if (songs.size() < 1) {
                        Menu.Message("No song to this search");
                    }
                    if (songs.size() > 1) {
                        Menu.Message("I found " + songs.size() + " songs to this search, that's too many to play.");
                    }
                } while (!(!title.equals("") && !title.isEmpty() && songs.size() == 1));
                if (songs.size() == 1) {
                    MyTunes.musicPlayer.setSong(songs.get(0));

                    Thread t = new Thread(MyTunes.musicPlayer);
                    t.start();
                }
                return new Menu_2();
            }
        }));

        items.add(new MenuItem("Start a PlayList", "l", new Callable<Menu_2>() {
            @Override
            public Menu_2 call() throws Exception {
                MyTunes.musicPlayer.stop();
                SongManager sm = new SongManager();
                PlaylistManager pm = new PlaylistManager();
                

                String title;
                ArrayList<Playlist> playlists;
                do {
                    title = Menu.getInput("Playlist name");
                    playlists = pm.getPlaylist(title);
                    if (playlists.size() < 1) {
                        Menu.Message("No playlist found");
                    }
                    if (playlists.size() > 1) {
                        Menu.Message("I found " + playlists.size() + " songs to this search, that's too many to play.");
                    }
                } while (!(!title.equals("") && !title.isEmpty() && playlists.size() == 1));
                if (playlists.size() == 1) {
                    MyTunes.musicPlayer.setSongs(playlists.get(0).getSongs());

                    Thread t = new Thread(MyTunes.musicPlayer);
                    t.start();
                }
                return new Menu_2();
            }
        }));

        items.add(new MenuItem("Stop", "s", new Callable<Menu_2>() {
            @Override
            public Menu_2 call() throws Exception {
                MyTunes.musicPlayer.stop();

                return new Menu_2();
            }
        }));

        items.add(new MenuItem("Pause", "a", new Callable<Menu_2>() {
            @Override
            public Menu_2 call() throws Exception {
                MyTunes.musicPlayer.pause();

                return new Menu_2();
            }
        }));

        items.add(new MenuItem("Resume", "r", new Callable<Menu_2>() {
            @Override
            public Menu_2 call() throws Exception {
                MyTunes.musicPlayer.resume();

                return new Menu_2();
            }
        }));

        items.add(new MenuItem("Info from current", "i", new Callable<Menu_2>() {
            @Override
            public Menu_2 call() throws Exception {
                Song current = MyTunes.musicPlayer.getPlayed();
                if (current != null) {
                    Menu.Message("Title: " + current.getTitle());
                    Menu.Message("Artist: " + current.getArtistName());
                } else {
                    Menu.Message("Currently we're not playing any songs");
                }

                return new Menu_2();
            }
        }));

        return items;
    }
}
