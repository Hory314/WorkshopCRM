package pl.coderslab.Controller.Employee;

import pl.coderslab.Dao.EmployeeDao;
import pl.coderslab.Entity.Employee;
import pl.coderslab.Entity.Repair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EmployeeEdit", urlPatterns = {"/employee/edit", "/employee/edit/"})
public class EmployeeEdit extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        Employee employee = (Employee) session.getAttribute("employee");
        session.removeAttribute("employee");

        Double pay;
        try
        {
            pay = Double.parseDouble(request.getParameter("pay"));
        }
        catch (NumberFormatException e)
        {
            pay = 0.0;
        }

        employee.setFirstName(request.getParameter("first_name"));
        employee.setSurname(request.getParameter("surname"));
        employee.setAddress(request.getParameter("address"));
        employee.setPhone(request.getParameter("phone"));
        employee.setNote(request.getParameter("note"));
        employee.setPay(pay);

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.save(employee);
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/employees"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");

        try
        {
            Integer intId = Integer.parseInt(id);

            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = employeeDao.getById(intId);

            request.setAttribute("employee", employee);

            HttpSession session = request.getSession(true);
            session.setAttribute("employee", employee); // do doPost()

            getServletContext().getRequestDispatcher("/WEB-INF/views/employee/edit.jsp").forward(request, response);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("to nie jest id pracownika");
        }
    }
}
