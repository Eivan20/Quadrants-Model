
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Strings;
import org.postgresql.Driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class DBConnection {

    public Connection connection;
    public String connectionString;
    public String user;
    public String password;
    public String port;

    public DBConnection() {
        connection = null;

        try (InputStream input = new FileInputStream("Table_Method-Test/src/config.properties")) {
            Properties p = new Properties();
            p.load(input);
            connectionString = p.getProperty("db.url");
            user = p.getProperty("db.user");
            password = p.getProperty("db.password");
            port = p.getProperty("db.port");
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        } catch (IOException e) {
            System.out.println(e);
        }
        connect();
    }

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionString, user, password);
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("CLASS NOT FOUND");
        }
        return connection;
    }

    public ArrayList<Song> findSong(User user) {
        ArrayList<String> songIDs = new ArrayList<>();
        ArrayList<Song> songs = new ArrayList<>();
        String[] userGenres = Quadrants.getSubGenre(user.getCurrentQuadrant());
        double valence = user.getValencePreference();
        double energy = user.getEnergyPreference();

        Statement stmt;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select  uri, track_name ,artist_individual, energy,valence, artist_genre from final group by uri, track_name, artist_individual, energy, valence, artist_genre");
            while (rs.next()) {
                if (songIDs.contains(rs.getString("uri"))) {
                } else {
                    String genre = rs.getString("artist_genre");
                    for (String userGenre : userGenres) {
                        if (genre.contains(userGenre)) {
                            if (valence - 0.1 < rs.getDouble("valence")
                                    && rs.getDouble("valence") < valence + 0.1
                                    && energy - 0.1 < rs.getDouble("energy")
                                    && rs.getDouble("energy") < energy + 0.1) {
                                songs.add(new Song(rs.getString("track_name"), rs.getString("artist_individual")));
                                songIDs.add(rs.getString("uri"));
                            }
                            break;
                        }
                    }
                }
            }
        } catch (
                SQLException e) {
            System.out.println("No results were returned from the query");
            System.out.println(e);
        }
        return songs;
    }

    public ArrayList<Song> listener() {

        //int id = -1;
        boolean runs = false;
        String[] eachSong;
        int songID;
        ArrayList<Song> songs = new ArrayList<>();
        Statement queryAddedSongs;
        Statement queryFinal;

        while (true) {
            try {
                queryAddedSongs = connection.createStatement();
                ResultSet rsAddedSongs = queryAddedSongs.executeQuery("SELECT * FROM added_songs");
                while (rsAddedSongs.next()) {
                    //id = rsAddedSongs.getInt("id");
                    eachSong = rsAddedSongs.getString("songs").split(";");
                    for (String song : eachSong) {
                        songID = Integer.parseInt(song.substring(song.indexOf('_') + 1));
                        queryFinal = connection.createStatement();
                        String query = "select track_name ,artist_individual, energy,valence, artist_genre from final where id='" + songID + "' group by id, uri, track_name, artist_individual, energy, valence, artist_genre";
                        ResultSet rsFinal = queryFinal.executeQuery(query);
                        while (rsFinal.next()) {
                            songs.add(new Song(rsFinal.getString("track_name"), rsFinal.getString("artist_individual"), rsFinal.getString("artist_genre"), rsFinal.getDouble("valence"), rsFinal.getDouble("energy")));
                        }
                    }
                    runs = true;
                }
                if (runs) break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

//        if (id != -1) {
//            try {
//                id = -1;
//                queryAddedSongs.executeQuery("DELETE FROM added_songs ");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
        System.out.println("Songs read");
        return songs;
    }

    public void insertIntoRecommend(ArrayList<Song> songs) {
        try {
            Collections.shuffle(songs);
            ArrayList<String> songNames = new ArrayList<>();
            ArrayList<String> artistNames = new ArrayList<>();
            for (int i = 0; i < songs.size(); i++) {
                songNames.add(songs.get(i).getSongName());
                artistNames.add(songs.get(i).getArtist());
            }
            String[] strings1 = songNames.toArray(new String[songNames.size()]);
            Array songArr = connection.createArrayOf("VARCHAR", strings1);
            String[] strings2 = artistNames.toArray(new String[artistNames.size()]);
            Array artistArr = connection.createArrayOf("VARCHAR", strings2);
            PreparedStatement stmt = connection.prepareStatement("insert into suggested_songs(songs,artists,model,updated_at,created_at) values(?,?,?,now(),now());");
            stmt.setArray(1, songArr);
            stmt.setArray(2, artistArr);
            stmt.setString(3, songs.get(0).getModel());
            stmt.execute();
            System.out.println("Song's added to suggested_songs");

            //System.out.println(resultSet.getString("track_name"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
