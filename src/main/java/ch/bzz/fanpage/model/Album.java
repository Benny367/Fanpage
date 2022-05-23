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
 * Die Alben, in welche sich die Lieder befinden
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
public class Album {
    private String albumUUID;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date publiziert;

    @JsonIgnore
    private List<Lied> lieder;

    /**
     * default Konstruktor
     *
     */
    public Album() {
    }

    /**
     * Konstruktor zur Instanzierung
     *
     */
    public Album(String albumUUID, String name, Date publiziert, List<Lied> lieder) {
        this.albumUUID = albumUUID;
        this.name = name;
        this.publiziert = publiziert;
        this.lieder = lieder;
    }

    /**
     * setzt Lieder in Liste
     *
     * @param liederUUID
     */
    public void setLiederUUID(ArrayNode liederUUID){
        setLieder(new ArrayList<>());
        for (JsonNode jsonNode : liederUUID) {
            getLieder().add(DataHandler.getInstance().readLiedByUUID(jsonNode.get("liedUUID").textValue()));
        }
    }

    /**
     * gibt albumUUID zurueck
     *
     * @return albumUUID wert von albumUUID
     */
    public String getAlbumUUID() {
        return albumUUID;
    }

    /**
     * setzt albumUUID
     *
     * @param albumUUID setzt wert von albumUUID
     */
    public void setAlbumUUID(String albumUUID) {
        this.albumUUID = albumUUID;
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

    /**
     * gibt lieder zurueck
     *
     * @return lieder wert von lieder
     */
    public List<Lied> getLieder() {
        return lieder;
    }

    /**
     * setzt lieder
     *
     * @param lieder setzt wert von lieder
     */
    public void setLieder(List<Lied> lieder) {
        this.lieder = lieder;
    }
}
