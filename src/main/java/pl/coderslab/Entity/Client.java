package pl.coderslab.Entity;

public class Client
{
    private Integer id;
    private String firstName;
    private String surname;
    private String birthdate;

    public Client()
    {
    }

    public Client(String firstName, String surname, String birthdate)
    {
        this.firstName = firstName;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(String birthdate)
    {
        this.birthdate = birthdate;
    }

    @Override
    public String toString()
    {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
