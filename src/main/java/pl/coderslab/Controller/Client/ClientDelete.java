package pl.coderslab.Controller.Client;

import pl.coderslab.Dao.ClientDao;
import pl.coderslab.Dao.RepairDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientDelete", urlPatterns = {"/customer/delete", "/customer/delete/"})
public class ClientDelete extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");

        try
        {
            Integer intId = Integer.parseInt(id);

            ClientDao clientDao = new ClientDao();
            clientDao.delete(intId);

            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/customers"));
        }
        catch (NumberFormatException e)
        {
            System.out.println("To nie jest ID.");
        }
    }
}
