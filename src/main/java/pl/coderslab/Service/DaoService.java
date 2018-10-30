package pl.coderslab.Service;

import pl.coderslab.Entity.Car;
import pl.coderslab.Entity.Client;
import pl.coderslab.Entity.Employee;
import pl.coderslab.Entity.Repair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoService
{
    public static void setNewId(Object obj, String query, List<String> params, String dbName)
    {
        try
        {
            Integer newId = DBService.executeInsert(dbName, query, params); // insert do bazy
            if (newId != null)
            {

                if (obj instanceof Car)
                {
                    ((Car) obj).setId(newId); // hmm powinno działać
                }
                else if (obj instanceof Client)
                {
                    ((Client) obj).setId(newId); // hmm powinno działać
                }
                else if (obj instanceof Employee)
                {
                    ((Employee) obj).setId(newId); // hmm powinno działać
                }
                else if (obj instanceof Repair)
                {
                    ((Repair) obj).setId(newId); // hmm powinno działać
                }
            }
        }
        catch (SQLException e)
        {
            //should be logger - save info about error
            System.out.println(e);
        }
    }

    // delete (by id)  is universal for all Daos
    public static boolean delete(Integer id, String tableName, String dbName)
    {
        try
        {
            String query = "Delete From " + tableName + " where `id`=?";
            List<String> params = new ArrayList<>();
            params.add(id.toString());

            DBService.executeQuery(dbName, query, params);
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
