package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Paciente;
import modelo.pojo.RespuestaPaciente;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


/*
* TODO obtener paciente por idMedico [x]
* TODO registrar paciente sin fotografía y iddomicilio [x]
* TODO Editar paciente, sin editar foto, idDomicilio, correo e id [x]
* TODO Eliminar paciente [x]
*/
public class PacienteDAO {

    public static RespuestaPaciente obtenerPorId(Integer idMedico) {
        RespuestaPaciente respuesta = new RespuestaPaciente();
        respuesta.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                respuesta.setContenido("No hay conexion con la base de datos");
                return respuesta;
            }

            List<Paciente> pacientes = conexionDB.selectList("paciente.obtenerPorId", idMedico);
            respuesta.setPacientes(pacientes);

            if (!pacientes.isEmpty()) {
                respuesta.setError(Boolean.FALSE);
                respuesta.setContenido("Respuesta exitosa");
            } else {
                respuesta.setContenido("No hay pacientes para este medico");
            }
        } catch (Exception e) {
            respuesta.setContenido("Error: " + e.getMessage());
        }

        return respuesta;
    }

    public static RespuestaPaciente eliminar(Integer idPaciente) {
        RespuestaPaciente respuesta = new RespuestaPaciente();
        respuesta.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {

            if (conexionDB == null) {
                respuesta.setContenido("No hay conexion con la base de datos");
                return respuesta;
            }

            Paciente paciente = conexionDB.selectOne("paciente.obtenerPaciente", idPaciente);
            if (paciente == null) {
                respuesta.setContenido("No se pudo eliminar la información del paciente porque no existe.");
                return respuesta;
            }

            int filasAfectadas = conexionDB.delete("paciente.eliminar", idPaciente);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                respuesta.setError(Boolean.FALSE);
                respuesta.setContenido("Paciente eliminado con éxito");
            } else {
                respuesta.setContenido("No se pudo eliminar la información del paciente.");
            }

        } catch (Exception e) {
            respuesta.setError(Boolean.TRUE);
            respuesta.setContenido("Error: " + e.getMessage());
        }

        return respuesta;
    }

    public static RespuestaPaciente agregar(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String sexo, Float peso, Float estatura, Integer tallaInicial, String email, String telefono, String contrasena, Integer idMedico) {
        RespuestaPaciente respuesta = new RespuestaPaciente();
        respuesta.setError(Boolean.TRUE);

        HashMap<String, Object> nuevoPaciente = new HashMap<>();
        nuevoPaciente.put("nombre", nombre);
        nuevoPaciente.put("apellidoPaterno", apellidoPaterno);
        nuevoPaciente.put("apellidoMaterno", apellidoMaterno);
        nuevoPaciente.put("fechaNacimiento", fechaNacimiento);
        nuevoPaciente.put("sexo", sexo);
        nuevoPaciente.put("peso", peso);
        nuevoPaciente.put("estatura", estatura);
        nuevoPaciente.put("tallaInicial", tallaInicial);
        nuevoPaciente.put("email", email);
        nuevoPaciente.put("telefono", telefono);
        nuevoPaciente.put("contrasena", contrasena);
        nuevoPaciente.put("idMedico", idMedico);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {

            if (conexionDB == null) {
                respuesta.setContenido("No hay conexion con la base de datos");
                return respuesta;
            }

            int filasAfectadas = conexionDB.insert("paciente.registrar", nuevoPaciente);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                respuesta.setError(Boolean.FALSE);
                respuesta.setContenido("Paciente agregado con éxito");
            } else {
                respuesta.setContenido("No se pudo registrar el paciente.");
            }

        } catch (Exception e) {
            respuesta.setContenido("Error: " + e.getMessage());
        }

        return respuesta;
    }

    public static RespuestaPaciente editar(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String sexo, Float peso, Float estatura, Integer tallaInicial, String telefono, String contrasena, Integer idMedico, Integer idPaciente) {
        RespuestaPaciente respuesta = new RespuestaPaciente();
        respuesta.setError(Boolean.TRUE);
        
        HashMap<String, Object> editarPaciente = new HashMap<>();
        editarPaciente.put("nombre", nombre);
        editarPaciente.put("apellidoPaterno", apellidoPaterno);
        editarPaciente.put("apellidoMaterno", apellidoMaterno);
        editarPaciente.put("fechaNacimiento", fechaNacimiento);
        editarPaciente.put("sexo", sexo);
        editarPaciente.put("peso", peso);
        editarPaciente.put("estatura", estatura);
        editarPaciente.put("tallaInicial", tallaInicial);
        editarPaciente.put("telefono", telefono);
        editarPaciente.put("contrasena", contrasena);
        editarPaciente.put("idMedico", idMedico);
        editarPaciente.put("idPaciente", idPaciente);
        
        try (SqlSession conexionDB = MyBatisUtil.getSession()) {

            if (conexionDB == null) {
                respuesta.setContenido("No hay conexion con la base de datos");
                return respuesta;
            }

            int filasAfectadas = conexionDB.insert("paciente.editar", editarPaciente);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                respuesta.setError(Boolean.FALSE);
                respuesta.setContenido("Paciente editado con éxito");
            } else {
                respuesta.setContenido("No se pudo editar el paciente.");
            }

        } catch (Exception e) {
            respuesta.setContenido("Error: " + e.getMessage());
        }

        return respuesta;
    }
}
