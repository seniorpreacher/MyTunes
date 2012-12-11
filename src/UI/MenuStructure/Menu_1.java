package UI.MenuStructure;

import UI.Menu;
import UI.MenuItem;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Menu_1 extends Menu {

    public Menu_1() throws Exception {
        super(create(), "Manage");
    }

    private static ArrayList<MenuItem> create() {
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Song", "s", new Callable<Menu_1_1>(){
            public Menu_1_1 call() throws Exception{
                return new Menu_1_1();
            }
        }));
        
        items.add(new MenuItem("Playlist", "p", new Callable<Menu_1_2>(){
            public Menu_1_2 call() throws Exception{
                return new Menu_1_2();
            }
        }));
        
        return items;
    }
}
