package pl.coderslab.Controller.Car;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Dao.RepairDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CarDelete", urlPatterns = {"/car/delete", "/car/delete/"})
public class CarDelete extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        String customerId = request.getParameter("cid");

        try
        {
            Integer intId = Integer.parseInt(id);

            CarDao carDao = new CarDao();
            carDao.delete(intId);

            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/customer?id=" + customerId));
        }
        catch (NumberFormatException e)
        {
            System.out.println("To nie jest ID.");
        }
    }
}
