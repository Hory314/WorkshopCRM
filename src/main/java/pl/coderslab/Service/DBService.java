package pl.coderslab.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBService
{

    public static Connection connect(String database) throws SQLException
    {
        /**
         Loading class `com.mysql.jdbc.Driver'. This is deprecated.
         The new driver class is `com.mysql.cj.jdbc.Driver'.
         The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
         */
        try
        {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + database + "?useUnicode=yes&characterEncoding=UTF-8" +
                        "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" +
                        "&useSSL=false",
                "root",
                "coderslab");
    }

    public static int numberOfRows(ResultSet rs) throws SQLException
    {
        int rowCount = 0;
        if (rs.last())
        {
            rowCount = rs.getRow();
            rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
        }
        return rowCount;

    }

    /**
     * For MySQL SELECT
     *
     * @param database database name
     * @param query    MySQL SELECT query (with zero or more ?)
     * @param params   list of parameters (?) in right order (as String)
     * @return List of Maps. Each list element is a row from database as Map. Map contains pairs column_name -> value (String, String)
     * @throws SQLException
     */
    // each selected row in a list as map
    public static List<Map<String, String>> executeSelectQuery(String database, String query, List<String> params) throws SQLException
    {
        //Prepare variable for results
        List<Map<String, String>> dbResult = new ArrayList<>();

        try (Connection conn = connect(database))
        {

            //prepare query
            PreparedStatement prep = conn.prepareStatement(query);

            //set params for query
            if (params != null)
            {
                for (int i = 0; i < params.size(); i++)
                {
                    prep.setString(i + 1, params.get(i));
                }
            }

            //get results after execute query
            ResultSet result = prep.executeQuery();

            //get metadata (ex. columnsCount, columnsName etc.) from result
            ResultSetMetaData rsmd = result.getMetaData();
            int columnsCount = rsmd.getColumnCount();

            //build result for function
            if (result != null)
            {
                //get each row from executed db query
                while (result.next())
                {
                    //create Map for data in row
                    Map<String, String> rowResult = new HashMap<>();

                    //get columnName &&  value in that column and put to map
                    for (int i = 1; i <= columnsCount; i++)
                    {
                        rowResult.put(rsmd.getColumnName(i), result.getString(i));
                    }

                    //we have prepared data row, so add it to global result
                    dbResult.add(rowResult);
                }
            }

        }
        catch (SQLException e)
        {
            throw e;
        }

        return dbResult;
    }

    /**
     * For MySQL INSERT
     *
     * @param database database name
     * @param query    MySQL INSERT query (with zero or more ?)
     * @param params   list of parameters (?) in right order (as String)
     * @return ID of new created row in DB. If insertion fail returns <code>null</code>.
     * @throws SQLException
     */
    public static Integer executeInsert(String database, String query, List<String> params) throws SQLException
    {
        Integer newId = null;

        try (Connection con = connect(database))
        {
            //prepare statement and force to get genereate keys - we will need to get new ID
            PreparedStatement prep = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            if (params != null)
            {
                for (int i = 0; i < params.size(); i++)
                {
                    prep.setString(i + 1, params.get(i));
                }
            }

            prep.executeUpdate();
            System.out.println("executeInsert wykonany");

            //GET NEW ID
            try (ResultSet generatedKeys = prep.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    newId = generatedKeys.getInt(1);
                }
                else
                {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }

        }
        catch (SQLException e)
        {
            System.out.println(e);
            throw e;
        }

        return newId;
    }

    /**
     * For MySQL UPDATE and DELETE
     *
     * @param database database name
     * @param query    MySQL UPDATE or DELETE query (with zero or more ?)
     * @param params   list of parameters (?) in right order (as String)
     * @throws SQLException
     */
    public static void executeQuery(String database, String query, List<String> params) throws SQLException
    {
        try (Connection con = connect(database))
        {
            PreparedStatement prep = con.prepareStatement(query);

            if (params != null)
            {
                for (int i = 0; i < params.size(); i++)
                {
                    prep.setString(i + 1, params.get(i));
                }
            }

            prep.executeUpdate();
            System.out.println("executeUpdate wykonany");
        }
        catch (SQLException e)
        {
            throw e;
        }
    }
}
