/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.DomicilioDAO;
import modelo.pojo.Domicilio;
import modelo.pojo.RespuestaPaciente;

/**
 *
 * @author Admin
 */
@Path("domicilio")
public class DomicilioWS {
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespuestaPaciente registrarDomicilioPaciente(String json) {
        Gson gson = new Gson();
        Domicilio domicilio = gson.fromJson(json, Domicilio.class);
        
        if (domicilio != null && domicilio.getIdPaciente() != null && domicilio.getIdPaciente() > 0) {
            return DomicilioDAO.registrarDomicilioPaciente(domicilio);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
    }
    
    @Path("obtenerDomicilioPaciente/{idPaciente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Domicilio obtenerDomicilioPaciente(@PathParam("idPaciente") Integer idPaciente) {
        if (idPaciente != null && idPaciente > 0) {
            return DomicilioDAO.obtenerDomicilioPaciente(idPaciente);
        } else {
           throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
