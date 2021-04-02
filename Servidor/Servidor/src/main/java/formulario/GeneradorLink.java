package formulario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Request;

@WebServlet("/generadorLink")
public class GeneradorLink extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        String idFormulario = request.getParameter("id");
        Formulario formularioSalidaHtml = ParseadorFormulario.entrada(idFormulario);
        ParserHtml parserHtml = new ParserHtml(formularioSalidaHtml);
        parserHtml.parser();
        //http://localhost:8080/Servidor/inicioUsuario.jsp#
        String link = ManejadorFormulario.rutaFormularioWeb+idFormulario+".jsp";
        System.out.println("el link esta en "+link);                
        request.setAttribute("link", link);        
        request.getRequestDispatcher("inicioUsuario.jsp").forward(request, response);
    }
}
