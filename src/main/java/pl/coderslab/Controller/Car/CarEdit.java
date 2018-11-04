package pl.coderslab.Controller.Car;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Entity.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CarEdit", urlPatterns = {"/car/edit", "/car/edit/"})
public class CarEdit extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        Car car = (Car) session.getAttribute("car");
        session.removeAttribute("car");

        car.setBrand(request.getParameter("brand"));
        car.setModel(request.getParameter("model"));
        car.setProdYear(Integer.parseInt(request.getParameter("prod_year")));
        car.setPlateNumber(request.getParameter("plate_number"));
        car.setNextInspection(request.getParameter("next_inspection").equals("") ? null : request.getParameter("next_inspection"));

        CarDao carDao = new CarDao();
        carDao.save(car);

        response.sendRedirect(response.encodeRedirectURL("/car?id=" + car.getId()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");

        try
        {
            Integer intId = Integer.parseInt(id);
            CarDao carDao = new CarDao();
            Car car = carDao.getById(intId);

            HttpSession session = request.getSession(true);
            session.setAttribute("car", car); // do doPost();

            request.setAttribute("car", car);

            getServletContext().getRequestDispatcher("/WEB-INF/views/car/edit.jsp").forward(request, response);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("to id nie jest liczba");
        }
    }
}
