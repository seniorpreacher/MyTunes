package UI.MenuStructure;

import BE.Artist;
import BE.Category;
import BE.Song;
import BL.ArtistManager;
import BL.CategoryManager;
import BL.PlaylistManager;
import BL.SongManager;
import UI.Menu;
import UI.MenuItem;
import UI.Table;
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
            @Override
            public Menu_1_1 call() throws SQLException, IOException, Exception {
                SongManager sm = new SongManager();
                ArrayList<Song> data = sm.getAllSongs();

                Table.fromSong(data);
                Menu.waitForEnter();
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Search", "s", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                SongManager sm = new SongManager();
                ArrayList<Song> data = sm.searchSongs(Menu.getInput("Search string"));

                Table.fromSong(data);
                Menu.waitForEnter();
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Add", "a", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                SongManager sm = new SongManager();

                String title = Menu.getInput("Title");
                if (title.equalsIgnoreCase("") || title.isEmpty()) {
                    return new Menu_1_1();
                }

                ArtistManager am = new ArtistManager();
                ArrayList<Artist> artists = am.getAllArtists();
                System.out.print(" │ Artists: ");
                for (Artist a : artists) {
                    System.out.print("(" + Integer.toString(a.getId()) + ")" + a.getName() + ", ");

                }
                System.out.print("\n");
                int artistId = Menu.getInputInt("Artist ID");
                if (artistId < 1) {
                    return new Menu_1_1();
                }
                CategoryManager cm = new CategoryManager();
                ArrayList<Category> categories = cm.getAllCategories();
                System.out.print(" │ Categories: ");
                for (Category c : categories) {
                    System.out.print("(" + Integer.toString(c.getId()) + ")" + c.getName() + ", ");

                }
                System.out.print("\n");
                int categoryId = Menu.getInputInt("Category ID");
                if (categoryId < 1) {
                    return new Menu_1_1();
                }
                String location = Menu.getInput("File location");
                if (location.equalsIgnoreCase("") || location.isEmpty()) {
                    return new Menu_1_1();
                }
                int duration = Menu.getInputInt("Duration");
                if (duration < 1) {
                    return new Menu_1_1();
                }

                Song toAdd = new Song(title, artistId, categoryId, location, duration);
                try {
                    sm.addSong(toAdd);
                } catch (Exception e) {
                    throw e;
                }
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Edit", "e", new Callable<Menu_1_1_edit>() {
            @Override
            public Menu_1_1_edit call() throws Exception {
                return new Menu_1_1_edit();
            }
        }));

        items.add(new MenuItem("Remove", "r", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                SongManager sm = new SongManager();
                int id = Menu.getInputInt("Song ID to remove");
                if (id > 0) {
                    sm.removeSong(id);
                    Menu.waitWithMessage("Song removed");
                }
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Add song to playlist", "p", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                PlaylistManager pm = new PlaylistManager();
                int songId = Menu.getInputInt("Song ID to add");
                int playlistId = Menu.getInputInt("Playlist ID");
                if (songId > 0 && playlistId > 0) {
                    pm.insertSongToPlaylist(playlistId, songId);
                    Menu.waitWithMessage("Song added to playlist");
                }
                return new Menu_1_1();
            }
        }));

        items.add(new MenuItem("Check all", "c", new Callable<Menu_1_1>() {
            @Override
            public Menu_1_1 call() throws Exception {
                SongManager sm = new SongManager();
                ArrayList<Song> data;
                data = sm.checkedSongs();

                String[][] tableData = new String[data.size()][5];

                int[] tableLayout = {4, 15, 25, 7, 4};
                String[] tableHeader = {"ID", "Title", "Artist", "Duration", "Check"};

                for (int i = 0; i < data.size(); i++) {
                    Song song = data.get(i);
                    tableData[i][0] = Integer.toString(song.getId());
                    tableData[i][1] = song.getTitle();
                    tableData[i][2] = song.getArtistName();
                    tableData[i][3] = Integer.toString(song.getDuration());
                    tableData[i][4] = song.getIsExists() ? "+  " : "-  ";

                }
                Table.draw(tableHeader, tableLayout, tableData);
                return new Menu_1_1();
            }
        }));

        return items;
    }
}
