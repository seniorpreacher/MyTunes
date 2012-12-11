package UI;

import BE.Playlist;
import BE.Song;
import java.util.ArrayList;

public class Table {

    public static void draw(String[] header, int[] layout, String[][] data) {
        int innerWidth = 0;
        for (int i : layout) {
            innerWidth += i;
        }

        System.out.println(" ├──┬" + multiString("─", h(header, layout).length()) + "┐");
        System.out.println(" │  │ " + h(header, layout));
        System.out.println(" ├──┼" + multiString("─", h(header, layout).length()) + "┤");

        for (String[] row : data) {
            System.out.print(" │  │ ");
            for (int i = 0; i < row.length; i++) {
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

    private static String multiString(String s, int n) {
        String ret = "";
        for (int i = 0; i < n; i++) {
            ret += s;
        }
        return ret;
    }

    private static String formatToLength(String base, int length) {
        if(base == null) {
            throw new NullPointerException();
        }
        
        String ret = "";
        for (int i = base.length(); i <= length; i++) {
            ret += " ";
        }
        ret += base;

        if (base.length() - 1 > length) {
            ret = base.substring(0, length - 1) + "..";
        }

        return ret;
    }

    public static void fromSong(Song song) {
        String[][] tableData = new String[1][4];

        int[] tableLayout = {4, 15, 25, 7};
        String[] tableHeader = {"ID", "Title", "Artist", "Duration"};

        tableData[0][0] = Integer.toString(song.getId());
        tableData[0][1] = song.getTitle();
        tableData[0][2] = song.getArtistName();
        tableData[0][3] = Integer.toString(song.getDuration());

        Table.draw(tableHeader, tableLayout, tableData);
    }

    public static void fromSong(ArrayList<Song> data) {

        String[][] tableData = new String[data.size()][4];

        int[] tableLayout = {4, 15, 25, 7};
        String[] tableHeader = {"ID", "Title", "Artist", "Duration"};

        for (int i = 0; i < data.size(); i++) {
            Song song = data.get(i);
            tableData[i][0] = Integer.toString(song.getId());
            tableData[i][1] = song.getTitle();
            tableData[i][2] = song.getArtistName();
            tableData[i][3] = Integer.toString(song.getDuration());

        }
        Table.draw(tableHeader, tableLayout, tableData);
    }

    public static void fromPlaylist(Playlist pl) {

        String[][] tableData = new String[1][2];

        int[] tableLayout = {4, 15, 25, 7};
        String[] tableHeader = {"ID", "Name"};

        tableData[0][0] = Integer.toString(pl.getId());
        tableData[0][1] = pl.getName();
        
        Table.draw(tableHeader, tableLayout, tableData);
    }

    public static void fromPlaylist(ArrayList<Playlist> data) {

        String[][] tableData = new String[data.size()][2];

        int[] tableLayout = {4, 15, 25, 7};
        String[] tableHeader = {"ID", "Name"};

        for (int i = 0; i < data.size(); i++) {
            Playlist pl = data.get(i);
            tableData[i][0] = Integer.toString(pl.getId());
            tableData[i][1] = pl.getName();
        }
        
        Table.draw(tableHeader, tableLayout, tableData);
    }
}