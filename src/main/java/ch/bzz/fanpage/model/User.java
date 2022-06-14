package ch.bzz.fanpage.model;

/**
 * User
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @verison : 1.0
 * */
public class User {
    private String userUUID;
    private String username;
    private String role;
    private String password;

    /**
     * instance constructor
     *
     */
    public User(String userUUID, String username, String role, String password) {
        this.userUUID = userUUID;
        this.username = username;
        this.role = role;
        this.password = password;
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
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * gets username
     *
     * @return username value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets username
     *
     * @param username the value to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets role
     *
     * @return role value of role
     */
    public String getRole() {
        return role;
    }

    /**
     * sets role
     *
     * @param role the value to set
     */
    public void setRole(String role) {
        this.role = role;
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
    public void setPassword(String password) {
        this.password = password;
    }
}