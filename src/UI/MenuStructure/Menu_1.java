package UI.MenuStructure;

import UI.Menu;
import UI.MenuItem;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Menu_1 extends Menu {

    public Menu_1() throws Exception {
        super(create());
    }

    private static ArrayList<MenuItem> create() {
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Back", "b", new Callable<Menu>(){
            public Menu call() throws Exception{
                return null;
            }
        }));
        
        items.add(new MenuItem("Song", "s", new Callable<Menu_1_1>(){
            public Menu_1_1 call() throws Exception{
                return new Menu_1_1();
            }
        }));
        
        items.add(new MenuItem("Playlist", "p", new Callable<Integer>(){
            public Integer call(){
                System.out.println("asd");
                return null;
            }
        }));
        
        return items;
    }
}
