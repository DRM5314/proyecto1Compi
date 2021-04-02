package usuario;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import archivos.ManejadorArchivos;
import formulario.ManejadorFormulario;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import rutas.RutaHome;
import solicitudes.Reportes;

public class ManejadorUsuario {

    public static final String rutaUsuario= RutaHome.obtenerRutaHome()+"USUARIOS/";    

    public static void consultaUsuario(String tipo, List<String> datos, List<String> parametros, Reportes reporteEntrada, int[] posXY, String usuarioEntrada, StringBuilder usuarioSalida) {
        String usuario;
        switch (tipo) {
            case "CREAR_USUARIO":
                usuario = datos.get(parametros.indexOf("USUARIO"));
                if (!ManejadorArchivos.existeArchivo(rutaUsuario + usuario)) {
                    String fechaCreacion;
                    Usuario u = new Usuario(usuario, datos.get(parametros.indexOf("PASSWORD")));
                    if (parametros.contains("FECHA_CREACION")) {
                        fechaCreacion = datos.get(parametros.indexOf("FECHA_CREACION"));
                    } else {
                        fechaCreacion = getFechaActual();
                    }
                    u.setFechaCreacion(fechaCreacion);
                    ParseadorUsuario.salida(u);
                    //System.out.println("Usuario " + u.getUsuario() + " Creado ");
                    reporteEntrada.agregarSolicitudProcesada(tipo, "Usuario " + u.getUsuario() + " Creado ");
                } else {
                    String info = "Usuario " + usuario + " no creado ya existe\n";
                    info += "Linea: " + posXY[0] + "\n";
                    info += "Columna: " + posXY[1] + "\n";
                    reporteEntrada.agregarSolicitudProcesada(tipo, info);
                    //System.out.println("Error ya existe usuario ");
                }
                break;
            case "MODIFICAR_USUARIO":
                usuario = datos.get(parametros.indexOf("USUARIO_ANTIGUO"));
                if (ManejadorArchivos.existeArchivo(rutaUsuario + usuario)) {
                    Usuario u = ParseadorUsuario.entrada(rutaUsuario + usuario, usuario);
                    String usuairoNuevo = "";
                    boolean existeUsuario = false;
                    if (parametros.contains("USUARIO_NUEVO")) {
                        usuairoNuevo = datos.get(parametros.indexOf("USUARIO_NUEVO"));
                        if (ManejadorArchivos.existeArchivo(rutaUsuario + usuairoNuevo)) {
                            existeUsuario = true;
                        }
                    }
                    if (u != null) {
                        if (!existeUsuario) {
                            ManejadorArchivos.borrarObjeto(rutaUsuario + usuario);
                            StringBuilder info = new StringBuilder("Usuario " + usuario + "\n");
                            editarUsuario(parametros, datos, u, info);
                            ParseadorUsuario.salida(u);
                            //System.out.println("Usuario " + u.getUsuario() + " Modificado");
                            reporteEntrada.agregarSolicitudProcesada(tipo, info.toString());
                            ManejadorArchivos.borrarObjeto(rutaUsuario + usuario);
                            if (usuarioEntrada.equals(usuario)) {
                                usuarioSalida.append(u.getUsuario());
                                usuarioSalida.append("\n" + u.getFechaCreacion());
                                usuarioSalida.append("\n" + u.getFechaModificacion());
                            }
                        } else {
                            String info = "Usuario " + usuairoNuevo + " ya existe\n";
                            info += "Linea: " + posXY[0] + "\n";
                            info += "Columna: " + posXY[1] + "\n";
                            reporteEntrada.agregarSolicitudProcesada(tipo, info);
                        }
                    }
                } else {
                    String info = "Usuario " + usuario + " No existe\n";
                    info += "Linea: " + posXY[0] + "\n";
                    info += "Columna: " + posXY[1] + "\n";
                    reporteEntrada.agregarSolicitudProcesada(tipo, info);
                    //System.out.println("Error no existe usuario ");
                }
                break;
            case "ELIMINAR_USUARIO":
                usuario = datos.get(parametros.indexOf("USUARIO"));
                if (ManejadorArchivos.existeArchivo(rutaUsuario + usuario)) {
                    ManejadorArchivos.borrarObjeto(rutaUsuario + usuario);
                    StringBuilder infoEliminacion = new StringBuilder();
                    ManejadorFormulario.elimiarFormulariosUsuarioEleminacion(usuario, infoEliminacion);
                    infoEliminacion.append("\nUsuario " + usuario + " Eliminado");
                    reporteEntrada.agregarSolicitudProcesada(tipo, infoEliminacion.toString());
                    if (usuarioEntrada.equals(usuario)) {
                        usuarioSalida.append("eliminado");
                    }
                    //System.out.println("Usuario " + usuario + " Eliminado");
                } else {
                    String info = "Usuario " + usuario + " No existe\n";
                    info += "Linea: " + posXY[0] + "\n";
                    info += "Columna: " + posXY[1] + "\n";
                    reporteEntrada.agregarSolicitudProcesada(tipo, info);
                    //System.out.println("Error no existe usuario ");
                }
                break;
            case "RECUPERACION_USUARIO":
                if (ManejadorArchivos.existeArchivo(rutaUsuario + datos.get(parametros.indexOf("USUARIO")))) {
                    ManejadorArchivos.borrarObjeto(rutaUsuario + datos.get(parametros.indexOf("USUARIO")));
                    System.out.println("Usuario " + datos.get(parametros.indexOf("USUARIO")) + " Eliminado");
                } else {
                    System.out.println("Error no existe usuario ");
                }

                break;
        }
    }

