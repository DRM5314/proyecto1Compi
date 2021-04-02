package servidorApp;

import archivos.ManejadorArchivos;
import formulario.ManejadorFormulario;
import formulario.ParseadorFormulario;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rutas.ManejadorRutas;
import solicitudes.AnalizadorSolicitudes;
import usuario.ManejadorUsuario;

public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ManejadorArchivos.nombresArchivos(ManejadorUsuario.getRutaUsuario());        
        ManejadorRutas.inicarRutas();
        ServerSocket servidor = null;
        Socket socket = null;
        final int puerto = 5000;
        DataInputStream input;
        DataOutputStream output;
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor levantado en puerto " + puerto);

            while (true) {

                socket = servidor.accept();

                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
                String tipo = input.readUTF();
                String instrucciones, usuario;
                switch (tipo) {
                    case "instrucciones":
                        instrucciones = input.readUTF();
                        usuario = input.readUTF();
                        StringBuilder respuesta = new StringBuilder();
                        StringBuilder usuarioSalida = new StringBuilder();
                        AnalizadorSolicitudes.analizar(instrucciones, respuesta, usuarioSalida, usuario);
                        output.writeUTF(respuesta.toString());
                        String datosUsuario[] = usuarioSalida.toString().split("\n");
                        if (datosUsuario.length == 3 && ManejadorArchivos.existeArchivo(ManejadorUsuario.rutaUsuario + datosUsuario[0])) {
                            output.writeUTF(datosUsuario[0]);
                            output.writeUTF(datosUsuario[1]);
                            output.writeUTF(datosUsuario[2]);
                        } else {
                            if (usuarioSalida.toString().equals("eliminado")) {
                                output.writeUTF("eliminado");
                            } else {
                                output.writeUTF("");
                            }
                        }
                        break;
                    case "importar":
                        instrucciones = input.readUTF();
                        String idFormulario = input.readUTF();
                        usuario = input.readUTF();             
                        StringBuilder respuestas = new StringBuilder();
                        ParseadorFormulario.entradaImportacion(instrucciones,idFormulario,usuario,respuestas);
                        output.writeUTF(respuestas.toString());
                        break;
                }
                socket.close();

                System.out.println("Cliente terminado");
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
