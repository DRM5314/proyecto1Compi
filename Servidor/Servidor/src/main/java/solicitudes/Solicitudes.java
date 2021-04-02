package solicitudes;

import java.util.ArrayList;
import java.util.List;

public class Solicitudes {
    private String tipoInstruccion,tipoInstruccionPadre;
    private List<String> parametrosInstruccion = new ArrayList<>();
    private List<String> datosInstruccion = new ArrayList<>();
    private final int [] posXY;
    public Solicitudes(String tipoInstruccionPadre,String tipoInstruccion, List<String> datosInstruccion, List<String> parametrosInstruccion,int [] posXY ) {
        this.tipoInstruccion = tipoInstruccion;
        this.parametrosInstruccion.addAll(parametrosInstruccion);
        this.datosInstruccion.addAll(datosInstruccion);
        this.tipoInstruccionPadre = tipoInstruccionPadre;
        this.posXY = posXY;
        System.out.println("--Solicitud "+tipoInstruccion+" Recibida--");
    }

    public int[] getPosXY() {        
        return posXY;
    }

    public String getTipoInstruccionPadre() {
        return tipoInstruccionPadre;
    }

    public String getTipoInstruccion() {
        return tipoInstruccion;
    }

    public List<String> getParametrosInstruccion() {
        return parametrosInstruccion;
    }

    public List<String> getDatosInstruccion() {
        return datosInstruccion;
    }
    
    
}
