package formulario;

import archivos.ManejadorArchivos;
import java.util.ArrayList;
import java.util.List;

public class ManejadorFormulariosAppWeb {

    private List<formulario.Formulario> formularios = new ArrayList<>();
    private final String usuario;

    public ManejadorFormulariosAppWeb(String usuario) {
        this.usuario = usuario;
        cargarFormularios();
    }

    private void cargarFormularios() {
        List<String> nombres = ManejadorArchivos.nombresArchivos(ManejadorFormulario.rutaFormulario);
        if (nombres != null) {
            for (String n : nombres) {
                Formulario fEntrada = formulario.ParseadorFormulario.entrada(n);
                if (fEntrada.getUsuario_creacion().equals(usuario)) {
                    formularios.add(fEntrada);
                }
            }
        }
    }

    public List<Formulario> getFormularios() {
        return this.formularios;
    }

}
