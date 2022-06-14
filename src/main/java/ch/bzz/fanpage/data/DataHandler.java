package ch.bzz.fanpage.data;

import ch.bzz.fanpage.model.Album;
import ch.bzz.fanpage.model.Artist;
import ch.bzz.fanpage.model.Song;
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

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setAlbumList(new ArrayList<>());
        readAlbumsJSON();
        setSongList(new ArrayList<>());
        readSongJSON();
        setArtistList(new ArrayList<>());
        readArtistJSON();
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
    public void updateKuenstler() {
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
            objectWriter.writeValue(fileWriter, getArtistList());
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
            objectWriter.writeValue(fileWriter, getArtistList());
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
        return artistList;
    }

    /**
     * sets artistList
     *
     * @param artistList the value to set
     */
    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }
}