/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

import java.util.List;

/**
 *
 * @author Admin
 */
public class RespuestaPaciente {
    private Boolean error;
    private String contenido;
    private List<Paciente> pacientes;

    public RespuestaPaciente(Boolean error, String contenido, List<Paciente> pacientes) {
        this.error = error;
        this.contenido = contenido;
        this.pacientes = pacientes;
    }

    public RespuestaPaciente() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
}
