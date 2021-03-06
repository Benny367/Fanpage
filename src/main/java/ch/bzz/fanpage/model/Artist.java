package ch.bzz.fanpage.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.Date;

/**
 * artist
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
public class Artist {

    @FormParam("artistUUID")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String artistUUID;

    @FormParam("firstName")
    @NotEmpty
    @Size(min=5, max=40)
    private String firstName;

    @FormParam("name")
    @NotEmpty
    @Size(min=5, max=40)
    private String name;

    @FormParam("artistName")
    @NotEmpty
    @Size(min=5, max=40)
    private String artistName;

    @FormParam("dateOfBirth")
    @NotEmpty
    @Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{4}")
    private String dateOfBirth;

    /**
     * default constructor
     *
     */
    public Artist() {
    }

    /**
     * instance constructor
     *
     */
    public Artist(String artistUUID, String firstName, String name, String artistName, String dateOfBirth) {
        this.artistUUID = artistUUID;
        this.firstName = firstName;
        this.name = name;
        this.artistName = artistName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * gets artistUUID
     *
     * @return artistUUID value of artistUUID
     */
    public String getArtistUUID() {
        return artistUUID;
    }

    /**
     * sets artistUUID
     *
     * @param artistUUID the value to set
     */
    public void setArtistUUID(String artistUUID) {
        this.artistUUID = artistUUID;
    }

    /**
     * gets firstName
     *
     * @return firstName value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets firstName
     *
     * @param firstName the value to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * gets artistName
     *
     * @return artistName value of artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * sets artistName
     *
     * @param artistName the value to set
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * gets dateOfBirth
     *
     * @return dateOfBirth value of dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * sets dateOfBirth
     *
     * @param dateOfBirth the value to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
