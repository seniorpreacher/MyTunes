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

public class Menu_1_1 extends Menu {

    public Menu_1_1() throws Exception {
        super(create(), "Manage/Song");
    }

    private static ArrayList<MenuItem> create() {
        ArrayList<MenuItem> items = new ArrayList<>();
        
        items.add(new MenuItem("List All", "l", new Callable<Menu_1_1>() {
            public Menu_1_1 call() throws SQLException, IOException, Exception {
                SongManager sm = new SongManager();
                ArrayList<Song> data = sm.getAllSongs();
                String[][] tableData = new String[data.size()][3];
                
                int[] tableLayout = {4, 15, 4};
                String[] tableHeader = {"ID", "Title", "Singer"};

                for (int i = 0; i < data.size(); i++) {
                    Song song = data.get(i);
                    tableData[i][0] = Integer.toString(song.getId());
                    tableData[i][1] = song.getTitle();
                    tableData[i][2] = Integer.toString(song.getArtistId());
                    
                }
                Table.draw(tableHeader, tableLayout, tableData);
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Search", "s", new Callable<Menu_1_1>() {
            public Menu_1_1 call() throws Exception {
                //searchSongs
                SongManager sm = new SongManager();
                ArrayList<Song> data = sm.searchSongs(Menu.getInput("Search string"));
                String[][] tableData = new String[data.size()][3];
                
                int[] tableLayout = {4, 15, 4};
                String[] tableHeader = {"ID", "Title", "Singer"};

                for (int i = 0; i < data.size(); i++) {
                    Song song = data.get(i);
                    tableData[i][0] = Integer.toString(song.getId());
                    tableData[i][1] = song.getTitle();
                    tableData[i][2] = Integer.toString(song.getArtistId());
                    
                }
                Table.draw(tableHeader, tableLayout, tableData);
                
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Add", "a", new Callable<Menu_1_1>() {
            public Menu_1_1 call() throws Exception {
                SongManager sm = new SongManager();
                Song toAdd = new Song(
                    0, 
                    Menu.getInput("Title"), 
                    Menu.getInputInt("Artist ID"), 
                    Menu.getInputInt("Category ID"), 
                    Menu.getInput("File location"), 
                    Menu.getInputInt("Duration")
                );
                try{
                    sm.addSong(toAdd);
                }
                catch(Exception e){
                    throw e;
                }
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Edit", "e", new Callable<Menu_1_1>() {
            public Menu_1_1 call() throws Exception {
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Remove", "r", new Callable<Menu_1_1>() {
            public Menu_1_1 call() throws Exception {
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Check all", "c", new Callable<Menu_1_1>() {
            public Menu_1_1 call() throws Exception {
                return new Menu_1_1();
            }
        }));

        return items;
    }
}
