package pl.coderslab.Controller.Employee;

import pl.coderslab.Dao.EmployeeDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeDelete", urlPatterns = {"/employee/delete", "/employee/delete/"})
public class EmployeeDelete extends HttpServlet
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

            EmployeeDao employeeDao = new EmployeeDao();
            employeeDao.delete(intId);

            response.sendRedirect(response.encodeRedirectURL("/employees"));
        }
        catch (NumberFormatException e)
        {
            System.out.println("To nie jest ID.");
        }
    }
}
