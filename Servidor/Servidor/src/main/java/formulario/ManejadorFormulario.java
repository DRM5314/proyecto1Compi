package formulario;

import archivos.ManejadorArchivos;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import rutas.RutaHome;
import solicitudes.Reportes;
import usuario.ManejadorUsuario;

public class ManejadorFormulario {

    public static String rutaFormulario = RutaHome.obtenerRutaHome()+"FORMULARIOS/";
    //"http://localhost:8080/Servidor/FORMULARIOSWEB/"
    //RutaHome.obtenerRutaHome()+"FORMULARIOSWEB/";
    public static String rutaFormularioWeb =  "http://localhost:8080/Servidor/FORMULARIOSWEB/";

    public static boolean parametrosVerificador(String tipoUsuario, List<String> parametrosEntrada) {
        List<String> p = parametrosEntrada;
        switch (tipoUsuario) {
            case "NUEVO_FORMULARIO":
                if ((p.contains("ID") && p.contains("TITULO") && p.contains("NOMBRE") && p.contains("TEMA") && parametrosEntrada.size() <= 4)
                        || (p.contains("ID") && p.contains("TITULO") && p.contains("NOMBRE") && p.contains("TEMA") && (p.contains("USUARIO_CREACION") || p.contains("FECHA_CREACION")) && parametrosEntrada.size() <= 5)
                        || (p.contains("ID") && p.contains("TITULO") && p.contains("NOMBRE") && p.contains("TEMA") && p.contains("USUARIO_CREACION") && p.contains("FECHA_CREACION") && parametrosEntrada.size() <= 6)) {
                    return true;
                }
                break;
            case "ELIMINAR_FORMULARIO":
                if (p.contains("ID") && parametrosEntrada.size() <= 1) {
                    return true;
                }
            case "MODIFICAR_FORMULARIO":
                if ((p.contains("ID") && p.contains("TITULO") && p.contains("NOMBRE") && p.contains("TEMA") && parametrosEntrada.size() <= 4)
                        || (p.contains("ID") && p.contains("TITULO") && (p.contains("NOMBRE") || p.contains("TEMA")) && parametrosEntrada.size() <= 3)
                        || (p.contains("ID") && p.contains("NOMBRE") && (p.contains("TITULO") || p.contains("TEMA")) && parametrosEntrada.size() <= 3)
                        || (p.contains("ID") && p.contains("TEMA") && (p.contains("NOMBRE") || p.contains("TITULO")) && parametrosEntrada.size() <= 3)
                        || (p.contains("ID") && (p.contains("TEMA") || p.contains("NOMBRE") || p.contains("TITULO")) && parametrosEntrada.size() <= 2)) {
                    return true;
                }
                break;
            case "RECUPERACION_FORMULARIO":
                if (p.contains("TITULO") && p.contains("NOMBRE") && p.contains("TEMA") && p.contains("USUARIO_CREACION") && p.contains("FECHA_CREACION") && parametrosEntrada.size() <= 5) {
                    return true;
                }
                break;
        }
        return false;
    }

    public static String parametrosObligatorios(String tipoCrud) {
        String aux = "";
        switch (tipoCrud) {
            case "NUEVO_FORMULARIO":
                aux = "Parametros obligatorios:\nId titulo  nombre  tema";
                return aux;
            case "ELIMINAR_FORMULARIO":
                aux = "Parametros obligatorios:\nId";
                return aux;
            case "MODIFICAR_FORMULARIO":
                aux = "Parametros obligatorios:\nId con ya sea (Titulo  Nombre  Tema)\nO todos juntos";
                return aux;
        }
        return null;
    }

