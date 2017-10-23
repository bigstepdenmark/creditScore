package dk.cphsoftdev.app.entity;

public class Loan
{
    private String ssn;
    private int creditScore;
    private double amount;
    private int duration;

    public Loan()
    {
    }

    public Loan( String ssn, double amount, int duration )
    {
        this.ssn = ssn;
        this.amount = amount;
        this.duration = duration;
    }

    public Loan( String ssn, int creditScore, double amount, int duration )
    {
        this.ssn = ssn;
        this.creditScore = creditScore;
        this.amount = amount;
        this.duration = duration;
    }

    public Loan( String commaSeparatedValues )
    {
        String[] values = commaSeparatedValues.split( "," );

        this.ssn = values[ 0 ];
        this.amount = Double.parseDouble( values[ 1 ] );
        this.duration = Integer.parseInt( values[ 2 ] );
    }

    public String getSsn()
    {
        return ssn;
    }

    public void setSsn( String ssn )
    {
        this.ssn = ssn;
    }

    public int getCreditScore()
    {
        return creditScore;
    }

    public void setCreditScore( int creditScore )
    {
        this.creditScore = creditScore;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount( double amount )
    {
        this.amount = amount;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration( int duration )
    {
        this.duration = duration;
    }

    @Override
    public String toString()
    {
        return ssn + "," + creditScore + "," + amount + "," + duration;
    }
}
