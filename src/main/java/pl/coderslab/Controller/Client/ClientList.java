package pl.coderslab.Controller.Client;

import pl.coderslab.Dao.CarDao;
import pl.coderslab.Dao.RepairDao;
import pl.coderslab.Entity.Car;
import pl.coderslab.Entity.Repair;
import pl.coderslab.Entity.Status;
import pl.coderslab.Service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ClientList", urlPatterns = {"/customers/", "/customers"})
public class ClientList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//          RepairDao repairDao = new RepairDao();
//         Repair repair = repairDao.getById(1);
//
//         repair.setStatus(Status.IN_REPAIR);
//         repairDao.save(repair);
//
//        Repair repair2 = repairDao.getById(1);

        //  CarDao carDao = new CarDao();
        //   Car car = carDao.getById(1);

//        try
//        {
//            List<Map<String, String>> years =  DBService.executeSelectQuery("workshop_crm","select `prod_year` from car",null);
//            for(Map<String,String> row : years)
//            {
//                System.out.println(row.get("prod_year"));
//            }
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }

        response.getWriter().append("lista klientów");
    }
}
