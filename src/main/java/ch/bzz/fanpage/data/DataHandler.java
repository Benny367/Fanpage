package ch.bzz.fanpage.data;

import ch.bzz.fanpage.model.Album;
import ch.bzz.fanpage.model.Kuenstler;
import ch.bzz.fanpage.model.Lied;
import ch.bzz.fanpage.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * liest und schreibt daten ins JSON-File
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Album> albumListe;
    private List<Lied> liedListe;
    private List<Kuenstler> kuenstlerListe;

    /**
     * privater Konstruktor für die Instanzierung
     */
    private DataHandler() {
        setAlbumListe(new ArrayList<>());
        readAlbenJSON();
        setLiedListe(new ArrayList<>());
        readLiedJSON();
        setKuenstlerListe(new ArrayList<>());
        readKuenstlerJSON();
    }

    /**
     * ermöglicht nur eine Instanz
     *
     * @return instance
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /**
     * liest alle Alben
     * @return liste aller Alben
     */
    public List<Album> readAllAlben() {
        return getAlbumListe();
    }

    /**
     * liest ein Album anhand seiner UUID
     *
     * @param albumUUID
     * @return Wert von Album
     */
    public Album readAlbumByUUID(String albumUUID) {
        Album album = null;
        for (Album a : getAlbumListe()) {
            if (a.getAlbumUUID().equals(albumUUID)) {
                album = a;
            }
        }
        return album;
    }

    /**
     * liest alle Lieder
     * @return liste aller Lieder
     */
    public List<Lied> readAllLieder() {
        return getLiedListe();
    }

    /**
     * liest ein Lied anhand seiner UUID
     *
     * @param liedUUID
     * @return Wert von Lied
     */
    public Lied readLiedByUUID(String liedUUID) {
        Lied lied = null;
        for (Lied l : getLiedListe()) {
            if (l.getLiedUUID().equals(liedUUID)) {
                lied = l;
            }
        }
        return lied;
    }

    /**
     * liest alle Kuenstler
     * @return liste aller Kuenstler
     */
    public List<Kuenstler> readAllKuenstler() {
        return getKuenstlerListe();
    }

    /**
     * liest einen Kuenstler anhand seiner UUID
     *
     * @param kuenstlerUUID
     * @return Wert von Kuenstler
     */
    public Kuenstler readProduktByUUID(String kuenstlerUUID) {
        Kuenstler kuenstler = null;
        for (Kuenstler k : getKuenstlerListe()) {
            if (k.getKuenstlerUUID().equals(kuenstlerUUID)) {
                kuenstler = k;
            }
        }
        return kuenstler;
    }

    /**
     * liest das Album aus einem JSON-File
     */
    private void readAlbenJSON() {
        try {
            String path = Config.getProperty("AlbumJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Album[] alben = objectMapper.readValue(jsonData, Album[].class);
            for (Album a : alben) {
                getAlbumListe().add(a);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * liest den Inhalt aus einem JSON-File
     */
    private void readLiedJSON() {
        try {
            String path = Config.getProperty("LiedJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Lied[] lieder = objectMapper.readValue(jsonData, Lied[].class);
            for (Lied l : lieder) {
                getLiedListe().add(l);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * liest die Produkte aus einem JSON-File
     */
    private void readKuenstlerJSON() {
        try {
            String path = Config.getProperty("KuenstlerJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Kuenstler[] kuenstler = objectMapper.readValue(jsonData, Kuenstler[].class);
            for (Kuenstler k : kuenstler) {
                getKuenstlerListe().add(k);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * holt Albumliste
     *
     * @return Wert von Albumliste
     */
    private List<Album> getAlbumListe() {
        return albumListe;
    }

    /**
     * setzt Albumliste
     *
     * @param albumListe
     */
    private void setAlbumListe(List<Album> albumListe) {
        this.albumListe = albumListe;
    }

    /**
     * holt Liedliste
     *
     * @return Wert von Liedliste
     */
    private List<Lied> getLiedListe() {
        return liedListe;
    }

    /**
     * setzt Liedliste
     *
     * @param liedListe
     */
    private void setLiedListe(List<Lied> liedListe) {
        this.liedListe = liedListe;
    }

    /**
     * holt Kuenstlerliste
     *
     * @return Wert von Kuenstlerliste
     */
    public List<Kuenstler> getKuenstlerListe() {
        return kuenstlerListe;
    }

    /**
     * setzt KuenstlerListe
     *
     * @param kuenstlerListe
     */
    public void setKuenstlerListe(List<Kuenstler> kuenstlerListe) {
        this.kuenstlerListe = kuenstlerListe;
    }
}