    private static String getFechaActual() {
        String retorno = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        return retorno;
    }

    private static void editarUsuario(List<String> parametros, List<String> datos, Usuario entrada, StringBuilder infoEntrada) {
        boolean setPassword = parametros.contains("NUEVO_PASSWORD");
        boolean setUsuario = parametros.contains("USUARIO_NUEVO");
        boolean setFechaModificacion = parametros.contains("FECHA_MODIFICACION");
        if (setPassword) {
            entrada.setPassword(datos.get(parametros.indexOf("NUEVO_PASSWORD")));
            infoEntrada.append("Edito password\n");
        }
        if (setUsuario) {
            String usuarioNuevo = datos.get(parametros.indexOf("USUARIO_NUEVO"));
            StringBuilder infoModificacionFormularioUsuaro = new StringBuilder();
            ManejadorFormulario.modificarUsuario(entrada.getUsuario(), usuarioNuevo, infoModificacionFormularioUsuaro);
            entrada.setUsuario(usuarioNuevo);
            infoEntrada.append("Edito usuario a " + entrada.getUsuario());
            infoEntrada.append(infoModificacionFormularioUsuaro.toString());
        }
        if (setFechaModificacion) {
            entrada.setFechaModificacion(datos.get(parametros.indexOf("FECHA_MODIFICACION")));
        } else {
            entrada.setFechaModificacion(getFechaActual());
        }
    }

    public static boolean parametrosVerificador(String tipoUsuario, List<String> parametrosEntrada) {
        List<String> p = parametrosEntrada;
        boolean obligatorio;
        boolean opcion1;
        boolean opcion2;
        switch (tipoUsuario) {
            case "CREAR_USUARIO":
                if ((p.contains("USUARIO") && p.contains("PASSWORD") && parametrosEntrada.size() <= 2)
                        || ((p.contains("USUARIO") && p.contains("PASSWORD") && p.contains("FECHA_CREACION") && parametrosEntrada.size() <= 3))) {
                    return true;
                }
                break;
            case "MODIFICAR_USUARIO":
                obligatorio = p.contains("USUARIO_ANTIGUO");
                opcion1 = p.contains("USUARIO_NUEVO") || p.contains("NUEVO_PASSWORD") || p.contains("FECHA_MODIFICACION");

                boolean opcionUsuarioNuevo2 = (p.contains("USUARIO_NUEVO") && p.contains("NUEVO_PASSWORD"))
                        || (p.contains("USUARIO_NUEVO") && p.contains("FECHA_MODIFICACION"));

                boolean opcionPassword2 = p.contains("NUEVO_PASSWORD") && p.contains("FECHA_MODIFICACION");

                boolean opcion3 = p.contains("USUARIO_NUEVO") && p.contains("NUEVO_PASSWORD") && p.contains("FECHA_MODIFICACION");
                if ((obligatorio && opcion1 && parametrosEntrada.size() <= 2)
                        || (obligatorio && opcionUsuarioNuevo2 && parametrosEntrada.size() <= 3)
                        || (obligatorio && opcionPassword2 && parametrosEntrada.size() <= 3)
                        || (obligatorio && opcion3 && parametrosEntrada.size() <= 4)) {
                    return true;
                }
                break;
            case "ELIMINAR_USUARIO":
                if (p.contains("USUARIO") && parametrosEntrada.size() <= 1) {
                    return true;
                }
                break;
            case "LOGIN_USUARIO":
                if (p.contains("USUARIO") && p.contains("PASSWORD") && parametrosEntrada.size() <= 2) {
                    return true;
                }
                break;
            case "RECUPERACION_USUARIO":
                obligatorio = p.contains("PASSWORD");
                opcion1 = p.contains("FECHA_CREACION") || p.contains("FECHA_MODIFICACION");
                opcion2 = p.contains("FECHA_CREACION") && p.contains("FECHA_MODIFICACION");
                if (obligatorio
                        || (obligatorio && opcion1 && parametrosEntrada.size() <= 2)
                        || (obligatorio && opcion2 && parametrosEntrada.size() <= 3)) {
                    return true;
                }
                break;
        }
        return false;
    }

