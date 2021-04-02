package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ManejadorArchivos extends javax.swing.JFrame {

    public static boolean existeArchivo(String ruta) {
        File archivo = new File(ruta);
        //System.out.println("La ruta recibida en existe archivo es " + archivo.getAbsolutePath());
        if (archivo.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static void guardarObjeto(Object objeto, String ruta) {
        try {
            File a = new File(ruta);
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta));
            salida.writeObject(objeto);
            //System.out.println("Guardo objeto en " + a.getAbsolutePath());
            //System.out.println("Guardo objeto en (ruta relativa): " + a.getPath());
            salida.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object leerObjeto(String ruta) throws FileNotFoundException, IOException, ClassNotFoundException {
        return new ObjectInputStream(new FileInputStream(ruta)).readObject();
    }

    public static void borrarObjeto(String ruta) {
        File a = new File(ruta);
        a.delete();
    }

    public static void escribirArchivo(String texto, String ruta) {
        File a = new File(ruta);
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(a);
            // Escribimos linea a linea en el fichero            
            fichero.write(texto);
            fichero.close();
        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
    }

    public static String leerArchivo(String ruta) {
        String retorno = "\n", aux = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            while ((aux = br.readLine()) != null) {
                //System.out.println(retorno);            
                retorno += aux + "\n";
            }

        } catch (IOException e) {
            retorno = null;
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return retorno;
    }

    public static List<String> nombresArchivos(String ruta) {
        System.out.println("ingreso a nombres archivos");
        if (ManejadorArchivos.existeArchivo(ruta)) {
            List<String> retorno = new ArrayList<>();
            File carpeta = new File(ruta);
            File [] listadoArchivos = carpeta.listFiles();
            for (File l : listadoArchivos) {
                retorno.add(l.getName());
            }           
            return retorno;
        }
        return null;
    }
    public static void escribirArchivoFileChoser (String extencion,String auxGuardarTexto){        
        if(!auxGuardarTexto.isEmpty()){
            JFileChooser jf = new JFileChooser("../");
            //FileNameExtensionFilter filtro = new FileNameExtensionFilter("LNZ, CLRS, TMP & PNT","lnz","clrs","tmp","pnt");
            //jf.setFileFilter(filtro);
            if(jf.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                File archivo = jf.getSelectedFile(); // obtiene el archivo seleccionado
                String rutaOrigen = archivo.getAbsolutePath();
                if(!archivo.exists()){
                    try {
                        new File(rutaOrigen+"."+extencion).createNewFile();
                        archivo = new File(rutaOrigen+"."+extencion);
                    } catch (IOException ex) {
                    }
                }else {
                    if(JOptionPane.showConfirmDialog(null, "Ya existe archivo, desea reemplazar?")==JOptionPane.NO_OPTION){                        
                        escribirArchivoFileChoser(extencion,auxGuardarTexto);
                        return;
                    }
                }
                // muestra error si es inválido
                if ((archivo != null) && (!archivo.getName().equals(""))) {
                    try {
                        FileWriter escribir = new FileWriter(archivo);
                        escribir.write(auxGuardarTexto);
                        //System.out.println("va a guardar"+auxGuardarTexto.getText());
                        escribir.close();
                    } catch (IOException ex) {
                        //Logger.getLogger(manejador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
    }
}
