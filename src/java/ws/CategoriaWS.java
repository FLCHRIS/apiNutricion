/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.CategoriaDAO;
import modelo.pojo.Estado;
import modelo.pojo.Municipio;

@Path("categoria")
public class CategoriaWS {
    @Path("obtenerEstados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> obtenerTodos() {
        return CategoriaDAO.obtenerEstados();
    }
    
    @Path("obtenerMunicipioEstado/{idEstado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Municipio> obtenerMunicipioEstado(@PathParam("idEstado") Integer idEstado) {
        List<Municipio> municipios = null;
        
        if (idEstado!= null && idEstado > 0) {
            municipios = CategoriaDAO.obtenerMunicipioEstado(idEstado);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return municipios;
    }
}
