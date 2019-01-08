package pl.coderslab.Controller.Repair;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Dao.EmployeeDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Car;
import pl.coderslab.Entity.Employee;
import pl.coderslab.Entity.Repair;
import pl.coderslab.Entity.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RepairEdit", urlPatterns = {"/repair/edit", "/repair/edit/"})
public class RepairEdit extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        Repair repair = (Repair) session.getAttribute("repair");
        session.removeAttribute("repair");

        String employeeId = request.getParameter("employee_id");
        String carId = request.getParameter("car_id");

        try
        {
            Integer intEmployeeId = Integer.parseInt(employeeId);
            Integer intCarId = Integer.parseInt(carId);

            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = employeeDao.getById(intEmployeeId);

            CarDao carDao = new CarDao();
            Car car = carDao.getById(intCarId);


            repair.setStart(request.getParameter("start").equals("") ? null : request.getParameter("start"));
            repair.setEnd(request.getParameter("end").equals("") ? null : request.getParameter("end"));
            repair.setProblemDesc(request.getParameter("problem_desc").equals("") ? null : request.getParameter("problem_desc"));
            repair.setRepairDesc(request.getParameter("repair_desc").equals("") ? null : request.getParameter("repair_desc"));
            switch (request.getParameter("status"))
            {
                case "accepted":
                    repair.setStatus(Status.ACCEPTED);
                    break;
                case "cost_approved":
                    repair.setStatus(Status.COST_APPROVED);
                    break;
                case "in_repair":
                    repair.setStatus(Status.IN_REPAIR);
                    break;
                case "ready":
                    repair.setStatus(Status.READY);
                    break;
                case "canceled":
                    repair.setStatus(Status.CANCELED);
                    break;
            }
            repair.setClientCost(request.getParameter("client_cost").equals("") ? null : Double.parseDouble(request.getParameter("client_cost")));
            repair.setPartsCost(request.getParameter("parts_cost").equals("") ? null : Double.parseDouble(request.getParameter("parts_cost")));
            repair.setPay(employee.getPay());
            repair.setWorkHours(request.getParameter("work_hours").equals("") ? null : Double.parseDouble(request.getParameter("work_hours")));
            repair.setEmployee(employee);
            repair.setCar(car);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("Id nie jest liczbą");
        }

        RepairDao repairDao = new RepairDao();
        repairDao.save(repair);
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/repair?id=" + repair.getId()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");

        try
        {
            Integer intId = Integer.parseInt(id);

            RepairDao repairDao = new RepairDao();
            Repair repair = repairDao.getById(intId);

            CarDao carDao = new CarDao();
            List<Car> cars = carDao.findAll("select car.id, brand, model, prod_year, plate_number, client_id, first_name, surname from car\n" +
                    "join client c on car.client_id = c.id\n" +
                    "order by c.surname, c.first_name ASC"); // złączam do sortowania tylko

            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> employees = employeeDao.findAll();

            request.setAttribute("repair", repair);
            request.setAttribute("cars", cars);
            request.setAttribute("employees", employees);

            HttpSession session = request.getSession(true);
            session.setAttribute("repair", repair); // do doPost()

            getServletContext().getRequestDispatcher("/WEB-INF/views/repair/edit.jsp").forward(request, response);
        }
        catch (NumberFormatException e)
        {
            System.out.println("To nie jest ID.");
        }
    }
}
