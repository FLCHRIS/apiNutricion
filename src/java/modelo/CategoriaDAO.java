/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import modelo.pojo.Estado;
import modelo.pojo.Municipio;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Admin
 */
public class CategoriaDAO {
    public static List<Estado> obtenerEstados () {
        List<Estado> estados = null;
        
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                estados = conexionDB.selectList("categoria.obtenerEstados");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        
        return estados;
    }
    
    public static List<Municipio> obtenerMunicipioEstado (Integer idEstado) {
        List<Municipio> municipio = null;
        
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                municipio = conexionDB.selectList("categoria.obtenerMunicipioEstado", idEstado);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        
        return municipio;
    }
}
