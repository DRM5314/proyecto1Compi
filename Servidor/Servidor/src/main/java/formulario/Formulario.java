
package formulario;

import componente.Componente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Formulario implements Serializable{
    private String id,titulo,nombre,tema,usuario_creacion,fechaCreacion;

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }
    
    private List<componente.Componente> componentes = new ArrayList<>();
    private List<String> nomvreComponentes = new ArrayList<>();

    public Formulario(String id, String titulo, String nombre, String tema) {
        this.id = id;
        this.titulo = titulo;
        this.nombre = nombre;
        this.tema = tema;
    }
    
    public void eliminarComponente(int pos){
        this.nomvreComponentes.remove(pos);
        this.componentes.remove(pos);
    }
    public void setIndiceComponente(int pos,Componente entrada){
        this.nomvreComponentes.add(pos, entrada.getId());
        this.componentes.add(pos, entrada);
    }
    public Componente getComponente(int pos){
        return this.componentes.get(pos);
    }
    
    public void agregarComponente(componente.Componente entrada){
        this.nomvreComponentes.add(entrada.getId());
        System.out.println("Componente de clase: se agrego"+entrada.getClase());
        this.componentes.add(entrada);
    }
    public void setComponente(int pos,Componente entrada){
        this.nomvreComponentes.set(pos, entrada.getId());
        this.componentes.set(pos, entrada);
    }
    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public List<String> getNomvreComponentes() {
        return nomvreComponentes;
    }

    public void setNomvreComponentes(List<String> nomvreComponentes) {
        this.nomvreComponentes = nomvreComponentes;
    }
                        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(String usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }
    
}
