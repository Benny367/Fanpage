package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;
import ch.bzz.fanpage.model.Album;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Services fürs Lesen, Erstellen, Ändern und Löschen von Alben
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
@Path("album")
public class AlbumService {
    /**
     * liest eine Liste von Alben
     * @return liefert Album als JSON
     */
    @GET
    @Path("liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAlben() {
        List<Album> albumListe = DataHandler.getInstance().readAllAlben();
        return Response
                .status(200)
                .entity(albumListe)
                .build();
    }

    /**
     * liest ein Album
     * @return liefert Album als JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAlbum(@QueryParam("uuid") String albumUUID) {
        Album album = DataHandler.getInstance().readAlbumByUUID(albumUUID);
        if(album != null){
            return Response
                    .status(200)
                    .entity(album)
                    .build();
        }
        else {
            return Response
                    .status(404)
                    .build();
        }
    }
}

