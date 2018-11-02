package pl.coderslab.Entity;

public class Repair
{
    private Integer id;
    private String date;
    private String start;
    private String end;
    private String problemDesc;
    private String repairDesc;
    private Status status;
    private Double clientCost;
    private Double partsCost;
    private Double pay;
    private Double workHours;
    private Employee employee;
    private Car car;

    public Repair()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getStart()
    {
        return start;
    }

    public void setStart(String start)
    {
        this.start = start;
    }

    public String getEnd()
    {
        return end;
    }

    public void setEnd(String end)
    {
        this.end = end;
    }

    public String getProblemDesc()
    {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc)
    {
        this.problemDesc = problemDesc;
    }

    public String getRepairDesc()
    {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc)
    {
        this.repairDesc = repairDesc;
    }

    public String getStringStatus()
    {
        switch (status)
        {
            case ACCEPTED:
                return "Przyjęty";
            case COST_APPROVED:
                return "Zatwierdzone koszty naprawy";
            case IN_REPAIR:
                return "W naprawie";
            case READY:
                return "Gotowy do odbioru";
            case CANCELED:
                return "Rezygnacja";
            default:
                return null;
        }
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public Double getClientCost()
    {
        return clientCost;
    }

    public void setClientCost(Double clientCost)
    {
        this.clientCost = clientCost;
    }

    public Double getPartsCost()
    {
        return partsCost;
    }

    public void setPartsCost(Double partsCost)
    {
        this.partsCost = partsCost;
    }

    public Double getPay()
    {
        return pay;
    }

    public void setPay(Employee employee)
    {
        this.pay = employee.getPay(); // myk
    }

    public Double getWorkHours()
    {
        return workHours;
    }

    public void setWorkHours(Double workHours)
    {
        this.workHours = workHours;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
        this.pay = employee.getPay();
    }

    public Car getCar()
    {
        return car;
    }

    public void setCar(Car car)
    {
        this.car = car;
    }

    @Override
    public String toString()
    {
        return "Repair{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", problemDesc='" + problemDesc + '\'' +
                ", repairDesc='" + repairDesc + '\'' +
                ", status=" + status +
                ", clientCost=" + clientCost +
                ", partsCost=" + partsCost +
                ", pay=" + pay +
                ", workHours=" + workHours +
                ", employee=" + employee +
                ", car=" + car +
                '}';
    }
}
