package pl.coderslab.Controller.Client;

import pl.coderslab.Dao.ClientDao;
import pl.coderslab.Entity.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ClientEdit", urlPatterns = {"/customer/edit", "/customer/edit/"})
public class ClientEdit extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        Client client = (Client) session.getAttribute("client");

        client.setFirstName(request.getParameter("first_name"));
        client.setSurname(request.getParameter("surname"));
        client.setBirthdate(request.getParameter("birthdate"));

        ClientDao clientDao = new ClientDao();
        clientDao.save(client);

        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/customer?id=" + client.getId()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");

        try
        {
            Integer intId = Integer.parseInt(id);

            ClientDao clientDao = new ClientDao();
            Client client = clientDao.getById(intId);

            request.setAttribute("client", client);

            HttpSession session = request.getSession(true);
            session.setAttribute("client", client); // do doPost();
            getServletContext().getRequestDispatcher("/WEB-INF/views/client/edit.jsp").forward(request, response);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("To id nie jest liczbą");
        }
    }
}
