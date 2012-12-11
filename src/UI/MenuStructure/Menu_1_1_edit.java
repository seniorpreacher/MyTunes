package UI.MenuStructure;

import BE.Song;
import BL.SongManager;
import UI.Table;
import UI.Menu;
import UI.MenuItem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Menu_1_1_edit extends Menu {

    public Menu_1_1_edit() throws Exception {
        super(create(), "Manage/Song/Edit");
    }

    private static ArrayList<MenuItem> create() {
        ArrayList<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("Title", "t", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws SQLException, IOException, Exception {
                int id = Menu.getInputInt("Song ID to edit");
                if(id <= 0){
                    return new Menu_1_1();
                }
                
                SongManager sm = new SongManager();
                Song song = sm.getSongById(id);
                
                song.setTitle(Menu.getInput("New title"));
                sm.updateSong(song);
                
                Table.fromSong(song);
                Menu.waitForEnter();
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Artist", "a", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws SQLException, IOException, Exception {
                int id = Menu.getInputInt("Song ID to edit");
                if(id <= 0){
                    return new Menu_1_1();
                }
                
                SongManager sm = new SongManager();
                Song song = sm.getSongById(id);
                
                song.setTitle(Menu.getInput("New artist ID"));
                sm.updateSong(song);
                
                Table.fromSong(song);
                
                Menu.waitForEnter();
                return new Menu_1_1();
            }
        }));
        
        return items;
    }
}
