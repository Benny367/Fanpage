package ch.bzz.fanpage.model;

/**
 * User, bedient die Applikation
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
     * Konstruktor zur Instanzierung
     *
     */
    public User(String userUUID, String username, String role, String password) {
        this.userUUID = userUUID;
        this.username = username;
        this.role = role;
        this.password = password;
    }

    /**
     * gibt userUUID zurueck
     *
     * @return userUUID wert von userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * setzt userUUID
     *
     * @param userUUID setzt wert von userUUID
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * gibt username zurueck
     *
     * @return username wert von username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setzt username
     *
     * @param username setzt wert von username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gibt role zurueck
     *
     * @return role wert von role
     */
    public String getRole() {
        return role;
    }

    /**
     * setzt role
     *
     * @param role setzt wert von role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * gibt password zurueck
     *
     * @return password wert von password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setzt password
     *
     * @param password setzt wert von password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}