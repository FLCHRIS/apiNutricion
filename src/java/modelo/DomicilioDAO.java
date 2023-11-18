package modelo;

import modelo.pojo.Domicilio;
import modelo.pojo.RespuestaPaciente;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class DomicilioDAO {
    
    public static Domicilio obtenerDomicilioPaciente(Integer idPaciente) {
        Domicilio domicilio = null;
        
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                domicilio = conexionDB.selectOne("domicilio.obtenerDomicilioPaciente", idPaciente);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        
        return domicilio;
    }

    public static RespuestaPaciente registrarDomicilioPaciente(Domicilio domicilio) {
        RespuestaPaciente mensaje = new RespuestaPaciente();
        mensaje.setError(Boolean.TRUE);

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.insert("domicilio.registrar", domicilio);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    mensaje.setError(Boolean.FALSE);
                    mensaje.setContenido("Domicilio del paciente agregado con éxito");
                } else {
                    mensaje.setContenido("No se puede registrar la información del domicilio enviado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setContenido("Por el momento no hay conexión para guardar domicilio");
        }

        return mensaje;
    }

    public static RespuestaPaciente actualizarDomicilioPaciente(Domicilio domicilio) {
        RespuestaPaciente mensaje = new RespuestaPaciente();
        mensaje.setError(Boolean.TRUE);
        
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("domicilio.editar", domicilio);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    mensaje.setError(Boolean.FALSE);
                    mensaje.setContenido("Domicilio del paciente actualizado con éxito");
                } else {
                    mensaje.setContenido("No se puede actualizar la información del domicilio enviado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setContenido("Por el momento no hay conexión para guardar domicilio");
        }

        return mensaje;
    }
}
