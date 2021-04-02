/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutas;

/**
 *
 * @author david
 */
public class RutaHome {
    public static String obtenerRutaHome(){
        String rutaUsuario = System.getProperty("user.home");
        switch(System.getProperty("os.name").toLowerCase()){
            case "linux":
                return rutaUsuario+"/baseDatos/";
        }
        System.out.println("Va a retornar null");
        return null;
    }
}
