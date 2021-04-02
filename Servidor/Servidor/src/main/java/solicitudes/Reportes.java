package solicitudes;

import java.util.ArrayList;
import java.util.List;

public class Reportes {

    public List<String[]> getErrores() {
        return errores;
    }

    public List<String[]> getSolicitudesProcesadas() {
        return solicitudesProcesadas;
    }

    private List<String[]> errores = new ArrayList<>();
    private List<String[]> solicitudesProcesadas = new ArrayList<>();

    public void agregarError(String tipo, String dato, int posLinea, int posColumna) {
        dato+="\nTipo :"+tipo + "\nLinea: " + posLinea + "\nColumna: " + posColumna;
        errores.add(new String[]{"error", dato});
    }
    public void agregarSolicitudProcesada(String tipo,String info){
        solicitudesProcesadas.add(new String []{tipo,info});
    }
    public void getInfo(){
        System.out.println("Errores #"+errores.size());
        for (String[] e : errores) {
            System.out.println(e[0]+" "+e[1]);
        }
        System.out.println("Solicitudes procesadas #"+solicitudesProcesadas.size());
        for (String[] s : solicitudesProcesadas) {
            System.out.println(s[0]+" "+s[1]);
        }
    }
}
