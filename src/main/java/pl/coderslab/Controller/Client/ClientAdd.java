package pl.coderslab.Controller.Client;

import pl.coderslab.Dao.ClientDao;
import pl.coderslab.Entity.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientAdd", urlPatterns = {"/customer/add", "/customer/add/"})
public class ClientAdd extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Client client = new Client();

        client.setFirstName(request.getParameter("first_name"));
        client.setSurname(request.getParameter("surname"));
        client.setBirthdate(request.getParameter("birthdate"));

        ClientDao clientDao = new ClientDao();
        clientDao.save(client);

        int generatedId = client.getId();

        response.sendRedirect(response.encodeRedirectURL("/customer?id=" + generatedId));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/WEB-INF/views/client/add.jsp").forward(request, response);
    }
}
