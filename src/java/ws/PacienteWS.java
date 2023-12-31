package ws;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.PacienteDAO;
import modelo.pojo.Paciente;
import modelo.pojo.RespuestaPaciente;

/**
 *
 * @author Admin
 */
@Path("pacientes")
public class PacienteWS {

    @Context
    private UriInfo context;

    @GET
    @Path("obtenerPorId/{idMedico}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaPaciente obtenerPorId(@PathParam("idMedico") Integer idMedico) {
        
        if (idMedico < 1) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        RespuestaPaciente respuesta = PacienteDAO.obtenerPorId(idMedico);
        return respuesta;
    }

    @DELETE
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaPaciente eliminar(@FormParam("idPaciente") Integer idPaciente) {
        RespuestaPaciente respuesta = null;

        if (idPaciente == null || idPaciente < 1) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        respuesta = PacienteDAO.eliminar(idPaciente);

        return respuesta;
    }

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaPaciente agregar(@FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno, @FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("fechaNacimiento") String fechaNacimiento, @FormParam("sexo") String sexo, @FormParam("peso") Float peso, @FormParam("estatura") Float estatura, @FormParam("tallaInicial") Integer tallaInicial, @FormParam("email") String email, @FormParam("telefono") String telefono, @FormParam("contrasena") String contrasena, @FormParam("idMedico") Integer idMedico) {
        RespuestaPaciente respuesta = null;

        if (nombre == null || apellidoPaterno == null || apellidoMaterno == null || fechaNacimiento == null || sexo == null || peso == null || estatura == null || tallaInicial == null || email == null || telefono == null || contrasena == null || idMedico == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        respuesta = PacienteDAO.agregar(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo, peso, estatura, tallaInicial, email, telefono, contrasena, idMedico);

        return respuesta;
    }
    
    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaPaciente editar(@FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno, @FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("fechaNacimiento") String fechaNacimiento, @FormParam("sexo") String sexo, @FormParam("peso") Float peso, @FormParam("estatura") Float estatura, @FormParam("tallaInicial") Integer tallaInicial, @FormParam("telefono") String telefono, @FormParam("contrasena") String contrasena, @FormParam("idPaciente") Integer idPaciente) {
        RespuestaPaciente respuesta = null;

        if (nombre == null || apellidoPaterno == null || apellidoMaterno == null || fechaNacimiento == null || sexo == null || peso == null || estatura == null || tallaInicial == null || telefono == null || contrasena == null || idPaciente == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        respuesta = PacienteDAO.editar(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo, peso, estatura, tallaInicial, telefono, contrasena, idPaciente);
        
        return respuesta;
    }
    
    @PUT
    @Path("RegistrarFoto/{idPaciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaPaciente registrarFotografia(@PathParam("idPaciente") Integer idPaciente, byte[] foto) {
        RespuestaPaciente respuesta = new RespuestaPaciente();
        
        if (idPaciente != null && idPaciente > 0 && foto != null) {
            respuesta = PacienteDAO.subirFotografia(idPaciente, foto);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return respuesta;
    }

    @GET
    @Path("obtenerFoto/{idPaciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Paciente obtenerFotografia(@PathParam("idPaciente") Integer idPaciente) {
        Paciente paciente = null;
        
        if (idPaciente != null && idPaciente > 0) {
            paciente = PacienteDAO.obtenerFotografia(idPaciente);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return paciente;
    }
}
