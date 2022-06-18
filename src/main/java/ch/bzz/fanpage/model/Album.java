package ch.bzz.fanpage.model;

import ch.bzz.fanpage.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
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

    @FormParam("albumUUID")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String albumUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min=5, max=40)
    private String name;

    @FormParam("published")
    @NotEmpty
    @Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{4}")
    private String published;

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
    public Album(String albumUUID, String name, String published, List<Song> songs) {
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
    public String getPublished() {
        return published;
    }

    /**
     * sets published
     *
     * @param published the value to set
     */
    public void setPublished(String published) {
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

    /*
     * sets songs
     * @param songs
     */
    @JsonProperty("song")
    @FormParam("songs")
    public void setSongsUUID(List<String> songs) {
        setSongs(new ArrayList<>());
        for (String s : songs) {
            this.songs.add(DataHandler.getInstance().readSongByUUID(s));
        }
    }
}
