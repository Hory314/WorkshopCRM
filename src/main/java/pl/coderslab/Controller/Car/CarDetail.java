package pl.coderslab.Controller.Car;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Car;
import pl.coderslab.Entity.Repair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CarDetail", urlPatterns = {"/car", "/car/"})
public class CarDetail extends HttpServlet
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
            RepairDao repairDao = new RepairDao();
            List<Repair> repairs = repairDao.findAll("WHERE `car_id` = " + intId);

            CarDao carDao = new CarDao();
            Car car = carDao.getById(intId);

            request.setAttribute("repairs", repairs);
            request.setAttribute("car", car);

            getServletContext().getRequestDispatcher("/WEB-INF/views/car/detail.jsp").forward(request, response);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("to id nie jest liczba");
        }
    }
}
