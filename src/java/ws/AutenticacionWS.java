/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.AutenticacionDAO;
import modelo.pojo.RespuestaLoginEscritorio;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("autenticacion")
public class AutenticacionWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AutenticacionWS
     */
    public AutenticacionWS() {
    }

    @POST
    @Path("loginEscritorio")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLoginEscritorio iniciarSesionEscritorio(@FormParam("numeroPersona") String numeroPersona, @FormParam("password") String password) {
        RespuestaLoginEscritorio respuesta = null;
        if (!numeroPersona.isEmpty() && !password.isEmpty()) {
            respuesta = AutenticacionDAO.verificarSesionEscritorio(numeroPersona, password);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuesta;
    }
}
