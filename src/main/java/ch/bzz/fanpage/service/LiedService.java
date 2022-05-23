package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;
import ch.bzz.fanpage.model.Lied;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Services fürs Lesen, Erstellen, Ändern und Löschen von Lieder
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
@Path("lied")
public class LiedService {
    /**
     * liest eine Liste von Lieder
     * @return liefert Lied als JSON
     */
    @GET
    @Path("liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listLieder() {
        List<Lied> liederListe = DataHandler.getInstance().readAllLieder();
        return Response
                .status(200)
                .entity(liederListe)
                .build();
    }

    /**
     * liest ein Lied
     * @return liefert Lied als JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readLied(@QueryParam("uuid") String liedUUID) {
        Lied lied = DataHandler.getInstance().readLiedByUUID(liedUUID);
        if(lied != null){
            return Response
                    .status(200)
                    .entity(lied)
                    .build();
        }
        else {
            return Response
                    .status(404)
                    .build();
        }
    }
}

