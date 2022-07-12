package ch.bzz.fanpage.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * User
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @verison : 1.0
 * */
public class User {

    @FormParam("userUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89ABab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")
    private String userUUID;

    @FormParam("userName")
    @NotEmpty
    @Size(min = 3,max = 30)
    private String username;

    @FormParam("userRole")
    @NotEmpty
    @Size(min = 3,max = 30)
    private String userRole;

    @FormParam("password")
    @NotEmpty
    @Size(min = 5,max = 30)
    private String password;

    @FormParam("words")
    @NotEmpty
    private List<String> words;

    /**
     * instance constructor
     *
     */
    public User(String userUUID, String username, String userRole, String password, List<String> words) {
        this.userUUID = userUUID;
        this.username = username;
        this.userRole = userRole;
        this.password = password;
        this.words = words;
    }

    /**
     * empty constructor
     */
    public User() {
    }

    /**
     * Used to logon the user
     */
    public void logon(){

    }

    /**
     * Used to logoff the user
     */
    public void logoff(){

    }

    /**
     * gets userUUID
     *
     * @return userUUID value of userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * sets userUUID
     *
     * @param userUUID the value to set
     */
    public User setUserUUID(String userUUID) {
        this.userUUID = userUUID;
        return this;
    }

    /**
     * gets userName
     *
     * @return userName value of userName
     */
    public String getUserName() {
        return username;
    }

    /**
     * sets userName
     *
     * @param username the value to set
     */
    public User setUserName(String username) {
        this.username = username;
        return this;
    }

    /**
     * gets userRole
     *
     * @return userRole value of userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * sets userRole
     *
     * @param userRole the value to set
     */
    public User setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    /**
     * gets password
     *
     * @return password value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     *
     * @param password the value to set
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * gets words
     *
     * @return words value of words
     */
    public List<String> getWords() {
        return words;
    }

    /**
     * sets words
     *
     * @param words the value to set
     */
    public User setWords(List<String> words) {
        this.words = words;
        return this;
    }
}