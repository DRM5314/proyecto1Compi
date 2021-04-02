package componente;

import archivos.ManejadorArchivos;
import formulario.Formulario;
import formulario.ManejadorFormulario;
import formulario.ParseadorFormulario;
import java.util.ArrayList;
import java.util.List;
import solicitudes.Reportes;

public class ManejadorComponente {

    public static boolean parametrosVerificador(String tipo, List<String> datos, List<String> parametrosEntrada) {
        List<String> p = parametrosEntrada;
        boolean obligatorios = p.contains("ID") && p.contains("FORMULARIO") && p.contains("CLASE") && p.contains("TEXTO_VISIBLE");
        switch (tipo) {
            case "AGREGAR_COMPONENTE":
                if (obligatorios && parametrosVerificadorClase(parametrosEntrada, datos.get(parametrosEntrada.indexOf("CLASE")), 0)) {
                    return true;
                }
                break;
            case "RECUPERACION_COMPONENTE":
                obligatorios = p.contains("ID") && p.contains("FORMULARIO") && p.contains("CLASE")
                        && p.contains("TEXTO_VISIBLE") && p.contains("INDICE");
                if (obligatorios && parametrosVerificadorClase(parametrosEntrada, datos.get(parametrosEntrada.indexOf("CLASE")), 1)) {
                    return true;
                }
                break;
            case "ELIMINAR_COMPONENTE":
                obligatorios = p.contains("ID") && p.contains("FORMULARIO");
                if (obligatorios && parametrosEntrada.size() <= 2) {
                    return true;
                }
                break;
            case "MODIFICAR_COMPONENTE":
                obligatorios = p.contains("ID") && p.contains("FORMULARIO");
                if (obligatorios) {
                    return true;
                }
                break;
        }
        return false;
    }

    public static String parametrosObligatorios(String tipoCrud) {
        String aux = "";
        switch (tipoCrud) {
            case "AGREGAR_COMPONENTE":
                aux += "Parametros obligatorios \n\tID FORMULARIO\n\tTEXTO_VISIBLE\n\tCLASE Y sus parametros correspondientes";
                return aux;
            case "ELIMINAR_COMPONENTE":
                aux += "Parametros obligatorios\n\tID\n\tFORMULARIO";
                return aux;
            case "MODIFICAR_COMPONENTE":
                aux += "Parametros obligatorios\n\tID\n\tFORMULARIO\n\tAcompañado de\n\t\tTitulo o nombre o texto visible o indice\n\t\tClase Con sus parametros correspondientes";
                return aux;
        }
        return null;
    }

    private static boolean parametrosVerificadorClase(List<String> parametros, String tipoClase, int i) {
//i hace referencia a cuando se recupera el parametro indice es obligatorio        
//obligatorios 5
        boolean opcionalUno = parametros.contains("ALINEACION") || parametros.contains("REQUERIDO");
        boolean opcionalDos = parametros.contains("ALINEACION") && parametros.contains("REQUERIDO");
        boolean opciones = parametros.contains("OPCIONES") && parametros.contains("NOMBRE_CAMPO");
        switch (tipoClase) {
            case "CAMPO_TEXTO":
                opciones = parametros.contains("NOMBRE_CAMPO");
                if (opciones && parametros.size() <= (5 + i)
                        || (opciones && opcionalUno && parametros.size() <= (6 + i))
                        || (opciones && opcionalDos && parametros.size() <= (7 + i))) {
                    return true;
                }
                break;
            case "FICHERO":
                opciones = parametros.contains("NOMBRE_CAMPO");
                if (opciones && parametros.size() <= (5 + i)
                        || (opciones && opcionalUno && parametros.size() <= (6 + i))
                        || (opciones && opcionalDos && parametros.size() <= (7 + i))) {
                    return true;
                }
                break;
            case "AREA_TEXTO":
                opciones = parametros.contains("FILAS") && parametros.contains("COLUMNAS") && parametros.contains("NOMBRE_CAMPO");
                if (opciones && parametros.size() <= (7 + i)
                        || (opciones && opcionalUno && parametros.size() <= (8 + i))
                        || (opciones && opcionalDos && parametros.size() <= (9 + i))) {
                    return true;
                }
                break;
            case "CHECKBOX":
                if (opciones && parametros.size() <= (6 + i)
                        || (opciones && opcionalUno && parametros.size() <= (7 + i))
                        || (opciones && opcionalDos && parametros.size() <= (8 + i))) {
                    return true;
                }
                break;
            case "RADIO":
                if (opciones && parametros.size() <= (6 + i)
                        || (opciones && opcionalUno && parametros.size() <= (7 + i))
                        || (opciones && opcionalDos && parametros.size() <= (8 + i))) {
                    return true;
                }
                break;
            case "IMAGEN":
                opciones = parametros.contains("URL");
                if (opciones && parametros.size() <= (5 + i)
                        || (opciones && opcionalUno && parametros.size() <= (6 + i))
                        || (opciones && opcionalDos && parametros.size() <= (7 + i))) {
                    return true;
                }
                break;
            case "COMBO":
                if (opciones && parametros.size() <= (6 + i)
                        || (opciones && opcionalUno && parametros.size() <= (7 + i))
                        || (opciones && opcionalDos && parametros.size() <= (8 + i))) {
                    return true;
                }
                break;

            case "BOTON":
                if (parametros.size() <= (4 + i)
                        || (opcionalUno && parametros.size() <= (5 + i))
                        || (opcionalDos && parametros.size() <= (6 + i))) {
                    return true;
                }
                break;
        }
        return false;
    }

