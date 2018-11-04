package pl.coderslab.Controller.Car;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Dao.ClientDao;
import pl.coderslab.Entity.Car;
import pl.coderslab.Entity.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CarAdd", urlPatterns = {"/car/add", "/car/add/"})
public class CarAdd extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Car car = new Car();
            car.setBrand(request.getParameter("brand"));
            car.setModel(request.getParameter("model"));
            car.setProdYear(Integer.parseInt(request.getParameter("prod_year")));
            car.setPlateNumber(request.getParameter("plate_number"));
            car.setNextInspection(request.getParameter("next_inspection").equals("") ? null : request.getParameter("next_inspection"));

            String cid = request.getParameter("cid");
            Integer intCid = Integer.parseInt(cid);
            ClientDao clientDao = new ClientDao();
            Client client = clientDao.getById(intCid);

            car.setClient(client);

            CarDao carDao = new CarDao();
            carDao.save(car);

            response.sendRedirect(response.encodeRedirectURL("/customer?id=" + intCid));
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("numer rej nie jest liczba lub id klienta nie jest liczba");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/WEB-INF/views/car/add.jsp").forward(request, response);
    }
}
