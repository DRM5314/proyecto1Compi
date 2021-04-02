package aplicacioncliente;

import java.awt.Component;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AnalizadorRespuestas {

    public static void analizar(String entrada, JTable entradaTabla, Component entradaComponente,boolean importar) {
        if (!entrada.isEmpty()) {
            lexico lex = new lexico(new StringReader(entrada));
            parser parse = new parser(lex);
            try {
                parse.parse();
                agregarResultadoTabla(entradaTabla, parse.getRespuestas());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(entradaComponente, "Error en el parser de respuestas");
            }
        }else{
            if(true){
                JOptionPane.showMessageDialog(entradaComponente, "La importacion de formulario trae erroes, porfavor revise su estrucura");
            }else{
                JOptionPane.showMessageDialog(entradaComponente, "No trae respuestas desde el servidor");
            }
        }
    }

    public static void agregarResultadoTabla(JTable tabla, List<Respuestas> respuestas) {
        DefaultTableModel m = (DefaultTableModel) tabla.getModel();
        for (Respuestas r : respuestas) {
            String filaNueva[] = new String[3];
            filaNueva[0] = r.getTipo();
            int contador = 0;
            for (String l : r.getListadoDatos()) {
                if (contador == 0) {
                    filaNueva[1] = l;
                    filaNueva[2] = "---------------";
                    m.addRow(filaNueva);
                } else {
                    filaNueva[0] = "";
                    filaNueva[1] = "-------------------------------------------------------->";
                    filaNueva[2] = l;
                    m.addRow(filaNueva);
                }
                contador++;
            }

        }
        tabla.setModel(m);
    }
}
