package pl.coderslab.Controller.Client;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Dao.ClientDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Car;
import pl.coderslab.Entity.Client;
import pl.coderslab.Entity.Repair;
import pl.coderslab.Entity.Status;
import pl.coderslab.Service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "ClientList", urlPatterns = {"/customers/", "/customers"})
public class ClientList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        ClientDao clientDao = new ClientDao();
        List<Client> clients;

        String searchedSurname = request.getParameter("search");

        if (searchedSurname != null)
        {
            if (!searchedSurname.equals(""))
            {
                searchedSurname = searchedSurname.replaceAll("\\*", "%");
                clients = clientDao.findAll("WHERE `surname` LIKE '" + searchedSurname + "'");
            }
            else
            {
                clients = clientDao.findAll();
            }
        }
        else
        {
            clients = clientDao.findAll();
        }


        Map<Client, List<Car>> clientsCarsMap = new LinkedHashMap<>(); // LinkedHashMap - to co hashmap ale zachowuje kolejnosc

        CarDao carDao = new CarDao();
        List<Car> cars;
        if (clients != null)
        {
            for (Client client : clients)
            {
                cars = carDao.findAll("WHERE `client_id` = " + client.getId());
                clientsCarsMap.put(client, cars);
            }
        }
        else
        {
            clientsCarsMap = null;
        }

        request.setAttribute("clientsCarsMap", clientsCarsMap);

        getServletContext().getRequestDispatcher("/WEB-INF/views/client/list.jsp").forward(request, response);
    }
}
