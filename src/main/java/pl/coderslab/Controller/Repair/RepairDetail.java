package pl.coderslab.Controller.Repair;

import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Repair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RepairDetail", urlPatterns = {"/repair", "/repair/"})
public class RepairDetail extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        Repair repair = null;

        try
        {
            Integer intId = Integer.parseInt(id);

            RepairDao repairDao = new RepairDao();
            repair = repairDao.getById(intId);
        }
        catch (NumberFormatException e)
        {
            System.out.println("To nie jest ID.");
        }

        request.setAttribute("repair", repair);
        getServletContext().getRequestDispatcher("/WEB-INF/views/repair/detail.jsp").forward(request, response);
    }
}
