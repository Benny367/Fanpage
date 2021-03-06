package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;
import ch.bzz.fanpage.model.Artist;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading, adding, changing and deleting artist
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
@Path("artist")
public class ArtistService {

    /**
     * reads a list of all artists
     * @return  artists as JSON
     */
    @RolesAllowed({"admin", "user"})
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listArtists() {
        List<Artist> artistList = DataHandler.getInstance().readAllArtists();
        return Response
                .status(200)
                .entity(artistList)
                .build();
    }

    /**
     * reads a artist identified by the uuid
     * @param artistUUID
     * @return artist
     */
    @RolesAllowed({"admin", "user"})
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readArtist(@QueryParam("uuid") String artistUUID) {
        Artist artist = DataHandler.getInstance().readArtistByUUID(artistUUID);
        if(artist != null){
            return Response
                    .status(200)
                    .entity(artist)
                    .build();
        }
        else {
            return Response
                    .status(410)
                    .build();
        }
    }

    /**
     * inserts a new artist
     * @param artist model to create
     * @return Response
     */
    @RolesAllowed({"admin", "user"})
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertArtist(
            @Valid @BeanParam Artist artist
    ) {
        artist.setArtistUUID(artist.getArtistUUID());

        DataHandler.getInstance().insertArtist(artist);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new artist
     * @param artist model to update
     * @return Response
     */
    @RolesAllowed({"admin", "user"})
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateArtist(
            @Valid @BeanParam Artist artist
    ) {
        int httpStatus = 200;
        Artist oldArtist = DataHandler.getInstance().readArtistByUUID(artist.getArtistUUID());
        if (oldArtist != null) {
            oldArtist.setFirstName(artist.getFirstName());
            oldArtist.setName(artist.getName());
            oldArtist.setArtistName(artist.getArtistName());
            oldArtist.setDateOfBirth(artist.getDateOfBirth());

            DataHandler.getInstance().updateArtist();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a artist identified by its uuid
     * @param artistUUID  the key
     * @return  Response
     */
    @RolesAllowed({"admin"})
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteArtist(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String artistUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteArtist(artistUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}

