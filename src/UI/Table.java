package UI;

public class Table {

    public static void draw(String[] header, int[] layout, String[][] data) {
        int innerWidth = 0;
        for(int i : layout){
            innerWidth += i;
        }
        
        System.out.println(" ├──┬" + multiString("─", h(header, layout).length()) + "┐");
        System.out.println(" │  │ " + h(header, layout));
        System.out.println(" ├──┼" + multiString("─", h(header, layout).length()) + "┤");
        
        for (String[] row : data){
            System.out.print(" │  │ ");
            for(int i = 0; i < row.length; i++){
                System.out.print(" " + formatToLength(row[i], layout[i]) + " |");
            }
            System.out.println("");
        }
        
        System.out.println(" ├──┴" + multiString("─", h(header, layout).length()) + "┘");
    }

    private static String h(String[] header, int[] layout) {
        String ret = "";
        for (int i = 0; i < header.length; i++) {
            ret += " " + formatToLength(header[i], layout[i]) + " |";
        }
        return ret;
    }
    
    private static String multiString(String s, int n){
        String ret = "";
        for (int i = 0; i < n; i++) {
            ret += s;
        }
        return ret;
    }
    
    private static String formatToLength(String base, int length) {
        String ret = "";
        for (int i = base.length(); i <= length; i++) {
            ret += " ";
        }
        ret += base;

        if (base.length()-1 > length) {
            ret = base.substring(0, length - 1) + "..";
        }

        return ret;
    }
}
