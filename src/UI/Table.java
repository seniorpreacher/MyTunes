package UI;

import BE.Playlist;
import BE.Song;
import java.util.ArrayList;

/**
 * This Class can be used to draw data tables. Every methods are static, so you
 * don't have to create an instance of this Class.
 *
 * @author Dani
 */
public class Table {

    /**
     * Draw a table of dataset. You need to have the same size for the
     * parameters.
     *
     * @param header Header texts.
     * @param layout Width of each column - 3 (margins, don't care about them :D
     * )
     * @param data 2-dimensional-array for the data to show. [rows][columns]
     */
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

    /**
     * Submethod to format the header text.
     *
     * @param header
     * @param layout
     * @return
     */
    private static String h(String[] header, int[] layout) {
        String ret = "";
        for (int i = 0; i < header.length; i++) {
            ret += " " + formatToLength(header[i], layout[i]) + " |";
        }
        return ret;
    }

    /**
     * Submethod to multiply a String.
     *
     * @param s What to multiply.
     * @param n How many times.
     * @return
     */
    private static String multiString(String s, int n) {
        String ret = "";
        for (int i = 0; i < n; i++) {
            ret += s;
        }
        return ret;
    }

    /**
     * This method can make a String longer with putting spaces before it and
     * make it shorter by cutting the end and putting '..' to the end to show
     * that is not the whole String
     *
     * @param base
     * @param length
     * @return
     */
    private static String formatToLength(String base, int length) {
        if (base == null) {
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

    /**
     * Helper method to draw a table from a Song object.
     *
     * @param song
     */
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

    /**
     * Helper method to draw a table from Song objects.
     *
     * @param song
     */
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

    /**
     * Helper method to draw a table from a Playlist object.
     *
     * @param song
     */
    public static void fromPlaylist(Playlist pl) {

        String[][] tableData = new String[1][2];

        int[] tableLayout = {4, 15, 25, 7};
        String[] tableHeader = {"ID", "Name"};

        tableData[0][0] = Integer.toString(pl.getId());
        tableData[0][1] = pl.getName();

        Table.draw(tableHeader, tableLayout, tableData);
    }

    /**
     * Helper method to draw a table from Playlist objects.
     *
     * @param song
     */
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