    public static void consultaComponente(String tipo, List<String> datos, List<String> parametros, Reportes reporteEnrada,int posxy[]) {
        String idFormulario = datos.get(parametros.indexOf("FORMULARIO"));
        String idComponente = datos.get(parametros.indexOf("ID"));
        String ruta = ManejadorFormulario.rutaFormulario + idFormulario;
        String info;
        Formulario aux = exitenciaFormulario(ruta, idFormulario);
        if (aux != null) {
            switch (tipo) {
                case "AGREGAR_COMPONENTE":
                    System.out.println("--Completo agreagar componente--");
                    if (!aux.getNomvreComponentes().contains(idComponente)) {
                        aux.agregarComponente(parserCompoente(idComponente, datos, parametros));
                        info = idComponente + " agregado a " + idFormulario;
                        reporteEnrada.agregarSolicitudProcesada(tipo, info);                        
                        //System.out.println("--Componente agregado a form--");
                    } else {
                        info = "No se agrego " + idComponente + " en " + idFormulario+" ya existe";                        
                        info +="\nlinea: "+posxy[0]+"\nColumna: "+posxy[1];
                        reporteEnrada.agregarSolicitudProcesada(tipo, info);
                        //System.out.println("--Componente con id repetido, no agrego a form--");
                    }
                    break;
                case "RECUPERADOR_COMPONENTE":
                    aux.agregarComponente(parserCompoente(tipo, datos, parametros));
                    System.out.println("--Completo agreagar componente en recuperacion--");
                    reescribir(ruta, aux, true);
                    return;
                case "ELIMINAR_COMPONENTE":
                    System.out.println("--Completo agreagar componente--");
                    if (aux.getNomvreComponentes().contains(idComponente)) {
                        int posEliminar = aux.getNomvreComponentes().indexOf(idComponente);
                        aux.eliminarComponente(posEliminar);
                        info = idComponente + " eliminado en " + idFormulario;
                        reporteEnrada.agregarSolicitudProcesada(tipo, info);
                        //System.out.println("--Componente eliminado--");
                    } else {
                        info = "No existe " + idComponente + " en " + idFormulario;
                        info +="\nlinea: "+posxy[0]+"\nColumna: "+posxy[1];
                        reporteEnrada.agregarSolicitudProcesada(tipo, info);
                        //System.out.println("--Componente no existe--");
                    }
                    //System.out.println("--Completo eliminar componente--");
                    break;
                case "MODIFICAR_COMPONENTE":
                    if (aux.getNomvreComponentes().contains(idComponente)) {
                        int posModificar = aux.getNomvreComponentes().indexOf(idComponente);
                        info =idComponente + " en " + idFormulario;
                        StringBuilder auxInfo = new StringBuilder(" Modificaciones en: \n");
                        if (!parametros.contains("CLASE")) {
                            modificadorComponente(parametros, datos, aux, posModificar, auxInfo);
                        } else {
                            modificadorConClase(parametros, datos, aux, posModificar, auxInfo,posxy);
                        }
                        if (parametros.contains("INDICE")) {
                            modificarIndiceComponente(aux, posModificar, datos, parametros, auxInfo);
                        }
                        //System.out.println("--Componente modificado--");
                        info = info + " " + auxInfo.toString();
                        reporteEnrada.agregarSolicitudProcesada(tipo, info);
                    } else {
                        info = "No existe " + idComponente + " en " + idFormulario;
                        info +="\nlinea: "+posxy[0]+"\nColumna: "+posxy[1];
                        reporteEnrada.agregarSolicitudProcesada(tipo, info);
                    }
                    //System.out.println("--Completo modificar componente--");
                    break;
            }
            reescribir(ruta, aux, false);
        } else {
            reporteEnrada.agregarSolicitudProcesada(tipo, idFormulario+" no existe");
        }
    }

