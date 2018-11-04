package pl.coderslab.Controller.Repair;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RepairAdd", urlPatterns = {"/repair/add", "/repair/add/"})
public class RepairAdd extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String start = request.getParameter("start");
        String problemDesc = request.getParameter("problem_desc");
        String emoloyeeId = request.getParameter("emoloyee_id");
        String carId = request.getParameter("car_id");

        try
        {
            Integer intEmployeeId = Integer.parseInt(emoloyeeId);
            Integer intCarId = Integer.parseInt(carId);

            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = employeeDao.getById(intEmployeeId);

            CarDao carDao = new CarDao();
            Car car = carDao.getById(intCarId);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            Repair repair = new Repair();
            repair.setDate(now.format(formatter)); // data dodania
            repair.setStart(start);
            repair.setProblemDesc(problemDesc);
            repair.setStatus(Status.ACCEPTED);
            repair.setPay(employee.getPay());
            repair.setEmployee(employee);
            repair.setCar(car);

            RepairDao repairDao = new RepairDao();
            repairDao.save(repair);

            request.setAttribute("info", "<p style='color: green;'>Dodano</p>");
        }
        catch (NumberFormatException e)
        {
            request.setAttribute("info", "<p style='color: green;'>Nie udało się dodać.</p>");
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CarDao carDao = new CarDao();
        List<Car> cars = carDao.findAll("select car.id, brand, model, prod_year, plate_number, client_id, first_name, surname from car\n" +
                "join client c on car.client_id = c.id\n" +
                "order by c.surname, c.first_name ASC"); // złączam do sortowania tylko

        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employees = employeeDao.findAll();

        request.setAttribute("cars", cars);
        request.setAttribute("employees", employees);

        getServletContext().getRequestDispatcher("/WEB-INF/views/repair/add.jsp").forward(request, response);
    }
}
