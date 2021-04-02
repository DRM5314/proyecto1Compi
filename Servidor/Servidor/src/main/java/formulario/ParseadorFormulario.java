package formulario;

import archivos.ManejadorArchivos;
import java.io.StringReader;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import solicitudes.ParseadorRespuestas;
import solicitudes.Reportes;

public class ParseadorFormulario {

        public static String Salida(Formulario entrada, boolean recuperado) {
        if (recuperado && entrada.getComponentes().size() > 0) {
            Collections.reverse(entrada.getComponentes());
        }
        String salida
                = "{\n"
                + "\"TITULO\" :\"" + entrada.getTitulo() + "\",\n"
                + "\"NOMBRE\" :\"" + entrada.getNombre() + "\",\n"
                + "\"TEMA\" :\"" + entrada.getTema() + "\",\n"
                + "\"USUARIO_CREACION\" :\"" + entrada.getUsuario_creacion() + "\",\n"
                + "\"FECHA_CREACION\" :\"" + entrada.getFechaCreacion() + "\"";

        if (entrada.getComponentes().size() > 0) {
            String componentes = ",\n\t\"ESTRUCTURA\" :(";
            for (int i = 0; i < entrada.getComponentes().size(); i++) {
                componente.Componente comp1 = entrada.getComponentes().get(i);
                componentes
                        += "\n\t\t{\n"
                        + "\t\t\"ID\" :\"" + comp1.getId() + "\",\n"
                        + "\t\t\"FORMULARIO\" :\"" + comp1.getFormulario() + "\",\n"
                        + "\t\t\"TEXTO_VISIBLE\" :\"" + comp1.getTextoVisible() + "\",\n"
                        + "\t\t\"INDICE\" :\"" + (i + 1) + "\",\n"
                        + "\t\t\"CLASE\" :\"" + comp1.getClase() + "\"";
                componentes += parserClase(comp1) + "\n\t\t}\n";
                if ((i + 1) < entrada.getComponentes().size()) {
                    componentes += ",\n";
                }
            }
            componentes += "\t)\n";
            salida += componentes;
        } else {
            salida += "\n";
        }
        salida += "}";
        return salida;
    }

    private static String parserClase(componente.Componente entrada) {
        String retorno = "\n";
        switch (entrada.getClase()) {
            case "CAMPO_TEXTO":
                retorno
                        += ",\t\t\"NOMBRE_CAMPO\" :\"" + entrada.getNombreCampo() + "\"";
                if (entrada.getAlineacion() != null) {
                    retorno += ",\n\t\t\"ALINEACION\" :\"" + entrada.getAlineacion() + "\"";
                }
                if (entrada.getRequerido() != null) {
                    retorno += ",\n\t\t\"REQUERIDO\" :\"" + entrada.getRequerido() + "\"";
                }
                return retorno;
            case "FICHERO":
                retorno
                        += ",\t\t\"NOMBRE_CAMPO\" :\"" + entrada.getNombreCampo() + "\"";
                if (entrada.getAlineacion() != null) {
                    retorno += ",\n\t\t\"ALINEACION\" :\"" + entrada.getAlineacion() + "\"";
                }
                if (entrada.getRequerido() != null) {
                    retorno += ",\n\t\t\"REQUERIDO\" :\"" + entrada.getRequerido() + "\"";
                }
                return retorno;
            case "AREA_TEXTO":
                retorno
                        += ",\t\t\"FILAS\" :\"" + entrada.getFila() + "\",\n"
                        + "\t\t\"COLUMNAS\" :\"" + entrada.getColumna() + "\",\n"
                        + "\t\t\"NOMBRE_CAMPO\" :\"" + entrada.getNombreCampo() + "\"";
                if (entrada.getAlineacion() != null) {
                    retorno += ",\n\t\t\"ALINEACION\" :\"" + entrada.getAlineacion() + "\"";
                }
                if (entrada.getRequerido() != null) {
                    retorno += ",\n\t\t\"REQUERIDO\" :\"" + entrada.getRequerido() + "\"";
                }
                return retorno;
            case "CHECKBOX":
                retorno
                        += ",\t\t\"OPCIONES\" :\"" + entrada.getOpciones() + "\",\n"
                        + "\t\t\"NOMBRE_CAMPO\" :\"" + entrada.getNombreCampo() + "\"";
                if (entrada.getAlineacion() != null) {
                    retorno += ",\n\t\t\"ALINEACION\" :\"" + entrada.getAlineacion() + "\"";
                }
                if (entrada.getRequerido() != null) {
                    retorno += ",\n\t\t\"REQUERIDO\" :\"" + entrada.getRequerido() + "\"";
                }
                return retorno;
            case "RADIO":
                retorno
                        += ",\t\t\"OPCIONES\" :\"" + entrada.getOpciones() + "\",\n"
                        + "\t\t\"NOMBRE_CAMPO\" :\"" + entrada.getNombreCampo() + "\"";
                if (entrada.getAlineacion() != null) {
                    retorno += ",\n\t\t\"ALINEACION\" :\"" + entrada.getAlineacion() + "\"";
                }
                if (entrada.getRequerido() != null) {
                    retorno += ",\n\t\t\"REQUERIDO\" :\"" + entrada.getRequerido() + "\"";
                }
                return retorno;
            case "IMAGEN":
                retorno
                        += ",\t\t\"URL\" :\"" + entrada.getUrl() + "\"";
                if (entrada.getAlineacion() != null) {
                    retorno += ",\n\t\t\"ALINEACION\" :\"" + entrada.getAlineacion() + "\"";
                }
                if (entrada.getRequerido() != null) {
                    retorno += ",\n\t\t\"REQUERIDO\" :\"" + entrada.getRequerido() + "\"";
                }
                return retorno;
            case "COMBO":
                retorno
                        += ",\t\t\"OPCIONES\" :\"" + entrada.getOpciones() + "\",\n"
                        + "\t\t\"NOMBRE_CAMPO\" :\"" + entrada.getNombreCampo() + "\"";
                if (entrada.getAlineacion() != null) {
                    retorno += ",\n\t\t\"ALINEACION\" :\"" + entrada.getAlineacion() + "\"";
                }
                if (entrada.getRequerido() != null) {
                    retorno += ",\n\t\t\"REQUERIDO\" :\"" + entrada.getRequerido() + "\"";
                }
                return retorno;
            case "BOTON":
                if (entrada.getAlineacion() != null) {
                    retorno += ",\t\t\"ALINEACION\" :\"" + entrada.getAlineacion() + "\"";
                }
                if (entrada.getRequerido() != null) {
                    retorno += ",\n\t\t\"REQUERIDO\" :\"" + entrada.getRequerido() + "\"";
                }
                return retorno;
        }
        return retorno;
    }

