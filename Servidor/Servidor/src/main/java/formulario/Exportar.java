package formulario;

import archivos.ManejadorArchivos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/exportar")
public class Exportar extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        String idFormulario = request.getParameter("id");
        String textoFormulario = ManejadorArchivos.leerArchivo(ManejadorFormulario.rutaFormulario+idFormulario);
        //System.out.println("En exportar esta esto\n"+textoFormulario);
        String ruta = ManejadorFormulario.rutaFormulario+idFormulario;
        
        ManejadorArchivos.borrarObjeto(ruta);        
        ManejadorArchivos.escribirArchivo(textoFormulario, ruta);
        request.setAttribute("idExportado", idFormulario);
        request.getRequestDispatcher("inicioUsuario.jsp").forward(request, response);
    }
}
