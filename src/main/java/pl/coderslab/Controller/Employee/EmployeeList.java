package pl.coderslab.Controller.Employee;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Dao.ClientDao;
import pl.coderslab.Dao.EmployeeDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeList", urlPatterns = {"/employees", "/employees/"})
public class EmployeeList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employees = employeeDao.findAll();


        Map<Employee, List<Repair>> employeesRepairsMap = new LinkedHashMap<>(); // LinkedHashMap - to co hashmap ale zachowuje kolejnosc

        RepairDao repairDao = new RepairDao();
        List<Repair> repairs;
        for (Employee employee : employees)
        {
            repairs = repairDao.findAll("WHERE `employee_id` = " + employee.getId() + " AND `status` = '" + Status.IN_REPAIR + "'");
            employeesRepairsMap.put(employee, repairs);
        }

        request.setAttribute("employeesRepairsMap", employeesRepairsMap);

        getServletContext().getRequestDispatcher("/WEB-INF/views/employee/list.jsp").forward(request, response);
    }
}