    private static void modificadorConClase(List<String> parametros, List<String> datos, Formulario entrada, int posModificar, StringBuilder infoEntrada,int [] posxy) {
        String tipo = datos.get(parametros.indexOf("CLASE"));
        List<String> p = parametros;
        Componente aux = entrada.getComponente(posModificar);
        boolean obligatorios = p.contains("ID") && p.contains("FORMULARIO");
        if (obligatorios && parametrosVerificadorObjeto_O_Entrada(parametros, aux, tipo)) {
            //System.out.println("Si va a modificar por " + tipo);
            infoEntrada.append("Clase "+aux.getClase()+" a "+ tipo + "\n");
            String id = datos.get(parametros.indexOf("ID"));
            agregarFaltantes(parametros, datos, tipo, aux);
            aux = parserCompoente(id, datos, parametros);
            entrada.setComponente(posModificar, aux);
        } else {
            //System.out.println("No modifico, no pertenece a la clase o no venian los minimos");
            infoEntrada.append("Clase no modificada\nNo pertenece a la clase\nO no viene con parametros minimos de la misma");
            infoEntrada.append("\nlinea: "+posxy[0]+"\nColumna: "+posxy[1]);
        }

    }

    private static void agregarFaltantes(List<String> parametros, List<String> datos, String tipo, Componente entrada) {
        if (!parametros.contains("TEXTO_VISIBLE") && entrada.getTextoVisible() != null) {
            parametros.add("TEXTO_VISIBLE");
            datos.add(entrada.getTextoVisible());
        }
        if (!parametros.contains("ALINEACION") && entrada.getAlineacion() != null) {
            parametros.add("ALINEACION");
            datos.add(entrada.getAlineacion());
        }
        if (!parametros.contains("REQUERIDO") && entrada.getRequerido() != null) {
            parametros.add("REQUERIDO");
            datos.add(entrada.getRequerido());
        }
        switch (tipo) {
            case "CAMPO_TEXTO":
                if (!parametros.contains("NOMBRE_CAMPO")) {
                    parametros.add("NOMBRE_CAMPO");
                    datos.add(entrada.getNombreCampo());
                }
                break;
            case "FICHERO":
                if (!parametros.contains("NOMBRE_CAMPO")) {
                    parametros.add("NOMBRE_CAMPO");
                    datos.add(entrada.getNombreCampo());
                }
                break;
            case "AREA_TEXTO":
                if (!parametros.contains("FILAS")) {
                    parametros.add("FILAS");
                    datos.add(entrada.getFila());
                }
                if (!parametros.contains("COLUMNAS")) {
                    parametros.add("COLUMNAS");
                    datos.add(entrada.getColumna());
                }
                if (!parametros.contains("NOMBRE_CAMPO")) {
                    parametros.add("NOMBRE_CAMPO");
                    datos.add(entrada.getNombreCampo());
                }
                break;
            case "CHECKBOX":
                if (!parametros.contains("OPCIONES")) {
                    parametros.add("OPCIONES");
                    datos.add(entrada.getOpciones());
                }
                if (!parametros.contains("NOMBRE_CAMPO")) {
                    parametros.add("NOMBRE_CAMPO");
                    datos.add(entrada.getNombreCampo());
                }
                break;
            case "RADIO":
                if (!parametros.contains("OPCIONES")) {
                    parametros.add("OPCIONES");
                    datos.add(entrada.getOpciones());
                }
                if (!parametros.contains("NOMBRE_CAMPO")) {
                    parametros.add("NOMBRE_CAMPO");
                    datos.add(entrada.getNombreCampo());
                }
                break;
            case "IMAGEN":
                if (!parametros.contains("URL")) {
                    parametros.add("URL");
                    datos.add(entrada.getUrl());
                }
                break;
            case "COMBO":
                if (!parametros.contains("OPCIONES")) {
                    parametros.add("OPCIONES");
                    datos.add(entrada.getOpciones());
                }
                if (!parametros.contains("NOMBRE_CAMPO")) {
                    parametros.add("NOMBRE_CAMPO");
                    datos.add(entrada.getNombreCampo());
                }
                break;
            case "BOTON":
                if (!parametros.contains("TEXTO_VISIBLE")) {
                    parametros.add("TEXTO_VISIBLE");
                    datos.add(entrada.getNombreCampo());
                }
                break;
        }

    }

