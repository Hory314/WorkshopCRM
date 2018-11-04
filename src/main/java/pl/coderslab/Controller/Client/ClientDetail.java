package pl.coderslab.Controller.Client;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Dao.ClientDao;
import pl.coderslab.Dao.EmployeeDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Car;
import pl.coderslab.Entity.Client;
import pl.coderslab.Entity.Employee;
import pl.coderslab.Entity.Repair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClientDetail", urlPatterns = {"/customer", "/customer/"})
public class ClientDetail extends HttpServlet
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
            Client client = clientDao.getById(intId);

            CarDao carDao = new CarDao();
            List<Car> cars = carDao.findAll("WHERE `client_id` = " + intId);

            request.setAttribute("client", client);
            request.setAttribute("cars", cars);
            getServletContext().getRequestDispatcher("/WEB-INF/views/client/detail.jsp").forward(request, response);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("to nie jest id");
        }
    }
}