    public static Formulario entrada(String idFormulario) {
        try {
            String entrada = (String) archivos.ManejadorArchivos.leerArchivo(ManejadorFormulario.rutaFormulario + idFormulario);
            //System.out.println("----En entrada esta esto----\n" + entrada);
            lexico lex = new lexico(new StringReader(entrada));
            parser p = new parser(lex);
            p.parse();
            ProcesadorInstrucciones a = new ProcesadorInstrucciones(p.getInstrucciones());
            a.procesarInstrucciones(idFormulario,false);
            Formulario salida = a.getFormularioRecuperado();
            Collections.reverse(salida.getComponentes());
            Collections.reverse(salida.getNomvreComponentes());
            return salida;
        } catch (Exception ex) {
            Logger.getLogger(ParseadorFormulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void entradaImportacion(String formEntradaTexto, String idFormulario, String usuario, StringBuilder respuestas) {
        Reportes reportes = new Reportes();
        if (!ManejadorArchivos.existeArchivo(ManejadorFormulario.rutaFormulario + idFormulario)) {
            try {
                //System.out.println("----En entrada esta esto----\n" + formEntradaTexto);
                lexico lex = new lexico(new StringReader(formEntradaTexto));
                parser p = new parser(lex);
                p.parse();
                ProcesadorInstrucciones a = new ProcesadorInstrucciones(p.getInstrucciones());
                a.procesarInstrucciones(idFormulario,true);
                Formulario fSalida = a.getFormularioRecuperado();
                fSalida.setUsuario_creacion(usuario);
                String formulario = Salida(fSalida, true);
                ManejadorArchivos.escribirArchivo(formulario, ManejadorFormulario.rutaFormulario + idFormulario);
                String info = "Importo formulario "+idFormulario+" con exito a la base de datos";
                reportes.agregarSolicitudProcesada("NUEVO_FORMULARIO", info);
            } catch (Exception ex) {
                reportes.agregarSolicitudProcesada("error", "Error en formulario\n no se proceso la solicitud");
            }
        } else {
            reportes.agregarSolicitudProcesada("NUEVO_FORMULARIO", "\nFormulario ya existe con ese nombre\nCambie el nombre de archivo");
        }        
        respuestas.append(ParseadorRespuestas.salida(reportes));
        //System.out.println("Salio con estas instrucciones "+respuestas.toString());
    }

}
