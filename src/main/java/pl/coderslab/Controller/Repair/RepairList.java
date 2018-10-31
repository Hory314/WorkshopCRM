package pl.coderslab.Controller.Repair;

import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Repair;
import pl.coderslab.Entity.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RepairList", urlPatterns = {"", "/repairs", "/repairs/"}) // "" = main site
public class RepairList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RepairDao repairDao = new RepairDao();
        List<Repair> repairs;

        if (request.getServletPath().equals("")) // glowna strona
        {
            repairs = repairDao.findAll("WHERE `status` = '" + Status.IN_REPAIR + "'"); // to tylko in_repair ładuj
        } else
        {
            repairs = repairDao.findAll(); // repairs, to ładuj all
        }

        request.setAttribute("repairs", repairs);
        getServletContext().getRequestDispatcher("/WEB-INF/views/repair/list.jsp").forward(request, response);
    }
}
