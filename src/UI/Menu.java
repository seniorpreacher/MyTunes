package UI;

import UI.MenuStructure.Menu_main;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Menu {

    private ArrayList<MenuItem> items;
    private String parent;

    public Menu(ArrayList<MenuItem> items, String parent) throws Exception {
        //adding back
        ArrayList<MenuItem> list = new ArrayList<MenuItem>();
        if (parent == null) {
            list.add(new MenuItem("Quit", "q", new Callable<Menu>() {
                public Menu call() throws Exception {
                    newLine(10);
                    return null;
                }
            }));

        } else {
            list.add(new MenuItem("<- Back", "b", new Callable<Menu>() {
                public Menu call() throws Exception {
                    return new Menu_main();
                }
            }));
        }
        list.addAll(items);
        this.items = list;
        this.parent = parent;
        start();
        listen();
    }

    private void start() {
        //newLine(5);
        draw();
    }

    private void draw() {
        int lineLength = 2;
        for (MenuItem i : items) {
            lineLength += lengthOfHeader(i);
        }
        
        System.out.print(" ┌┬");
        for (MenuItem i : items) {
            for(int j = 0; j < lengthOfHeader(i); j++){
                System.out.print("─");
            }
            System.out.print("┬");
        }
        System.out.println("┐");
        
        
        
        System.out.print(" ││");
        for (MenuItem i : items) {
            System.out.print(" " + i.getText() + " (" + i.getTrigger() + ") │");
        }
        System.out.println("│");
        
        
        System.out.print(" └┴");
        for (MenuItem i : items) {
            for(int j = 0; j < lengthOfHeader(i); j++){
                System.out.print("─");
            }
            System.out.print("┴");
        }
        System.out.println("┘");
    }

    private void listen() throws Exception {
        Scanner in = new Scanner(System.in);
        String userInput;
        boolean isCommandWrong = true;

        while (isCommandWrong) {
            System.out.print(" " + (parent != null && !parent.isEmpty() ? parent : "Command") + " > ");
            userInput = in.nextLine();

            for (MenuItem i : items) {
                if (i.getTrigger().equalsIgnoreCase(userInput)) {
                    isCommandWrong = false;
                    
                    i.getFunc().call();
                    break;
                }
            }
        }
        
        
    }

    /**
     *
     * @param n
     */
    public static void newLine(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("\n");
        }
    }

    public static String formatToLength(String base, int length) {
        String ret = "";
        for (int i = base.length(); i <= length; i++) {
            ret += " ";
        }
        ret += base;
        
        if (base.length() > length) {
            ret = base.substring(0, length-1) + "..";
        }

        return ret;
    }
    
    private static int lengthOfHeader(MenuItem item){
        String s = " " + item.getText() + " (" + item.getTrigger() + ") ";
        return s.length();
    };
}
