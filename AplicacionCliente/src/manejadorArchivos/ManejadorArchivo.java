package manejadorArchivos;


import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ManejadorArchivo {
    private static String nombreRuta ;
    public static String recuperarArchivo1(Component entrada){
        JFileChooser abrir = new JFileChooser("../");
        String salida = "";
        //FileNameExtensionFilter filtro = new FileNameExtensionFilter("LNZ, CLRS, TMP & PNT","lnz","clrs","tmp","pnt");
        //abrir.setFileFilter(filtro);
        if(abrir.showOpenDialog(entrada) == JFileChooser.APPROVE_OPTION){
            try {
                Scanner textoRecuperado = new Scanner(abrir.getSelectedFile());
                nombreRuta = abrir.getSelectedFile().getName();
                int contador = 0;
                while (textoRecuperado.hasNextLine()) {                    
                    String aux = textoRecuperado.nextLine()+"\n";
                    salida +=aux;
                }
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(manejador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else nombreRuta = null;
        return salida;
    }
    public static String [] recuperarArchivo(Component entrada){
        JFileChooser abrir = new JFileChooser("../");
        String retorno [] = null;
        //FileNameExtensionFilter filtro = new FileNameExtensionFilter("LNZ, CLRS, TMP & PNT","lnz","clrs","tmp","pnt");
        //abrir.setFileFilter(filtro);
        if(abrir.showOpenDialog(entrada) == JFileChooser.APPROVE_OPTION){
            try {
                File archivoSeleccionado = abrir.getSelectedFile();
                Scanner textoRecuperado = new Scanner(archivoSeleccionado);                
                nombreRuta = abrir.getSelectedFile().getName();
                retorno = new String [2];
                retorno [1] = archivoSeleccionado.getName();
                retorno[0] = "";
                while (textoRecuperado.hasNextLine()) {
                    String aux = textoRecuperado.nextLine()+"\n";
                    retorno [0]+= aux;                    
                }                
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(manejador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retorno;
    }
    
    public String getNombreRuta() {
        return nombreRuta;
    }
    public static void escribirArchivo (JTextArea auxGuardarTexto,String extencion,Component entrada){        
        if(!auxGuardarTexto.getText().isEmpty()){
            JFileChooser jf = new JFileChooser("../");
            //FileNameExtensionFilter filtro = new FileNameExtensionFilter("LNZ, CLRS, TMP & PNT","lnz","clrs","tmp","pnt");
            //jf.setFileFilter(filtro);
            if(jf.showOpenDialog(entrada) == JFileChooser.APPROVE_OPTION){
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
                        escribirArchivo(auxGuardarTexto, extencion,entrada);
                        return;
                    }
                }
                // muestra error si es inválido
                if ((archivo != null) && (!archivo.getName().equals(""))) {
                    try {
                        FileWriter escribir = new FileWriter(archivo);
                        escribir.write(auxGuardarTexto.getText());
                        System.out.println("va a guardar"+auxGuardarTexto.getText());
                        escribir.close();
                    } catch (IOException ex) {
                        //Logger.getLogger(manejador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
    }
    public static void abrirarchivo(String archivo){
    try {
       File objetofile = new File (archivo);
       Desktop.getDesktop().open(objetofile);

    }catch (IOException ex) {
   //System.out.println(ex);
    }
    }
    public static boolean existeArchivo(String ruta) {
        File archivo = new File(ruta);
        System.out.println("La ruta recibida en existe archivo es " + archivo.getAbsolutePath());
        if (archivo.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
