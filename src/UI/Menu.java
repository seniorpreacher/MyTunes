package UI;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private ArrayList<MenuItem> items;
    //private String parent;

    public Menu(ArrayList<MenuItem> items) throws Exception {
        this.items = items;
        start();
        listen();
    }
    
    private void start(){
        newLine(5);
        draw();
    }

    private void draw() {
        /*if(!parent.equals("")){
            System.out.println("/" + parent + "/ ");
        }*/
        for (MenuItem i : items) {
            System.out.print(" | " + i.getText() + " (" + i.getTrigger() + ")");
        }
        System.out.println(" |\n");
        newLine(1);
    }

    private int listen() throws Exception {
        Scanner in = new Scanner(System.in);
        String userInput;
        boolean isCommandWrong = true;
        
        while (isCommandWrong) {
            System.out.print(" Command > ");
            userInput = in.nextLine();
            
            for (MenuItem i : items) {
                if(i.getTrigger().equalsIgnoreCase(userInput)){
                    if(i.getFunc().call() != null){
                        isCommandWrong = false;
                    }
                    else{
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    /**
     *
     * @param n
     */
    public static void newLine(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("\n");
        }
    }
}
