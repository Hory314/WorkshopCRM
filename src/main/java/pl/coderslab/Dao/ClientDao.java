package pl.coderslab.Dao;

import pl.coderslab.Entity.Client;
import pl.coderslab.Service.DBService;
import pl.coderslab.Service.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientDao
{
    private String dbName = "workshop_crm";
    private String tableName = "client";

    public List<Client> findAll(String where)
    {
        where = (where == null) ? "" : where;

        String query = "Select * from " + tableName + " " + where + " ORDER BY `surname` ASC";

        System.out.println(query);

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<Client> clients = new ArrayList<>();

            if (result.size() > 0)
            {
                for (Map<String, String> row : result)
                {

                    Client client = createClient(row);
                    clients.add(client);
                }
                return clients;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public List<Client> findAll()
    {
        return findAll(null);
    }

    public Client getById(Integer id)
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
                Map<String, String> resultClient = result.get(0);

                Client client = createClient(resultClient);

                return client;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    private Client createClient(Map<String, String> row)
    {
        Client client = new Client();

        client.setId(Integer.parseInt(row.get("id")));
        client.setFirstName(row.get("first_name"));
        client.setSurname(row.get("surname"));
        client.setBirthdate(row.get("birthdate"));

        return client;
    }

    public void save(Client client)
    {
        if (client.getId() == null)
        {
            //add new userGroup
            add(client);
        }
        else
        {
            //update userGroup
            update(client);
        }
    }

    private void add(Client client)
    {

        String query = "Insert into " + tableName + " values (null, ?, ?, ?)"; // pamietac o kolejnosci dodawania do listy params

        List<String> params = new ArrayList<>();
        params.add(client.getFirstName());
        params.add(client.getSurname());
        params.add(client.getBirthdate());

        DaoService.setNewId(client, query, params, dbName); // zawiera insert do bazy
    }

    private void update(Client client)
    {

        String query = "Update " + tableName + " Set `first_name` = ?, `surname` = ?, `birthdate` = ? where `id` = ?";

        List<String> params = new ArrayList<>();
        params.add(client.getFirstName());
        params.add(client.getSurname());
        params.add(client.getBirthdate());
        params.add(client.getId().toString());

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
