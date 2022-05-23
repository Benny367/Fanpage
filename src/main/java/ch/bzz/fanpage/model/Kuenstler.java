package ch.bzz.fanpage.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Der Künstler
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
public class Kuenstler {
    private String kuenstlerUUID;
    private String vorname;
    private String name;
    private String kuenstlerName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date geburtsdatum;

    /**
     * default Konstruktor
     *
     */
    public Kuenstler() {
    }

    /**
     * Konstruktor zur Instanzierung
     *
     */
    public Kuenstler(String kuenstlerUUID, String vorname, String name, String kuenstlerName, Date geburtsdatum) {
        this.kuenstlerUUID = kuenstlerUUID;
        this.vorname = vorname;
        this.name = name;
        this.kuenstlerName = kuenstlerName;
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * gibt kuenstlerUUID zurueck
     *
     * @return kuenstlerUUID wert von kuenstlerUUID
     */
    public String getKuenstlerUUID() {
        return kuenstlerUUID;
    }

    /**
     * setzt kuenstlerUUID
     *
     * @param kuenstlerUUID setzt wert von kuenstlerUUID
     */
    public void setKuenstlerUUID(String kuenstlerUUID) {
        this.kuenstlerUUID = kuenstlerUUID;
    }

    /**
     * gibt vorname zurueck
     *
     * @return vorname wert von vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * setzt vorname
     *
     * @param vorname setzt wert von vorname
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
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
     * gibt kuenstlerName zurueck
     *
     * @return kuenstlerName wert von kuenstlerName
     */
    public String getKuenstlerName() {
        return kuenstlerName;
    }

    /**
     * setzt kuenstlerName
     *
     * @param kuenstlerName setzt wert von kuenstlerName
     */
    public void setKuenstlerName(String kuenstlerName) {
        this.kuenstlerName = kuenstlerName;
    }

    /**
     * gibt geburtsdatum zurueck
     *
     * @return geburtsdatum wert von geburtsdatum
     */
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * setzt geburtsdatum
     *
     * @param geburtsdatum setzt wert von geburtsdatum
     */
    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
}
