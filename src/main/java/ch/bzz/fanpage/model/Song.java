package ch.bzz.fanpage.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * song
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
public class Song {
    private String songUUID;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date published;

    /**
     * default Konstruktor
     *
     */
    public Song() {
    }

    /**
     * instance constructor
     *
     */
    public Song(String songUUID, String name, Date published) {
        this.songUUID = songUUID;
        this.name = name;
        this.published = published;
    }

    /**
     * gets songUUID
     *
     * @return songUUID value of songUUID
     */
    public String getSongUUID() {
        return songUUID;
    }

    /**
     * sets songUUID
     *
     * @param songUUID the value to set
     */
    public void setSongUUID(String songUUID) {
        this.songUUID = songUUID;
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
}
