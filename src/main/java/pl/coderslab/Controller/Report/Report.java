package pl.coderslab.Controller.Report;

import pl.coderslab.Dao.EmployeeDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Employee;
import pl.coderslab.Service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Report", urlPatterns = {"/reports", "/reports/"})
public class Report extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String type = request.getParameter("type");

        EmployeeDao employeeDao = new EmployeeDao();
        try
        {
            List<Map<String, String>> result;
            if (type.equals("1"))
            {
                result = DBService.executeSelectQuery("workshop_crm", "select employee.id, first_name, surname, date, SUM(work_hours) as sumh from employee\n" +
                        "join repair r on employee.id = r.employee_id\n" +
                        "where date between '" + from + " 00:00:00' and '" + to + " 00:00:00'\n" +
                        "group by employee.id\n" +
                        "order by surname, first_name asc", null);


            }
            else if (type.equals("2"))
            {
                result = null; // todo raport #2
            }
            else
            {
                System.out.println("Nie ma takiego raportu");
                return;
            }

            if (result.size() > 0)
            {
                request.setAttribute("result", result);
            }
            else
            {
                System.out.println("Brak wyników");
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/report/report1.jsp").forward(request, response);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/WEB-INF/views/report/report.jsp").forward(request, response);
    }
}
