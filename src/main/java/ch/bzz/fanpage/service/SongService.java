package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;
import ch.bzz.fanpage.model.Song;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
}

