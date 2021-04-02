package solicitudes;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import usuario.ParseadorUsuario;

public class AnalizadorSolicitudes {

    public static void analizar(String entrada, StringBuilder respuestasSalida, StringBuilder usuarioSalida, String usuarioEntrada) {
        try {
            lexico lex = new lexico(new StringReader(entrada));
            Reportes reporte = new Reportes();
            lex.entradaReporte(reporte);
            parser parse = new parser(lex, reporte);
            parse.parse();
            procesadorSolicitudes s = new procesadorSolicitudes(parse.getSolicitudes(), usuarioEntrada);
            if (reporte.getErrores().size() == 0) {
                s.procesarSolicitudes(reporte);
                if (s.isLogeado()) {
                    if(!s.isEliminado())usuarioSalida.append(s.getUsuarioSalida());
                }else if(usuarioEntrada!=null && !usuarioEntrada.isEmpty()){
                    usuarioSalida.append(usuarioEntrada);
                }
                if(s.isEliminado()){
                    usuarioSalida.append("eliminado");
                }
            } else {
                System.out.println("No procesa nada, trae errores");
            }
            //reporte.getInfo();
            String salida = ParseadorRespuestas.salida(reporte);
            respuestasSalida.append(salida);
        } catch (Exception ex) {
            Logger.getLogger(ParseadorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
