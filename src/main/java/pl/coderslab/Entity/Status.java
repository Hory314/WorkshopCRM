package pl.coderslab.Entity;

public enum Status
{
    ACCEPTED,
    COST_APPROVED,
    IN_REPAIR,
    READY,
    CANCELED;

    @Override
    public String toString()
    {
        return super.toString().toLowerCase(); // zwrac to samo co te napisy Enum. A do malej bo takie jest w bazie
    }
}
