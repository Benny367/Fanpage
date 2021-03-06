package ch.bzz.fanpage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.time.LocalDate;
import java.util.Date;

/**
 * song
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
public class Song {

    @FormParam("songUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String songUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min=5, max=40)
    private String name;

    @FormParam("published")
    @NotEmpty
    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{4}")
    private String published;

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
    public Song(String songUUID, String name, String published) {
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
}
