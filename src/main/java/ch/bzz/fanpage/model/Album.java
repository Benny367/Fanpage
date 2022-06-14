package ch.bzz.fanpage.model;

import ch.bzz.fanpage.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * album
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
public class Album {
    private String albumUUID;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date published;

    @JsonIgnore
    private List<Song> songs;

    /**
     * default Konstruktor
     *
     */
    public Album() {
    }

    /**
     * instance constructor
     *
     */
    public Album(String albumUUID, String name, Date published, List<Song> songs) {
        this.albumUUID = albumUUID;
        this.name = name;
        this.published = published;
        this.songs = songs;
    }

    /**
     * gets albumUUID
     *
     * @return albumUUID value of albumUUID
     */
    public String getAlbumUUID() {
        return albumUUID;
    }

    /**
     * sets albumUUID
     *
     * @param albumUUID the value to set
     */
    public void setAlbumUUID(String albumUUID) {
        this.albumUUID = albumUUID;
    }

    /**
     * gets name
     *
     * @return name value of name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name
     *
     * @param name the value to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets published
     *
     * @return published value of published
     */
    public Date getPublished() {
        return published;
    }

    /**
     * sets published
     *
     * @param published the value to set
     */
    public void setPublished(Date published) {
        this.published = published;
    }

    /**
     * gets songs
     *
     * @return songs value of songs
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * sets songs
     *
     * @param songs the value to set
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    /**
     * sets songUUID
     *
     * @param songUUID the value to set
     */
    public void setSongUUID(ArrayNode songUUID){
        setSongs(new ArrayList<>());
        for (JsonNode jsonNode : songUUID) {
            getSongs().add(DataHandler.getInstance().readSongByUUID(jsonNode.get("songUUID").textValue()));
        }
    }
}