    public static boolean login(String usuario, String password) {
        Usuario user;
        if (ManejadorArchivos.existeArchivo(rutaUsuario + usuario)) {
            user = ParseadorUsuario.entrada(rutaUsuario + usuario, usuario);
            if (user.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean login(List<String> datos, List<String> parametros, StringBuilder usuarioSalida, Reportes reporteEntrada, int[] posXY) {
        Usuario retorno = null;
        if (parametrosVerificador("LOGIN_USUARIO", parametros)) {
            String usuario = datos.get(parametros.indexOf("USUARIO"));
            String password = datos.get(parametros.indexOf("PASSWORD"));
            if (ManejadorArchivos.existeArchivo(rutaUsuario + usuario)) {
                Usuario u = ParseadorUsuario.entrada(rutaUsuario + usuario, usuario);
                if (u != null) {
                    if (password.equals(u.getPassword())) {
                        reporteEntrada.agregarSolicitudProcesada("LOGIN_USUARIO", "Usuario " + u.getUsuario() + " Logueado");
                        //System.out.println("Usuario " + u.getUsuario() + " Logueado");
                        usuarioSalida.append(u.getUsuario());
                        usuarioSalida.append("\n" + u.getFechaCreacion());
                        if (u.getFechaModificacion() != null) {
                            usuarioSalida.append("\n" + u.getFechaModificacion());
                        } else {
                            usuarioSalida.append("\nNo se ha modificado");
                        }
                        return true;
                    } else {
                        String info = "Password " + usuario + " incorrecta\n";
                        info += "Linea: " + posXY[0] + "\n";
                        info += "Columna: " + posXY[1] + "\n";
                        reporteEntrada.agregarSolicitudProcesada("LOGIN_USUARIO", info);
                    }
                } else {
                    String info = "Usuario " + usuario + " no existe\n";
                    info += "Linea: " + posXY[0] + "\n";
                    info += "Columna: " + posXY[1] + "\n";
                    reporteEntrada.agregarSolicitudProcesada("LOGIN_USUARIO", info);
                    //System.out.println("Error no existe usuario ");
                }

            } else {
                String info = "Usuario " + usuario + " no existe\n";
                info += "Linea: " + posXY[0] + "\n";
                info += "Columna: " + posXY[1] + "\n";
                reporteEntrada.agregarSolicitudProcesada("LOGIN_USUARIO", info);
                return false;
            }
        } else {
            String parametrosObligatorios = parametrosObligatorios("LOGIN_USUARIO");
            reporteEntrada.agregarError("Semantico", parametrosObligatorios, 0, 0);
        }
        return false;
    }

    public static Usuario recuperacionUsuario(List<String> parametros, List<String> datos, String id) {
        if (parametrosVerificador("RECUPERACION_USUARIO", parametros)) {
            if (ManejadorArchivos.existeArchivo(rutaUsuario + id)) {
                Usuario f = new Usuario(id, datos.get(parametros.indexOf("PASSWORD")));
                if (parametros.contains("FECHA_CREACION")) {
                    f.setFechaCreacion(datos.get(parametros.indexOf("FECHA_CREACION")));
                }
                if (parametros.contains("FECHA_MODIFICACION")) {
                    f.setFechaModificacion(datos.get(parametros.indexOf("FECHA_MODIFICACION")));
                }
                return f;
            } else {
                System.out.println("Error no existe usuario ");
            }
        }
        return null;
    }

    public static String parametrosObligatorios(String tipoCrud) {
        String aux = "";
        switch (tipoCrud) {
            case "CREAR_USUARIO":
                aux += " Parametros obligatorios:\nUsuario y Password";
                return aux;
            case "MODIFICAR_USUARIO":
                aux += " Parametros obligatorios:\nUSUARIO_ANTIGUO con ya sea (USUARIO_NUEVO o NUEVO_PASSWORD)\nO todos juntos";
                return aux;
            case "ELIMINAR_USUARIO":
                aux += " Parametros obligatorios:\nUSUARIO";
                return aux;
            case "LOGIN_USUARIO":
                aux += " Parametros obligatorios:\nUSUARIO Y PASSWORD";
                return aux;
        }
        return null;
    }
    public static void iniciarUsuario(){
        Usuario nuevoUsuario = new Usuario("user", "user");
        nuevoUsuario.setFechaModificacion(getFechaActual());
        ParseadorUsuario.salida(nuevoUsuario);
    }
}
