package pl.coderslab.Entity;

public enum Status
{
    ACCEPTED
            {
                public String getValue()
                {
                    return "accepted";
                }
            },
    COST_APPROVED
            {
                public String getValue()
                {
                    return "cost_approved";
                }
            },
    IN_REPAIR
            {
                public String getValue()
                {
                    return "in_repair";
                }
            },
    READY
            {
                public String getValue()
                {
                    return "ready";
                }
            },
    CANCELED
            {
                public String getValue()
                {
                    return "canceled";
                }
            };

    public abstract String getValue();

    @Override
    public String toString()
    {
        return super.toString();
    }
}
