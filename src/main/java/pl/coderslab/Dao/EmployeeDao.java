package pl.coderslab.Dao;

import pl.coderslab.Entity.Client;
import pl.coderslab.Entity.Employee;
import pl.coderslab.Service.DBService;
import pl.coderslab.Service.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeDao
{
    private String dbName = "workshop_crm";
    private String tableName = "employee";

    public List<Employee> findAll(String condition)
    {
        condition = (condition == null) ? "" : condition;

        String query = "Select * from " + tableName + " ORDER BY `surname` ASC";

        if (condition.toLowerCase().startsWith("select")) // jesli warunek zaczyna sie od SELECT to bedzie caly select a nie tylko warunek
        {
            query = condition;
        }
        System.out.println(query);

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<Employee> employees = new ArrayList<>();

            if (result.size() > 0)
            {
                for (Map<String, String> row : result)
                {

                    Employee employee = createEmployee(row);
                    employees.add(employee);
                }
                return employees;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public List<Employee> findAll()
    {
        return findAll(null);
    }

    public Employee getById(Integer id)
    {
        if (id == null)
        {
            return null;
        }

        String query = "Select * from " + tableName + " where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultEmployee = result.get(0);

                Employee employee = createEmployee(resultEmployee);

                return employee;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    private Employee createEmployee(Map<String, String> row)
    {
        Employee employee = new Employee();

        employee.setId(Integer.parseInt(row.get("id")));
        employee.setFirstName(row.get("first_name"));
        employee.setSurname(row.get("surname"));
        employee.setAddress(row.get("address"));
        employee.setPhone(row.get("phone"));
        employee.setNote(row.get("note"));
        employee.setPay(row.get("pay") == null ? null : Double.parseDouble(row.get("pay")));

        return employee;
    }

    public void save(Employee employee)
    {
        if (employee.getId() == null)
        {
            //add new userGroup
            add(employee);
        }
        else
        {
            //update userGroup
            update(employee);
        }
    }

    private void add(Employee employee)
    {

        String query = "Insert into " + tableName + " values (null, ?, ?, ?, ?, ?, ?)"; // pamietac o kolejnosci dodawania do listy params

        List<String> params = new ArrayList<>();
        params.add(employee.getFirstName());
        params.add(employee.getSurname());
        params.add(employee.getAddress());
        params.add(employee.getPhone());
        params.add(employee.getNote());
        params.add(employee.getPay() == null ? null : employee.getPay().toString());

        DaoService.setNewId(employee, query, params, dbName); // zawiera insert do bazy
    }

    private void update(Employee employee)
    {

        String query = "Update " + tableName + " Set `first_name` = ?, `surname` = ?, `address` = ?, `phone` = ?, `note` = ?, `pay` = ? where `id` = ?";

        List<String> params = new ArrayList<>();

        params.add(employee.getFirstName());
        params.add(employee.getSurname());
        params.add(employee.getAddress());
        params.add(employee.getPhone());
        params.add(employee.getNote());
        params.add(employee.getPay().toString());
        params.add(employee.getId().toString());

        try
        {
            DBService.executeQuery(dbName, query, params);
        }
        catch (SQLException e)
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