    public static void consultaFormulario(String tipo, List<String> datos, List<String> parametros, String idUsuario, Reportes reporteEntrada, int[] posXY) {
        String id = datos.get(parametros.indexOf("ID"));
        switch (tipo) {
            case "NUEVO_FORMULARIO":
                boolean errorUsuario = false;
                String aux;
                if (!ManejadorArchivos.existeArchivo(rutaFormulario + id)) {
                    Formulario f = new Formulario(id, datos.get(parametros.indexOf("TITULO")),
                            datos.get(parametros.indexOf("NOMBRE")), datos.get(parametros.indexOf("TEMA"))
                    );
                    if (parametros.contains("FECHA_CREACION")) {
                        aux = datos.get(parametros.indexOf("FECHA_CREACION"));
                        f.setFechaCreacion(aux);
                    } else {
                        aux = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                        f.setFechaCreacion(aux);
                    }
                    if (!parametros.contains("USUARIO_CREACION")) {
                        f.setUsuario_creacion(idUsuario);
                    } else {
                        aux = datos.get(parametros.indexOf("USUARIO_CREACION"));
                        if (aux.equals(idUsuario) && ManejadorArchivos.existeArchivo(ManejadorUsuario.rutaUsuario + aux)) {
                            f.setUsuario_creacion(aux);
                        } else {
                            errorUsuario = true;
                        }
                    }
                    if (!errorUsuario) {
                        ManejadorArchivos.escribirArchivo(ParseadorFormulario.Salida(f, false), rutaFormulario + f.getId());
                        reporteEntrada.agregarSolicitudProcesada(tipo, "Formulario " + f.getId() + " Creado ");
                    } else {
                        String info = "Formulario " + f.getId() + " No creado\n ";
                        info += "Usuario no existe o no es el que esta logueado";
                        info += "Linea: " + posXY[0] + "\n";
                        info += "Columna: " + posXY[1] + "\n";
                        reporteEntrada.agregarSolicitudProcesada(tipo, info);
                    }
                } else {
                    String info = id + " No creado ya existe\n";
                    info += "Linea: " + posXY[0] + "\n";
                    info += "Columna: " + posXY[1] + "\n";
                    reporteEntrada.agregarSolicitudProcesada(tipo, info);
                    //System.out.println("Error ya existe formulario ");
                }
                break;
            case "ELIMINAR_FORMULARIO":
                if (ManejadorArchivos.existeArchivo(rutaFormulario + datos.get(parametros.indexOf("ID")))) {
                    ManejadorArchivos.borrarObjeto(rutaFormulario + datos.get(parametros.indexOf("ID")));
                    reporteEntrada.agregarSolicitudProcesada(tipo, id + " eliminado");
                    //System.out.println("Formulario " + id + " Eliminado");
                } else {
                    String info = id + " No existe\n";
                    info += "Linea: " + posXY[0] + "\n";
                    info += "Columna: " + posXY[1] + "\n";
                    reporteEntrada.agregarSolicitudProcesada(tipo, info);
                    //System.out.println("Error no existe formulario ");
                }
                break;
            case "MODIFICAR_FORMULARIO":
                if (ManejadorArchivos.existeArchivo(rutaFormulario + datos.get(parametros.indexOf("ID")))) {
                    System.out.println(rutaFormulario + datos.get(parametros.indexOf("ID")));
                    Formulario f = ParseadorFormulario.entrada(datos.get(parametros.indexOf("ID")));
                    String info = id + " Modifico: \n";
                    if (f != null) {
                        if (parametros.contains("ID")) {
                            aux = datos.get(parametros.indexOf("ID"));
                            f.setId(aux);
                        }
                        if (parametros.contains("TITULO")) {
                            aux = datos.get(parametros.indexOf("TITULO"));
                            f.setTitulo(datos.get(parametros.indexOf("TITULO")));
                            info += "TITULO a " + aux + "\n";
                        }
                        if (parametros.contains("NOMBRE")) {
                            aux = datos.get(parametros.indexOf("NOMBRE"));
                            f.setNombre(datos.get(parametros.indexOf("NOMBRE")));
                            info += "NOMBRE a " + aux + "\n";
                        }
                        if (parametros.contains("TEMA")) {
                            aux = datos.get(parametros.indexOf("TEMA"));
                            f.setTema(datos.get(parametros.indexOf("TEMA")));
                            info += "TEMA a " + aux + "\n";
                        }
                        //System.out.println("Recupero " + f.getComponentes().size() + " Componentes");
                        ManejadorArchivos.borrarObjeto(rutaFormulario + f.getId());
                        ManejadorArchivos.escribirArchivo(ParseadorFormulario.Salida(f, false), rutaFormulario + f.getId());
                        reporteEntrada.agregarSolicitudProcesada(tipo, info);
                        System.out.println("Modifico formulario " + f.getId());
                    }

                } else {
                    String info = id + " No existe\n";
                    info += "Linea: " + posXY[0] + "\n";
                    info += "Columna: " + posXY[1] + "\n";
                    reporteEntrada.agregarSolicitudProcesada(tipo, info);
                    //System.out.println("Error no existe formulario ");
                }
                break;
        }
    }

    public static Formulario recuperacionFormulario(List<String> parametros, List<String> datos, String id, boolean importacion) {
        if (parametrosVerificador("RECUPERACION_FORMULARIO", parametros)) {
            if (importacion) {
                Formulario f = new Formulario(id, datos.get(parametros.indexOf("TITULO")),
                        datos.get(parametros.indexOf("NOMBRE")), datos.get(parametros.indexOf("TEMA"))
                );
                f.setUsuario_creacion(datos.get(parametros.indexOf("USUARIO_CREACION")));
                f.setFechaCreacion(datos.get(parametros.indexOf("FECHA_CREACION")));
                return f;
            } else {
                if (ManejadorArchivos.existeArchivo(rutaFormulario + id)) {
                    Formulario f = new Formulario(id, datos.get(parametros.indexOf("TITULO")),
                            datos.get(parametros.indexOf("NOMBRE")), datos.get(parametros.indexOf("TEMA"))
                    );
                    f.setUsuario_creacion(datos.get(parametros.indexOf("USUARIO_CREACION")));
                    f.setFechaCreacion(datos.get(parametros.indexOf("FECHA_CREACION")));
                    return f;
                } else {
                    System.out.println("Error no existe formulario ");
                }
            }
        }
        return null;
    }

    public static void modificarUsuario(String usuarioAntiguo, String usuarioNuevo, StringBuilder infoEntrada) {
        ManejadorFormulariosAppWeb recuperadorFormulariosUsuario = new ManejadorFormulariosAppWeb(usuarioAntiguo);
        List<Formulario> formulariosEntrada = recuperadorFormulariosUsuario.getFormularios();
        for (Formulario f : formulariosEntrada) {
            ManejadorArchivos.borrarObjeto(rutaFormulario + f.getId());
            f.setUsuario_creacion(usuarioNuevo);
            ManejadorArchivos.escribirArchivo(ParseadorFormulario.Salida(f, false), rutaFormulario + f.getId());
            infoEntrada.append("\nModifico usuario " + usuarioNuevo + " a formulario " + f.getId() + "\n");
        }
    }

    public static void elimiarFormulariosUsuarioEleminacion(String usuario, StringBuilder infoEntrada) {
        ManejadorFormulariosAppWeb recuperadorFormulariosUsuario = new ManejadorFormulariosAppWeb(usuario);
        List<Formulario> formulariosEntrada = recuperadorFormulariosUsuario.getFormularios();
        for (Formulario f : formulariosEntrada) {
            ManejadorArchivos.borrarObjeto(rutaFormulario + f.getId());
            infoEntrada.append("\nElimino formulario " + f.getId() + " que creo usuario " + usuario + "\n");
        }
    }
}
