package formulario;

import componente.ManejadorComponente;
import java.util.Collections;
import java.util.List;

public class ProcesadorInstrucciones {
    List <ManejadorParser> manejadorParser;
    private Formulario formulario;
    public ProcesadorInstrucciones(List<ManejadorParser> manejadorParser) {
        this.manejadorParser = manejadorParser;
        Collections.reverse(this.manejadorParser);        
        System.out.println("Trae "+manejadorParser.size()+"  Instrucciones a procesar");
    }
    
    public void procesarInstrucciones (String nombreFormulario,boolean importacion){
        for (int i = 0; i < this.manejadorParser.size(); i++) {                    
            List<String> datosSolicitud = manejadorParser.get(i).getDatosInstruccion();
            List<String> parametrosSolicitud = manejadorParser.get(i).getParametrosInstruccion();
            System.out.println("Va con instruccion "+manejadorParser.get(i).getTipoInstruccion());
            switch(manejadorParser.get(i).getTipoInstruccion()){
                case "RECUPERACION_FORMULARIO":
                    this.formulario = ManejadorFormulario.recuperacionFormulario(parametrosSolicitud,datosSolicitud,nombreFormulario,importacion);
                break;
                
                case "RECUPERACION_COMPONENTE" :                                                                                    
                    this.formulario.agregarComponente(ManejadorComponente.parserCompoente(datosSolicitud.get(parametrosSolicitud.indexOf("ID")),datosSolicitud, parametrosSolicitud));
                break;
            }
        }
    }
    
    public Formulario getFormularioRecuperado(){        
        return this.formulario;
    }
}
