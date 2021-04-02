package solicitudes;

public class ParseadorRespuestas {

    public static String salida(Reportes entrada) {
        String salida = "";
        boolean masDeUnaSolicitud = false;
        if (entrada != null && entrada.getErrores().size()==0) {
            masDeUnaSolicitud = entrada.getSolicitudesProcesadas().size() > 1;
            if (masDeUnaSolicitud) {
                salida += "<!ini_respuestas>\n";
            }
            for (String[] e : entrada.getSolicitudesProcesadas()) {
                salida += "<!ini_respuesta:\"" + e[0] + "\">\n\t{\n";
                String[] infos = e[1].split("\n");
                int contador = 0;
                for (String info : infos) {
                    salida += "\t\t\"info\":" + "\""+info+"\"";
                    contador++;
                    if (infos.length > 1 && contador<infos.length) {
                        salida += ",";
                    }
                    salida += "\n";                    
                }
                salida += "\t}\n<!fin_respuesta>\n";
            }
            if (masDeUnaSolicitud) {
                salida += "\n<!fin_respuestas>";
            }
        }else if(entrada.getErrores().size()>0){
            masDeUnaSolicitud = entrada.getErrores().size() > 1;
            if (masDeUnaSolicitud) {
                salida += "<!ini_respuestas>\n";
            }
            for (String[] e : entrada.getErrores()) {
                salida += "<!ini_respuesta:\"" + e[0] + "\">\n\t{\n";
                String[] infos = e[1].split("\n");
                int contador = 0;
                for (String info : infos) {
                    salida += "\t\t\"info\":" + "\""+info+"\"";
                    contador++;
                    if (infos.length > 1 && contador<infos.length) {
                        salida += ",";
                    }
                    salida += "\n";                    
                }
                salida += "\t}\n<!fin_respuesta>\n";
            }
            if (masDeUnaSolicitud) {
                salida += "\n<!fin_respuestas>";
            }
        }
        return salida;
    }
}
