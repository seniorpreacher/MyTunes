package UI.MenuStructure;

import BE.Playlist;
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

public class Menu_1_2 extends Menu {

    public Menu_1_2() throws Exception {
        super(create(), "Manage/Playlist");
    }

    private static ArrayList<MenuItem> create() {
        ArrayList<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("List All", "l", new Callable<Menu_1_2>() {
            @Override
            public Menu_1_2 call() throws SQLException, IOException, Exception {
                PlaylistManager pm = new PlaylistManager();
                ArrayList<Playlist> data = pm.getAllPlaylists();
                
                Table.fromPlaylist(data);
                Menu.waitForEnter();
                return new Menu_1_2();
            }
        }));
        items.add(new MenuItem("View songs", "v", new Callable<Menu_1_2>() {
            @Override
            public Menu_1_2 call() throws SQLException, IOException, Exception {
                SongManager sm = new SongManager();
                ArrayList<Song> data = sm.getSongsFromPlaylist(Menu.getInputInt("Playlist ID"));
                
                Table.fromSong(data);
                Menu.waitForEnter();
                return new Menu_1_2();
            }
        }));

        items.add(new MenuItem("Add", "a", new Callable<Menu_1_2>() {
            @Override
            public Menu_1_2 call() throws Exception {
                PlaylistManager pm = new PlaylistManager();
                Playlist toAdd = new Playlist(Menu.getInput("Name"));
                try {
                    pm.addPlaylist(toAdd);
                } catch (Exception e) {
                    throw e;
                }
                Menu.waitWithMessage("Playlist created");
                return new Menu_1_2();
            }
        }));

        items.add(new MenuItem("Remove", "r", new Callable<Menu_1_2>() {
            @Override
            public Menu_1_2 call() throws Exception {
                PlaylistManager pm = new PlaylistManager();
                int id = Menu.getInputInt("Playlist ID to remove");
                if (id > 0) {
                    pm.removePlaylist(id);
                }
                Menu.waitWithMessage("Playlist removed");
                return new Menu_1_2();
            }
        }));

        items.add(new MenuItem("Reorder", "o", new Callable<Menu_1_2>() {
            @Override
            public Menu_1_2 call() throws Exception {
                PlaylistManager pm = new PlaylistManager();
                int playlistId = Menu.getInputInt("Playlist ID");
                int songId = Menu.getInputInt("Sond ID");
                String direction = Menu.getInput("Direction (u -> up, d -> down)");
                
                if(!direction.equalsIgnoreCase("u") && !direction.equalsIgnoreCase("d")){
                    return new Menu_1_2();
                }
                
                int newPos = direction.equalsIgnoreCase("u") ? -1 : 1;
                
                pm.reorderPlaylist(playlistId, songId, newPos);
                
                Menu.waitWithMessage("Song reodered");
                
                return new Menu_1_2();
            }
        }));

        return items;
    }
}
