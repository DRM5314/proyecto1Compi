package solicitudes;

import componente.ManejadorComponente;
import formulario.ManejadorFormulario;
import java.util.Collections;
import java.util.List;
import usuario.ManejadorUsuario;

public class procesadorSolicitudes {

    private List<Solicitudes> solicitudes;
    private boolean logeado = false;
    private String usuarioSalida = null;
    private String usuarioId;
    private boolean eliminado = false;
    
    public procesadorSolicitudes(List<Solicitudes> solicitudes,String usuarioEntrada) {
        this.solicitudes = solicitudes;
        if(usuarioEntrada!=null && !usuarioEntrada.isEmpty()){
            this.usuarioId = usuarioEntrada;
            this.logeado = true;
        }
    }
    

    public void procesarSolicitudes(Reportes reporteEntrada) {        
        StringBuilder user;
        for (Solicitudes sol : solicitudes) {
            String tipoPadre =sol.getTipoInstruccionPadre();
            switch (tipoPadre) {
                case "CRUD_USUARIO":
                    if (logeado && !sol.getTipoInstruccion().equals("LOGIN_USUARIO")) {
                        StringBuilder usuarioAux = new StringBuilder();
                        ManejadorUsuario.consultaUsuario(sol.getTipoInstruccion(), sol.getDatosInstruccion(), sol.getParametrosInstruccion(),reporteEntrada,sol.getPosXY(),usuarioId,usuarioAux);
                        if(!usuarioAux.toString().isEmpty()){
                            if(usuarioAux.toString().equals("eliminado")){
                                eliminado = true;
                                logeado = false;                                
                            }else{
                                usuarioSalida = usuarioAux.toString();
                                usuarioId = usuarioSalida.split("\n")[0];                                
                            }
                        }
                    } else if (sol.getTipoInstruccion().equals("LOGIN_USUARIO")) {
                         user = new StringBuilder();
                        logeado = ManejadorUsuario.login(sol.getDatosInstruccion(), sol.getParametrosInstruccion(), user,reporteEntrada,sol.getPosXY());
                        if(logeado){
                            usuarioSalida = user.toString();
                            usuarioId = usuarioSalida.split("\n")[0];
                        }
                    }else{
                        reporteEntrada.agregarSolicitudProcesada(sol.getTipoInstruccion(), "No procesada falta login");
                    }
                    break;
                case "CRUD_FORMULARIO":
                    if (logeado) {
                        ManejadorFormulario.consultaFormulario(sol.getTipoInstruccion(), sol.getDatosInstruccion(), sol.getParametrosInstruccion(), this.usuarioId,reporteEntrada,sol.getPosXY());
                    } else {
                        reporteEntrada.agregarSolicitudProcesada(sol.getTipoInstruccion(), "No procesada falta login");
                    }
                    break;
                case "CRUD_COMPONENTE":
                    if (logeado) {
                        ManejadorComponente.consultaComponente(sol.getTipoInstruccion(), sol.getDatosInstruccion(), sol.getParametrosInstruccion(),reporteEntrada,sol.getPosXY());
                    }else{
                        reporteEntrada.agregarSolicitudProcesada(sol.getTipoInstruccion(), "No procesada falta login");
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public String getUsuarioSalida() {
        return usuarioSalida;
    }

}
