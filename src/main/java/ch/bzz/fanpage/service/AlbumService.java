package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;
import ch.bzz.fanpage.model.Album;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading, adding, changing and deleting album
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
@Path("album")
public class AlbumService {

    /**
     * reads a list of all albums
     * @return  albums as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAlbums() {
        List<Album> albumList = DataHandler.getInstance().readAllAlbums();
        return Response
                .status(200)
                .entity(albumList)
                .build();
    }

    /**
     * reads a artist identified by the uuid
     * @param albumUUID
     * @return album
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

