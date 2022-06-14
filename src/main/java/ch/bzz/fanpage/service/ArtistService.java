package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;
import ch.bzz.fanpage.model.Artist;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
                    .status(404)
                    .build();
        }
    }
}

