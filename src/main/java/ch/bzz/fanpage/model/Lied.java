package ch.bzz.fanpage.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Ein Lied des Künstlers
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
public class Lied {
    private String liedUUID;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date publiziert;

    /**
     * default Konstruktor
     *
     */
    public Lied() {
    }

    /**
     * Konstruktor zur Instanzierung
     *
     */
    public Lied(String liedUUID, String name, Date publiziert) {
        this.liedUUID = liedUUID;
        this.name = name;
        this.publiziert = publiziert;
    }

    /**
     * gibt liedUUID zurueck
     *
     * @return liedUUID wert von liedUUID
     */
    public String getLiedUUID() {
        return liedUUID;
    }

    /**
     * setzt liedUUID
     *
     * @param liedUUID setzt wert von liedUUID
     */
    public void setLiedUUID(String liedUUID) {
        this.liedUUID = liedUUID;
    }

    /**
     * gibt name zurueck
     *
     * @return name wert von name
     */
    public String getName() {
        return name;
    }

    /**
     * setzt name
     *
     * @param name setzt wert von name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gibt publiziert zurueck
     *
     * @return publiziert wert von publiziert
     */
    public Date getPubliziert() {
        return publiziert;
    }

    /**
     * setzt publiziert
     *
     * @param publiziert setzt wert von publiziert
     */
    public void setPubliziert(Date publiziert) {
        this.publiziert = publiziert;
    }
}
