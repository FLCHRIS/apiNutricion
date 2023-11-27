/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import modelo.pojo.Medico;
import modelo.pojo.Paciente;
import modelo.pojo.RespuestaLoginEscritorio;
import modelo.pojo.RespuestaLoginMobile;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Admin
 */
public class AutenticacionDAO {
    
    public static RespuestaLoginEscritorio verificarSesionEscritorio(String numeroPersonal, String password) {
        RespuestaLoginEscritorio respuesta = new RespuestaLoginEscritorio();
        respuesta.setError(Boolean.TRUE);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                HashMap<String, String> parametros = new HashMap<>();
                parametros.put("numeroPersonal", numeroPersonal);
                parametros.put("contrasena", password);
                Medico medico = conexionDB.selectOne("autenticacion.loginEscritorio", parametros);
                if (medico != null) {
                    respuesta.setError(Boolean.FALSE);
                    respuesta.setContenido("Bienvenido(a) " + medico.getNombre() + " al sistema de control");
                    respuesta.setMedicoSesion(medico);
                } else {
                    respuesta.setContenido("Numero de personal y/o password incorrectos, favor de verificar");
                }
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionDB.close();
            }
        } else {
            respuesta.setContenido("No hay conexion con la base de datos");
        }
        return respuesta;
    }

    public static RespuestaLoginMobile verificarSesionMobile(
            String email, String contrasena) {
        RespuestaLoginMobile respuesta = new RespuestaLoginMobile();
        respuesta.setError(Boolean.TRUE);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                HashMap<String, String> parametros = new HashMap<>();
                parametros.put("email", email);
                parametros.put("contrasena", contrasena);
                
                Paciente paciente = conexionDB.selectOne("autenticacion.loginMobile", parametros);
                if (paciente != null) {
                    respuesta.setError(Boolean.FALSE);
                    respuesta.setContenido("Bienvenido(a) " + paciente.getNombre() + " al sistema.");
                    respuesta.setPaciente(paciente);
                } else {
                    respuesta.setContenido("Correo y/o contraseña incorrectos, favor de verificar");
                }
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionDB.close();
            }
        } else {
            respuesta.setContenido("No hay conexion con la base de datos");
        }
        return respuesta;
    }
}
