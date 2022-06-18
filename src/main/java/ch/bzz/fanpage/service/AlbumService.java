package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;
import ch.bzz.fanpage.model.Album;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
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
                    .status(410)
                    .build();
        }
    }

    /**
     * inserts a new album
     * @param album model to create
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertAlbum(
            @Valid @BeanParam Album album
    ) {
        album.setAlbumUUID(album.getAlbumUUID());

        DataHandler.getInstance().insertAlbum(album);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new album
     * @param album model to update
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAlbum(
            @Valid @BeanParam Album album
    ) {
        int httpStatus = 200;
        Album oldAlbum = DataHandler.getInstance().readAlbumByUUID(album.getAlbumUUID());
        if (oldAlbum != null) {
            oldAlbum.setAlbumUUID(album.getAlbumUUID());
            oldAlbum.setName(album.getName());
            oldAlbum.setPublished(album.getPublished());
            oldAlbum.setSongs(album.getSongs());

            DataHandler.getInstance().updateAlbum();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a album identified by its uuid
     * @param albumUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAlbum(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String albumUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteAlbum(albumUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}

