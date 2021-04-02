/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class ManejadorParser {
    private String tipoInstruccion;
    private List<String> parametrosSolicitud = new ArrayList<>();
    private List<String> datosSolicitud = new ArrayList<>();
    
    public ManejadorParser(String tipoInstruccion, List<String> datosSolicitud, List<String> parametrosSolicitud) {
        this.tipoInstruccion = tipoInstruccion;
        this.parametrosSolicitud.addAll(parametrosSolicitud);
        this.datosSolicitud.addAll(datosSolicitud);
    }

    public String getTipoInstruccion() {
        return tipoInstruccion;
    }

    public List<String> getParametrosInstruccion() {
        return parametrosSolicitud;
    }

    public List<String> getDatosInstruccion() {
        return datosSolicitud;
    }    
}
