package ch.bzz.fanpage.service;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * konfiguriert die Web-Services und Propertiers
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-23
 * @version : 1.0
 */
@ApplicationPath("/resource")
public class Config extends Application {
    private static final String PROPERTIES_PATH = "/home/bzz/IdeaProjects/Fanpage/testing/fanpageListe";
    private static Properties properties = null;

    /**
     * alle Klassen definieren
     *
     * @return Set von Klassen
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(TestService.class);
        providers.add(AlbumService.class);
        providers.add(KuenstlerService.class);
        providers.add(LiedService.class);
        return providers;
    }

    /**
     * holt property
     *
     * @param property
     * @return Wert von property
     */
    public static String getProperty(String property) {
        if (Config.properties == null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value == null) return "";
        return value;
    }

    /**
     * liest das Properties-File
     *
     */
    private static void readProperties() {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(PROPERTIES_PATH);
            properties.load(inputStream);
            if (inputStream != null) inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * setzt die Properties
     *
     * @param properties
     */
    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}