package pl.coderslab.Controller.Employee;

import pl.coderslab.Dao.EmployeeDao;
import pl.coderslab.Entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeAdd", urlPatterns = {"/employee/add", "/employee/add/"})
public class EmployeeAdd extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Employee employee = new Employee();
        employee.setFirstName(request.getParameter("first_name"));
        employee.setSurname(request.getParameter("surname"));
        employee.setAddress(request.getParameter("address"));
        employee.setPhone(request.getParameter("phone"));
        employee.setNote(request.getParameter("note"));
        employee.setPay(request.getParameter("pay").equals("") ? null : Double.parseDouble(request.getParameter("pay")));

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.save(employee);
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/employees"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/WEB-INF/views/employee/add.jsp").forward(request, response);
    }
}
