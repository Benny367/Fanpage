package ch.bzz.fanpage.service;


import ch.bzz.fanpage.util.AuthorizationFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * configure the web services and properties
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-23
 * @version : 1.0
 */
@ApplicationPath("/resource")
public class Config extends Application {
    private static final String PROPERTIES_PATH = "/home/bzz/IdeaProjects/Fanpage/testing/fanpageListe.properties";
    private static Properties properties = null;

    /**
     * define all provider classes
     *
     * @return set of classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(TestService.class);
        providers.add(AlbumService.class);
        providers.add(ArtistService.class);
        providers.add(SongService.class);
        providers.add(UserService.class);
        providers.add(AuthorizationFilter.class);
        return providers;
    }

    /**
     * gets the value of a property
     *
     * @param property the key of the property to be read
     * @return the value of the property
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
     * reads the properties file
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
     * sets the properties
     *
     * @param properties the value to set
     */
    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}