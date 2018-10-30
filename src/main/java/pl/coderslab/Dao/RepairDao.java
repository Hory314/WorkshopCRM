package pl.coderslab.Dao;

import pl.coderslab.Entity.*;
import pl.coderslab.Service.DBService;
import pl.coderslab.Service.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RepairDao
{
    private String dbName = "workshop_crm";
    private String tableName = "repair";

    public List<Repair> findAll()
    {

        String query = "Select * from " + tableName;
        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<Repair> repairs = new ArrayList<>();

            if (result.size() > 0)
            {
                for (Map<String, String> row : result)
                {

                    Repair repair = createRepair(row);
                    repairs.add(repair);
                }
                return repairs;
            }
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public Repair getById(Integer id)
    {
        String query = "Select * from " + tableName + " where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultRepair = result.get(0);

                Repair repair = createRepair(resultRepair);

                return repair;
            }
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    private Repair createRepair(Map<String, String> row)
    {
        Repair repair = new Repair();
        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = employeeDao.getById(Integer.parseInt(row.get("employee_id")));
        CarDao carDao = new CarDao();
        Car car = carDao.getById(Integer.parseInt(row.get("car_id")));

        repair.setId(Integer.parseInt(row.get("id")));
        repair.setDate(row.get("date"));
        repair.setStart(row.get("start"));
        repair.setEnd(row.get("end"));
        repair.setProblemDesc(row.get("problem_desc"));
        repair.setRepairDesc(row.get("repair_desc"));
        repair.setStatus(Status.valueOf(row.get("status").toUpperCase()));
        repair.setClientCost(Double.parseDouble(row.get("client_cost")));
        repair.setPartsCost(Double.parseDouble(row.get("parts_cost")));
        repair.setPay(employee);
        repair.setWorkHours(Double.parseDouble(row.get("work_hours")));
        repair.setEmployee(employee);
        repair.setCar(car);

        return repair;
    }

    public void save(Repair repair)
    {
        if (repair.getId() == null)
        {
            //add new userGroup
            add(repair);
        } else
        {
            //update userGroup
            update(repair);
        }
    }

    private void add(Repair repair)
    {

        String query = "Insert into " + tableName + " values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // pamietac o kolejnosci dodawania do listy params

        List<String> params = new ArrayList<>();
        params.add(repair.getDate());
        params.add(repair.getStart());
        params.add(repair.getEnd());
        params.add(repair.getProblemDesc());
        params.add(repair.getRepairDesc());
        params.add(repair.getStatus().toString());
        params.add(repair.getClientCost().toString());
        params.add(repair.getPartsCost().toString());
        params.add(repair.getPay().toString());
        params.add(repair.getWorkHours().toString());
        params.add(repair.getEmployee().getId().toString());
        params.add(repair.getCar().getId().toString());

        DaoService.setNewId(repair, query, params, dbName); // zawiera insert do bazy
    }

    private void update(Repair repair)
    {

        String query = "Update " + tableName + " Set `date` = ?, `start` = ?, `end` = ?, `problem_desc` = ?, `repair_desc` = ?, `status` = ?, `client_cost` = ?, `parts_cost` = ?, `pay` = ?, `work_hours` = ?, `employee_id` = ?, `car_id` = ? where `id` = ?";

        List<String> params = new ArrayList<>();
        params.add(repair.getDate());
        params.add(repair.getStart());
        params.add(repair.getEnd());
        params.add(repair.getProblemDesc());
        params.add(repair.getRepairDesc());
        params.add(repair.getStatus().toString());
        params.add(repair.getClientCost().toString());
        params.add(repair.getPartsCost().toString());
        params.add(repair.getPay().toString());
        params.add(repair.getWorkHours().toString());
        params.add(repair.getEmployee().getId().toString());
        params.add(repair.getCar().getId().toString());
        params.add(repair.getId().toString());

        try
        {
            DBService.executeQuery(dbName, query, params);
        } catch (SQLException e)
        {
            //should be logger - save info about error
            System.out.println(e);
        }
    }

    public boolean delete(Integer id)
    {
        return DaoService.delete(id, tableName, dbName);
    }


}
