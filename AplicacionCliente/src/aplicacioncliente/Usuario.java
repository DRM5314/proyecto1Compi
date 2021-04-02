package aplicacioncliente;

public class Usuario {
    private final String usuario,fechaCreacion;
    private String fechaModificacion;

    public Usuario(String usuario, String fechaCreacion, String fechaModificacion) {
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    
    
}
