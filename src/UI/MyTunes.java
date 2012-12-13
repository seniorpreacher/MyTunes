package UI;

import BL.MusicPlayer;
import UI.MenuStructure.Menu_main;

public class MyTunes {
    public static MusicPlayer musicPlayer;
    public static Thread playerThread;
    
    public static void main(String[] args) throws Exception {
        musicPlayer = new MusicPlayer();
        System.out.println(" ┌──── ── ── ─ ─ - -");
        System.out.println(" │ Welcome to MyTunes");
        System.out.println(" ├──── ── ── ─ ─ - -");
        Menu_main menu_main = new Menu_main();
        System.out.println(" ├──── ── ── ─ ─ - -");
        System.out.println(" │");
        System.out.println(" │ See you later!");
        System.out.println(" └── ── ─ -");
    }
}
