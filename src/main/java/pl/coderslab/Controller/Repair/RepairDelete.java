package pl.coderslab.Controller.Repair;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Dao.EmployeeDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Car;
import pl.coderslab.Entity.Employee;
import pl.coderslab.Entity.Repair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RepairDelete", urlPatterns = {"/repair/delete", "/repair/delete/"})
public class RepairDelete extends HttpServlet
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
            repairDao.delete(intId);

            response.sendRedirect(response.encodeRedirectURL("/repairs"));
        }
        catch (NumberFormatException e)
        {
            System.out.println("To nie jest ID.");
        }
    }
}
