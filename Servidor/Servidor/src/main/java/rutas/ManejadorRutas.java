package rutas;

import formulario.ManejadorFormulario;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import usuario.ManejadorUsuario;

public class ManejadorRutas {

    public static void inicarRutas()  {
        File directorio = new File(ManejadorUsuario.rutaUsuario+"user");
        if (!directorio.exists()) {
            directorio.mkdir();
            FileWriter salida = null;
            try {
                directorio = new File(ManejadorUsuario.rutaUsuario);
                directorio.mkdirs();
                salida = new FileWriter(ManejadorUsuario.rutaUsuario+"/user");
                String aux = "{\"PASSWORD\":\"user\",\"FECHA_CREACION\":\"1990-01-01\"}";
                salida.write(aux);
                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(ManejadorRutas.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    salida.close();
                } catch (IOException ex) {
                    Logger.getLogger(ManejadorRutas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        directorio = new File(ManejadorFormulario.rutaFormulario);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        directorio = new File(ManejadorFormulario.rutaFormularioWeb);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }
}
