package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;
import ch.bzz.fanpage.model.Artist;
import ch.bzz.fanpage.model.Song;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading, adding, changing and deleting song
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
@Path("song")
public class SongService {

    /**
     * reads a list of all songs
     * @return  songs as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSongs() {
        List<Song> songList = DataHandler.getInstance().readAllSongs();
        return Response
                .status(200)
                .entity(songList)
                .build();
    }

    /**
     * reads a song identified by the uuid
     * @param songUUID
     * @return song
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSong(@QueryParam("uuid") String songUUID) {
        Song song = DataHandler.getInstance().readSongByUUID(songUUID);
        if(song != null){
            return Response
                    .status(200)
                    .entity(song)
                    .build();
        }
        else {
            return Response
                    .status(404)
                    .build();
        }
    }

    /**
     * inserts a new song
     * @param songUUID the uuid of the song
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSong(
            @Valid @BeanParam Song song,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("songUUID") String songUUID
    ) {
        song.setSongUUID(songUUID);

        DataHandler.getInstance().insertSong(song);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new song
     * @param songUUID the uuid of the song
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSong(
            @Valid @BeanParam Song song,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("songUUID") String songUUID
    ) {
        int httpStatus = 200;
        Song oldSong = DataHandler.getInstance().readSongByUUID(song.getSongUUID());
        if (oldSong != null) {
            oldSong.setSongUUID(songUUID);
            oldSong.setName(song.getName());
            oldSong.setPublished(song.getPublished());

            DataHandler.getInstance().updateSong();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a song identified by its uuid
     * @param songUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSong(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String songUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteSong(songUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}

