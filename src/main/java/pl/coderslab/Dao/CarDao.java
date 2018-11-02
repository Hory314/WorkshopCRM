package pl.coderslab.Dao;

import pl.coderslab.Entity.Car;
import pl.coderslab.Entity.Client;
import pl.coderslab.Service.DBService;
import pl.coderslab.Service.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarDao
{
    private String dbName = "workshop_crm";
    private String tableName = "car";

    public List<Car> findAll(String condition)
    {
        condition = (condition == null) ? "" : condition;

        String query = "Select * from " + tableName + " " + condition;

        if(condition.toLowerCase().startsWith("select")) // jesli warunek zaczyna sie od SELECT to bedzie caly select a nie tylko warunek
        {
            query = condition;
        }

        System.out.println(query);

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<Car> cars = new ArrayList<>();

            if (result.size() > 0)
            {
                for (Map<String, String> row : result)
                {

                    Car car = createCar(row);
                    cars.add(car);
                }
                return cars;
            }
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public List<Car> findAll()
    {
        return findAll(null);
    }

    public Car getById(Integer id)
    {
        String query = "Select * from " + tableName + " where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultCar = result.get(0);

                Car car = createCar(resultCar);

                return car;
            }
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    private Car createCar(Map<String, String> row)
    {
        Car car = new Car();

        car.setId(Integer.parseInt(row.get("id")));
        car.setBrand(row.get("brand"));
        car.setModel(row.get("model"));
        car.setProdYear(Integer.parseInt(row.get("prod_year")));
        car.setPlateNumber(row.get("plate_number"));
        car.setNextInspection(row.get("next_inspection"));

        ClientDao clientDao = new ClientDao();
        Client client = clientDao.getById(Integer.parseInt(row.get("client_id")));
        car.setClient(client);

        return car;
    }

    public void save(Car car)
    {
        if (car.getId() == null)
        {
            //add new userGroup
            add(car);
        } else
        {
            //update userGroup
            update(car);
        }
    }

    private void add(Car car)
    {

        String query = "Insert into " + tableName + " values (null, ?, ?, ?, ?, ?, ?)"; // pamietac o kolejnosci dodawania do listy params

        List<String> params = new ArrayList<>();
        params.add(car.getBrand());
        params.add(car.getModel());
        params.add(car.getProdYear().toString());
        params.add(car.getPlateNumber());
        params.add(car.getNextInspection());
        params.add(car.getClient().getId().toString());

        DaoService.setNewId(car, query, params, dbName); // zawiera insert do bazy
    }

    private void update(Car car)
    {

        String query = "Update " + tableName + " Set `brand` = ?, `model` = ?, `prod_year` = ?, `plate_number` = ?, `next_inspection` = ?, `client_id` = ? where `id` = ?";

        List<String> params = new ArrayList<>();
        params.add(car.getBrand());
        params.add(car.getModel());
        params.add(car.getProdYear().toString());
        params.add(car.getPlateNumber());
        params.add(car.getNextInspection());
        params.add(car.getClient().getId().toString());
        params.add(car.getId().toString());

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
