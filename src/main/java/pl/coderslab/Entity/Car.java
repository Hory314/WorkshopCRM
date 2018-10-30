package pl.coderslab.Entity;

public class Car
{
    private Integer id;
    private String brand;
    private String model;
    private Integer year;
    private String plateNumber;
    private String nextInspection;
    private Client client;

    public Car()
    {
    }

    public Car(String brand, String model, Integer year, String plateNumber, String nextInspection, Client client)
    {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.plateNumber = plateNumber;
        this.nextInspection = nextInspection;
        this.client = client;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public String getPlateNumber()
    {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber)
    {
        this.plateNumber = plateNumber;
    }

    public String getNextInspection()
    {
        return nextInspection;
    }

    public void setNextInspection(String nextInspection)
    {
        this.nextInspection = nextInspection;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }
}
