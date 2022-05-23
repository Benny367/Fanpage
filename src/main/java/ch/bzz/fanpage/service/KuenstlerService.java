package ch.bzz.fanpage.service;

import ch.bzz.fanpage.data.DataHandler;
import ch.bzz.fanpage.model.Kuenstler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Services fürs Lesen, Erstellen, Ändern und Löschen von Kuenstler
 *
 * @author  : Mehic Benjamin
 * @date    : 2022-05-22
 * @version : 1.0
 */
@Path("kuenstler")
public class KuenstlerService {
    /**
     * liest eine Liste von Kuenstler
     * @return liefert Kuenstler als JSON
     */
    @GET
    @Path("liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKuenstler() {
        List<Kuenstler> kuenstlerListe = DataHandler.getInstance().readAllKuenstler();
        return Response
                .status(200)
                .entity(kuenstlerListe)
                .build();
    }

    /**
     * liest einen Kuenstler
     * @return liefert Kuenstler als JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKuenstler(@QueryParam("uuid") String kuenstlerUUID) {
        Kuenstler kuenstler = DataHandler.getInstance().readProduktByUUID(kuenstlerUUID);
        if(kuenstler != null){
            return Response
                    .status(200)
                    .entity(kuenstler)
                    .build();
        }
        else {
            return Response
                    .status(404)
                    .build();
        }
    }
}

