package dk.cphsoftdev.app.entity;

import java.util.Date;

public class Loan
{
    private String ssn;
    private int creditScore;
    private double amount;
    private Date duration;

    public Loan()
    {
    }

    public Loan(String ssn, double amount, Date duration)
    {
        this.ssn = ssn;
        this.amount = amount;
        this.duration = duration;
    }

    public String getSsn()
    {
        return ssn;
    }

    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }

    public int getCreditScore()
    {
        return creditScore;
    }

    public void setCreditScore(int creditScore)
    {
        this.creditScore = creditScore;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public Date getDuration()
    {
        return duration;
    }

    public void setDuration(Date duration)
    {
        this.duration = duration;
    }

    @Override
    public String toString()
    {
        return "Loan{" +
                "ssn='" + ssn + '\'' +
                ", creditScore=" + creditScore +
                ", amount=" + amount +
                ", duration=" + duration +
                '}';
    }
}