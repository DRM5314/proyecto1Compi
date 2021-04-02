package aplicacioncliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AplicacionCliente {

    public static void main(String[] args) {
        //AnalizadorRespuestas.analizar("");
        Editor e = new Editor();
        String retorno = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        System.out.println(retorno);
        /*
        final String host = "192.168.1.14";
        final int puerto = 5000;
        DataInputStream input;
        DataOutputStream output;
        
        try {
            Socket socket = new Socket(host, puerto);
            
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            
            output.writeUTF("DAVID MANDA SALUDOS EN EL SERVIDOR");
            
            String mensaje = input.readUTF();
            
            System.out.println("El servidor dice esto: \n"+mensaje);
            
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }
    
}
