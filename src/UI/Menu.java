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

        if (lineLength > 20 && parent != null) {
            System.out.println(" │");
            System.out.println(" │");
            System.out.println(" ├───── ── ── ── ── ─ ─ ─ ─ - - - -");
            System.out.println(" ├─» " + parent);
        }
        System.out.print(" ├┬");
        for (MenuItem i : items) {
            for (int j = 0; j < lengthOfHeader(i); j++) {
                System.out.print("─");
            }
            System.out.print("┬");
        }
        System.out.print("┐\n ││");
        for (MenuItem item : items) {
            System.out.print(formatMenuItem(item) + "│");
        }
        System.out.print("│\n ├┴");
        for (MenuItem i : items) {
            for (int j = 0; j < lengthOfHeader(i); j++) {
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
            //System.out.print(" │ " + (parent != null && !parent.isEmpty() ? parent : "Command") + " > ");
            System.out.print(" │ Command > ");
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

    private int lengthOfHeader(MenuItem item) {
        String s = formatMenuItem(item);
        return s.length();
    }
    
    private String formatMenuItem(MenuItem item) {
        return " " + item.getText() + " (" + item.getTrigger() + ") ";
    }
    
    public static String getInput(String label){
        Scanner in = new Scanner(System.in);
        String userInput;
        System.out.print(" │ " + label + " > ");
        try{
            userInput = in.nextLine();
        }
        catch(Error e){
            throw new Error(e);
        }
        return userInput;
    }
    
    public static int getInputInt(String label){
        Scanner in = new Scanner(System.in);
        String userInput;
        int ret = 0;
        System.out.print(" │ " + label + " (Undo: 0) > ");
        try{
            userInput = in.nextLine();
        }
        catch(Error e){
            throw e;
        }
        
        if(userInput.equals("") || userInput.isEmpty()){
            return 0;
        }
        
        try{
            ret = Integer.parseInt(userInput);
        }
        catch(NumberFormatException e){
            throw e;
        }
        return ret;
    }
}