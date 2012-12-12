package UI.MenuStructure;

import BE.Song;
import BL.PlaylistManager;
import BL.SongManager;
import UI.Table;
import UI.Menu;
import UI.MenuItem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Menu_1_1 extends Menu {

    public Menu_1_1() throws Exception {
        super(create(), "Manage/Song");
    }

    private static ArrayList<MenuItem> create() {
        ArrayList<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("List All", "l", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws SQLException, IOException, Exception {
                SongManager sm = new SongManager();
                ArrayList<Song> data = sm.getAllSongs();
                
                Table.fromSong(data);
                Menu.waitForEnter();
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Search", "s", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                SongManager sm = new SongManager();
                ArrayList<Song> data = sm.searchSongs(Menu.getInput("Search string"));
                
                Table.fromSong(data);
                Menu.waitForEnter();
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Add", "a", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                SongManager sm = new SongManager();
                
                String title = Menu.getInput("Title");
                if(title.equalsIgnoreCase("") || title.isEmpty()){
                    return new Menu_1_1();
                }
                int artistId = Menu.getInputInt("Artist ID");
                if(artistId < 1){
                    return new Menu_1_1();
                }
                int categoryId = Menu.getInputInt("Category ID");
                if(categoryId < 1){
                    return new Menu_1_1();
                }
                String location = Menu.getInput("File location");
                if(location.equalsIgnoreCase("") || location.isEmpty()){
                    return new Menu_1_1();
                }
                int duration = Menu.getInputInt("Duration");
                if(duration < 1){
                    return new Menu_1_1();
                }
                
                Song toAdd = new Song(title, artistId, categoryId, location, duration);
                try {
                    sm.addSong(toAdd);
                } catch (Exception e) {
                    throw e;
                }
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Edit", "e", new Callable<Menu_1_1_edit>() {
            @Override
            public Menu_1_1_edit call() throws Exception {
                return new Menu_1_1_edit();
            }
        }));

        items.add(new MenuItem("Remove", "r", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                SongManager sm = new SongManager();
                int id = Menu.getInputInt("Song ID to remove");
                if (id > 0) {
                    sm.removeSong(id);
                    Menu.waitWithMessage("Song removed");
                }
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Add song to playlist", "p", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                PlaylistManager pm = new PlaylistManager();
                int songId = Menu.getInputInt("Song ID to add");
                int playlistId = Menu.getInputInt("Playlist ID");
                if (songId > 0 && playlistId > 0) {
                    pm.insertSongToPlaylist(playlistId, songId);
                    Menu.waitWithMessage("Song added to playlist");
                }
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Check all", "c", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                return new Menu_1_1();
            }
        }));

        return items;
    }
}
