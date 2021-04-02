package usuario;

import archivos.ManejadorArchivos;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import rutas.ManejadorRutas;


public class ParseadorUsuario {
    public static void salida(Usuario entrada){        
        String salida = 
                "{\n"+               
                "\"PASSWORD\" :\""+entrada.getPassword()+"\"\n";
        if(entrada.getFechaCreacion()!=null)salida+=",\"FECHA_CREACION\" :\""+entrada.getFechaCreacion()+"\"\n";                
        if(entrada.getFechaModificacion()!=null)salida+=",\"FECHA_MODIFICACION\" :\""+entrada.getFechaModificacion()+"\"\n";                
        salida+="}";
        String ruta = ManejadorUsuario.rutaUsuario+entrada.getUsuario();
        ManejadorArchivos.escribirArchivo(salida,ruta);
    }
    public static String parserSalida(Usuario entrada){        
        String salida = 
                "{\n"+               
                "\"PASSWORD\" :\""+entrada.getPassword()+"\"\n";
        if(entrada.getFechaCreacion()!=null)salida+=",\"FECHA_CREACION\" :\""+entrada.getFechaCreacion()+"\"\n";                
        if(entrada.getFechaModificacion()!=null)salida+=",\"FECHA_MODIFICACION\" :\""+entrada.getFechaModificacion()+"\"\n";                
        salida+="}";
        return salida;
    }
    
    public static Usuario entrada(String ruta,String usuario){
        try {
            String textoEntrada = ManejadorArchivos.leerArchivo(ruta);
            //System.out.println("Va a analizar esto: \n"+textoEntrada);
            lexico lex = new lexico(new StringReader(textoEntrada));
            parser p = new parser(lex,usuario);
            p.parse();
            return p.usuario;
        } catch (Exception ex) {
            System.out.println("Error en parser usuario");
            Logger.getLogger(ParseadorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
