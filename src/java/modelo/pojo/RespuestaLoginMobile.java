package modelo.pojo;

public class RespuestaLoginMobile {
    
    private Boolean error;
    private String contenido;
    private Paciente paciente;

    public RespuestaLoginMobile() {
    }

    public RespuestaLoginMobile(Boolean error, String contenido, Paciente paciente) {
        this.error = error;
        this.contenido = contenido;
        this.paciente = paciente;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
}
