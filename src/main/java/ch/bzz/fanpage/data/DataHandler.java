package ch.bzz.fanpage.data;

import ch.bzz.fanpage.model.Album;
import ch.bzz.fanpage.model.Artist;
import ch.bzz.fanpage.model.Song;
import ch.bzz.fanpage.model.User;
import ch.bzz.fanpage.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Album> albumList;
    private List<Song> songList;
    private List<Artist> artistList;
    private List<User> userList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setUserList(new ArrayList<>());
        readUserJSON();
        setAlbumList(new ArrayList<>());
        readAlbumsJSON();
        setSongList(new ArrayList<>());
        readSongJSON();
        setArtistList(new ArrayList<>());
        readArtistJSON();
    }

    public void initLists() {
        DataHandler.getInstance().setAlbumList(null);
        DataHandler.getInstance().setSongList(null);
        DataHandler.getInstance().setArtistList(null);
    }

    /**
     * one instance only
     *
     * @return instance
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /**
     * reads all albums
     * @return list of albums
     */
    public List<Album> readAllAlbums() {
        return getAlbumList();
    }

    /**
     * reads a album by its uuid
     *
     * @param albumUUID
     * @return the album (null = not found)
     */
    public Album readAlbumByUUID(String albumUUID) {
        Album album = null;
        for (Album a : getAlbumList()) {
            if (a.getAlbumUUID().equals(albumUUID)) {
                album = a;
            }
        }
        return album;
    }

    /**
     * inserts a new album into the albumList
     *
     * @param album the album to be saved
     */
    public void insertAlbum(Album album){
        getAlbumList().add(album);
        writeAlbumJSON();
    }

    /**
     * deletes a album identified by the albumUUID
     * @param albumUUID  the key
     * @return  success=true/false
     */
    public boolean deleteAlbum(String albumUUID) {
        Album album = getInstance().readAlbumByUUID(albumUUID);
        if (album != null) {
            getAlbumList().remove(album);
            writeAlbumJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * updates the albumList
     */
    public void updateAlbum() {
        writeAlbumJSON();
    }

    /**
     * reads all songs
     * @return list of songs
     */
    public List<Song> readAllSongs() {
        return getSongList();
    }

    /**
     * reads a album by its uuid
     *
     * @param songUUID
     * @return the song (null = not found)
     */
    public Song readSongByUUID(String songUUID) {
        Song song = null;
        for (Song l : getSongList()) {
            if (l.getSongUUID().equals(songUUID)) {
                song = l;
            }
        }
        return song;
    }

    /**
     * inserts a new song into the songList
     *
     * @param song the song to be saved
     */
    public void insertSong(Song song){
        getSongList().add(song);
        writeSongJSON();
    }

    /**
     * deletes a song identified by the songUUID
     * @param songUUID  the key
     * @return  success=true/false
     */
    public boolean deleteSong(String songUUID) {
        Song song = getInstance().readSongByUUID(songUUID);
        if (song != null) {
            getSongList().remove(song);
            writeSongJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * updates the songList
     */
    public void updateSong() {
        writeSongJSON();
    }

    /**
     * reads all artists
     * @return list of artists
     */
    public List<Artist> readAllArtists() {
        return getArtistList();
    }

    /**
     * reads a artist by its uuid
     * @param artistUUID
     * @return the artist (null = not found)
     */
    public Artist readArtistByUUID(String artistUUID) {
        Artist artist = null;
        for (Artist k : getArtistList()) {
            if (k.getArtistUUID().equals(artistUUID)) {
                artist = k;
            }
        }
        return artist;
    }

    /**
     * inserts a new artist into the artistList
     *
     * @param artist the artist to be saved
     */
    public void insertArtist(Artist artist){
        getArtistList().add(artist);
        writeArtistJSON();
    }

    /**
     * deletes a artist identified by the artistUUID
     * @param artistUUID  the key
     * @return  success=true/false
     */
    public boolean deleteArtist(String artistUUID) {
        Artist artist = getInstance().readArtistByUUID(artistUUID);
        if (artist != null) {
            getArtistList().remove(artist);
            writeArtistJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * updates the artistList
     */
    public void updateArtist() {
        writeArtistJSON();
    }

    /**
     * reads the albums from the JSON-file
     */
    private void readAlbumsJSON() {
        try {
            String path = Config.getProperty("albumJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Album[] albums = objectMapper.readValue(jsonData, Album[].class);
            for (Album a : albums) {
                getAlbumList().add(a);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the songs from the JSON-file
     */
    private void readSongJSON() {
        try {
            String path = Config.getProperty("songJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Song[] songs = objectMapper.readValue(jsonData, Song[].class);
            for (Song l : songs) {
                getSongList().add(l);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the artist from the JSON-file
     */
    private void readArtistJSON() {
        try {
            String path = Config.getProperty("artistJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Artist[] artists = objectMapper.readValue(jsonData, Artist[].class);
            for (Artist k : artists) {
                getArtistList().add(k);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the songList to the JSON-file
     */
    private void writeSongJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String songPath = Config.getProperty("songJSON");
        try {
            fileOutputStream = new FileOutputStream(songPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getSongList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the albumList to the JSON-file
     */
    private void writeAlbumJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String albumPath = Config.getProperty("albumJSON");
        try {
            fileOutputStream = new FileOutputStream(albumPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getAlbumList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the artistList to the JSON-file
     */
    private void writeArtistJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String artistPath = Config.getProperty("artistJSON");
        try {
            fileOutputStream = new FileOutputStream(artistPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getArtistList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads a user by the username/password provided
     *
     * @param username
     * @param password
     * @return user-object
     */
    public User readUserLogin(String username, String password) {
        User user = new User();
        for (User entry : getUserList()) {
            if (entry.getUserName().equals(username) &&
                    entry.getPassword().equals(password)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * reads the Users from the JSON-file
     */
    private void readUserJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("userJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the userList to the JSON-file
     */
    private void writeUserJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String pathToJson = Config.getProperty("userJSON");
        try {
            fileOutputStream = new FileOutputStream(pathToJson);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getUserList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads a User by its uuid
     *
     * @param userUUID
     * @return the User
     *
     */
    public User readUserByUUID(String userUUID) {
        User user = null;
        for (User entry : getUserList()) {
            if (entry.getUserUUID().equals(userUUID)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * inserts a new user into the userList
     *
     * @param user the user to be saved
     *
     */
    public void insertUser(User user) {
        getUserList().add(user);
        writeUserJSON();
    }

    /**
     * updates the userList
     */
    public void updateUser() {
        writeUserJSON();
    }

    /**
     * deletes a user identified by the UUID
     *
     * @param userUUID the key
     * @return success = true/false
     */
    public boolean deleteUser(String userUUID) {
        User user = readUserByUUID(userUUID);
        if (user != null) {
            getUserList().remove(user);
            writeUserJSON();
            return true;
        }
        return false;
    }

    /**
     * sets instance
     *
     * @param instance the value to set
     */
    public static void setInstance(DataHandler instance) {
        DataHandler.instance = instance;
    }

    /**
     * gets albumList
     *
     * @return albumList value of albumList
     */
    public List<Album> getAlbumList() {
        if (albumList == null) {
            setAlbumList(new ArrayList<>());
            readAlbumsJSON();
        }

        return albumList;
    }

    /**
     * sets albumList
     *
     * @param albumList the value to set
     */
    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    /**
     * gets songList
     *
     * @return songList value of songList
     */
    public List<Song> getSongList() {
        if (songList == null) {
            setSongList(new ArrayList<>());
            readAllSongs();
        }

        return songList;
    }

    /**
     * sets songList
     *
     * @param songList the value to set
     */
    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    /**
     * gets artistList
     *
     * @return artistList value of artistList
     */
    public List<Artist> getArtistList() {
        if (artistList == null) {
            setArtistList(new ArrayList<>());
            readAllArtists();
        }

        return artistList;
    }

    /**
     * reads all User
     *
     * @return list of User
     */
    public List<User> readAllUser() {
        return getUserList();
    }

    /**
     * sets artistList
     *
     * @param artistList the value to set
     */
    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }

    /**
     * gets userList
     *
     * @return userList value of userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * sets artistList
     *
     * @param userList value of userList
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}