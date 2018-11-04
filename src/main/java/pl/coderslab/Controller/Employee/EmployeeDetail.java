package pl.coderslab.Controller.Employee;

import pl.coderslab.Dao.EmployeeDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Employee;
import pl.coderslab.Entity.Repair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeDetail", urlPatterns = {"/employee", "/employee/"})
public class EmployeeDetail extends HttpServlet
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
            Employee employee = employeeDao.getById(intId);

            RepairDao repairDao = new RepairDao();
            List<Repair> repairs = repairDao.findAll("WHERE `employee_id` = " + intId);

            request.setAttribute("employee", employee);
            request.setAttribute("repairs", repairs);
            getServletContext().getRequestDispatcher("/WEB-INF/views/employee/detail.jsp").forward(request, response);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("to nie jest id");
        }
    }
}
