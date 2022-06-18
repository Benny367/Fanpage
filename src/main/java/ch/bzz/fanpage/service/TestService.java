package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * confirms the application runs
 * @return  message
 */
@Path("test")
public class TestService {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {
        return Response
                .status(200)
                .entity("Test successful")
                .build();
    }

    /**
     * restores the json-files
     * @return Response
     */
    @GET
    @Path("restore")
    @Produces(MediaType.TEXT_PLAIN)
    public Response restore() {
        String[] pathsJSON = new String[]{"albumJSON", "artistJSON", "songJSON"};
        try {
            for (String pathJSON : pathsJSON) {
                java.nio.file.Path path = Paths.get(Config.getProperty(pathJSON));
                String filename = path.getFileName().toString();
                String folder = path.getParent().toString();

                byte[] JSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
                FileOutputStream fileOutputStream = new FileOutputStream(Config.getProperty(pathJSON));
                fileOutputStream.write(JSON);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataHandler.getInstance().initLists();
        return Response
                .status(200)
                .entity("Erfolgreich")
                .build();
    }
}