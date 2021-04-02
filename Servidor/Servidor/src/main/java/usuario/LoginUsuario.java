package usuario;


import archivos.ManejadorArchivos;
import formulario.ManejadorFormulariosAppWeb;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rutas.ManejadorRutas;
import usuario.ManejadorUsuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author david
 */
@WebServlet("/login")
public class LoginUsuario extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        procesar(request, response);
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        procesar(request, response);
    }
    private void procesar(HttpServletRequest resquest,HttpServletResponse response) throws IOException, ServletException{
        ManejadorRutas.inicarRutas();
        String usuario = resquest.getParameter("user");
        String password = resquest.getParameter("pass");        
        System.out.println("Usuario: "+usuario+"\nContraseña: "+password);
        boolean existeUsuario = ManejadorUsuario.login(usuario, password);
        if(existeUsuario){
            HttpSession s = resquest.getSession();            
            s.setAttribute("usuario", usuario);
            formulario.ManejadorFormulariosAppWeb fEntrada = new ManejadorFormulariosAppWeb(usuario);
            List<formulario.Formulario> listadoFormularios = fEntrada.getFormularios();
            if(listadoFormularios.size()>0){
                s.setAttribute("formularios", listadoFormularios);
            }else {
                s.setAttribute("sinFormularios", usuario+" no tiene formularios a su nombre aun");
            }
            response.sendRedirect("inicioUsuario.jsp");
        }else{
            resquest.setAttribute("error", "Usuario o constraseña invalidos");
            resquest.getRequestDispatcher("index.jsp").forward(resquest, response);
        }
    }
}