    private static boolean parametrosVerificadorObjeto_O_Entrada(List<String> parametros, Componente entrada, String tipoClase) {
        boolean opciones, opciones1, opciones2,noPertenecienteClase;
        switch (tipoClase) {
            case "CAMPO_TEXTO":
                opciones = (parametros.contains("NOMBRE_CAMPO") || (entrada.getNombreCampo() != null));
                noPertenecienteClase = parametros.contains("FILAS") || parametros.contains("COLUMNAS") || parametros.contains("OPCIONES") || parametros.contains("URL");
                return (opciones && !noPertenecienteClase);
            case "FICHERO":
                opciones = (parametros.contains("NOMBRE_CAMPO") || (entrada.getNombreCampo() != null));
                noPertenecienteClase = parametros.contains("FILAS") || parametros.contains("COLUMNAS") || parametros.contains("OPCIONES") || parametros.contains("URL");
                return (opciones && !noPertenecienteClase);
            case "AREA_TEXTO":
                opciones = parametros.contains("FILAS") || (entrada.getFila() != null);
                opciones1 = parametros.contains("COLUMNAS") || (entrada.getColumna() != null);
                opciones2 = parametros.contains("NOMBRE_CAMPO") || (entrada.getNombreCampo() != null);
                noPertenecienteClase = parametros.contains("OPCIONES") || parametros.contains("URL");
                return (opciones && opciones1 && opciones2 && !noPertenecienteClase);
            case "CHECKBOX":
                opciones = parametros.contains("OPCIONES") || (entrada.getOpciones() != null);                
                opciones1 = parametros.contains("NOMBRE_CAMPO") || (entrada.getNombreCampo() != null);
                noPertenecienteClase = parametros.contains("FILAS") || parametros.contains("COLUMNAS") || parametros.contains("URL");
                return (opciones && opciones1 && !noPertenecienteClase);
            case "RADIO":
                opciones = parametros.contains("OPCIONES") || (entrada.getOpciones() != null);
                opciones1 = parametros.contains("NOMBRE_CAMPO") || (entrada.getNombreCampo() != null);
                noPertenecienteClase = parametros.contains("FILAS") || parametros.contains("COLUMNAS") || parametros.contains("URL");
                return (opciones && opciones1 && !noPertenecienteClase);
            case "IMAGEN":                
                opciones = parametros.contains("URL") || (entrada.getUrl() != null);
                noPertenecienteClase = parametros.contains("FILAS") || parametros.contains("COLUMNAS") || parametros.contains("OPCIONES");
                return (opciones && !noPertenecienteClase);
            case "COMBO":
                opciones = parametros.contains("OPCIONES") || (entrada.getOpciones() != null);
                opciones1 = parametros.contains("NOMBRE_CAMPO") || (entrada.getNombreCampo() != null);
                noPertenecienteClase = parametros.contains("FILAS") || parametros.contains("COLUMNAS") || parametros.contains("URL");
                return (opciones && opciones1 && !noPertenecienteClase);
            case "BOTON":
                noPertenecienteClase = parametros.contains("FILAS") || parametros.contains("COLUMNAS") || parametros.contains("OPCIONES") || parametros.contains("URL");
                return !noPertenecienteClase;
        }
        return false;
    }

    private static void modificarIndiceComponente(Formulario entrada, int posModificar, List<String> datos, List<String> parametros, StringBuilder infoEntrada) {
        Componente aux = entrada.getComponente(posModificar);
        entrada.eliminarComponente(posModificar);
        int pos = Integer.parseInt(datos.get(parametros.indexOf("INDICE")));
        infoEntrada.append("Indice " + aux.getINDICE()+" a "+pos);
        if (pos > 0) {
            pos = pos - 1;
        }
        if (pos > entrada.getComponentes().size()) {
            pos = entrada.getComponentes().size() - 1;
            infoEntrada.append("\nNo aceptado, se cambio por" + pos);
            if (pos == 0) {
                pos++;
            }
        }
        infoEntrada.append("\n");
        entrada.setIndiceComponente(pos, aux);
        posModificar = pos;
    }

