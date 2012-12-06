package UI.MenuStructure;

import BE.Song;
import BL.SongManager;
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
                int[] tableLayout = {3, 15};

                for (Song item : sm.getAllSongs()) {
                    String out = "";
                    out += "|" + formatToLength(Integer.toString(item.getId()), tableLayout[0]) + "|";
                    out += "|" + formatToLength(item.getTitle(), tableLayout[1]) + "|";
                    System.out.println(out);
                }
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Playlist", "p", new Callable<Integer>() {
            public Integer call() {
                System.out.println("asd");
                return null;
            }
        }));

        return items;
    }
}
