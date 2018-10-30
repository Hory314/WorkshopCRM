package pl.coderslab.Entity;

public class Employee
{
    private Integer id;
    private String firstName;
    private String surname;
    private String address;
    private String phone;
    private String note;
    private Double pay;

    public Employee()
    {
    }

    public Employee(String firstName, String surname, String address, String phone, String note, Double pay)
    {
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.pay = pay;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public Double getPay()
    {
        return pay;
    }

    public void setPay(Double pay)
    {
        this.pay = pay;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", note='" + note + '\'' +
                ", pay=" + pay +
                '}';
    }
}