    private static void modificadorComponente(List<String> parametros, List<String> datos, Formulario entrada, int posModificar, StringBuilder infoEntrada) {
        boolean indice = parametros.contains("INDICE");
        boolean texto_visible = parametros.contains("TEXTO_VISIBLE");
        boolean alineacion = parametros.contains("ALINEACION");
        boolean requerido = parametros.contains("REQUERIDO");
        boolean opciones = parametros.contains("OPCIONES");
        boolean filas = parametros.contains("FILAS");
        boolean columnas = parametros.contains("COLUMNAS");
        boolean url = parametros.contains("URL");
        String aux1;
        if (texto_visible) {
            aux1 = datos.get(parametros.indexOf("TEXTO_VISIBLE"));
            entrada.getComponente(posModificar).setTextoVisible(aux1);
            infoEntrada.append("TEXTO_VISIBLE\n");
        }
        if (alineacion) {
            aux1 = datos.get(parametros.indexOf("ALINEACION"));
            entrada.getComponente(posModificar).setAlineacion(aux1);
            infoEntrada.append("ALINEACION\n");
        }
        if (requerido) {
            aux1 = datos.get(parametros.indexOf("REQUERIDO"));
            entrada.getComponente(posModificar).setRequerido(aux1);
            infoEntrada.append("REQUERIDO\n");
        }
        if (opciones) {
            aux1 = datos.get(parametros.indexOf("OPCIONES"));
            entrada.getComponente(posModificar).setOpciones(aux1);
            infoEntrada.append("OPCIONES\n");
        }
        if (filas) {
            aux1 = datos.get(parametros.indexOf("FILAS"));
            entrada.getComponente(posModificar).setFila(aux1);
            infoEntrada.append("FILAS\n");
        }
        if (columnas) {
            aux1 = datos.get(parametros.indexOf("COLUMNAS"));
            entrada.getComponente(posModificar).setColumna(aux1);
            infoEntrada.append("COLUMNAS\n");
        }
        if (url) {
            aux1 = datos.get(parametros.indexOf("URL"));
            entrada.getComponente(posModificar).setUrl(aux1);
            infoEntrada.append("URL\n");
        }
    }

    private static Formulario exitenciaFormulario(String ruta, String idFormulario) {
        if (ManejadorArchivos.existeArchivo(ruta)) {
            System.out.println(ruta);
            return ParseadorFormulario.entrada(idFormulario);
        } else {
            System.out.println("Error no existe formulario ");
        }
        return null;
    }

    private static void reescribir(String ruta, Formulario f, boolean recuperador) {
        ManejadorArchivos.borrarObjeto(ruta);
        ManejadorArchivos.escribirArchivo(ParseadorFormulario.Salida(f, recuperador), ruta);
    }

    public static Componente parserCompoente(String id, List<String> datos, List<String> parametros) {
        Componente retorno = new Componente(id, datos.get(parametros.indexOf("FORMULARIO")), datos.get(parametros.indexOf("CLASE")));
        if (parametros.contains("INDICE")) {
            retorno.setINDICE(datos.get(parametros.indexOf("INDICE")));
        }
        if (parametros.contains("TEXTO_VISIBLE")) {
            retorno.setTextoVisible(datos.get(parametros.indexOf("TEXTO_VISIBLE")));
        }
        switch (retorno.getClase()) {
            case "CAMPO_TEXTO":
                retorno.setNombreCampo(datos.get(parametros.indexOf("NOMBRE_CAMPO")));
                break;
            case "FICHERO":
                retorno.setNombreCampo(datos.get(parametros.indexOf("NOMBRE_CAMPO")));
                break;
            case "AREA_TEXTO":
                retorno.setFila(datos.get(parametros.indexOf("FILAS")));
                retorno.setColumna(datos.get(parametros.indexOf("COLUMNAS")));
                retorno.setNombreCampo(datos.get(parametros.indexOf("NOMBRE_CAMPO")));
                break;
            case "CHECKBOX":
                retorno.setOpciones(datos.get(parametros.indexOf("OPCIONES")));
                retorno.setNombreCampo(datos.get(parametros.indexOf("NOMBRE_CAMPO")));
                break;
            case "RADIO":
                retorno.setOpciones(datos.get(parametros.indexOf("OPCIONES")));
                retorno.setNombreCampo(datos.get(parametros.indexOf("NOMBRE_CAMPO")));
                break;
            case "IMAGEN":
                retorno.setUrl(datos.get(parametros.indexOf("URL")));
                break;
            case "COMBO":
                retorno.setOpciones(datos.get(parametros.indexOf("OPCIONES")));
                retorno.setNombreCampo(datos.get(parametros.indexOf("NOMBRE_CAMPO")));
                break;
            case "BOTON":

                break;
        }
        if (parametros.contains("ALINEACION")) {
            retorno.setAlineacion(datos.get(parametros.indexOf("ALINEACION")));
        }
        if (parametros.contains("REQUERIDO")) {
            retorno.setRequerido(datos.get(parametros.indexOf("REQUERIDO")));
        }
        System.out.println("Agrego componente " + retorno.getId() + " de la clase " + retorno.getClase());
        return retorno;
    }

    
}
