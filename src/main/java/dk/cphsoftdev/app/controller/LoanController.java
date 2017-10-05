package dk.cphsoftdev.app.controller;

import dk.cphsoftdev.app.entity.Loan;

public class LoanController
{
    private Loan loan;

    /**
     * Constructor
     * @param loan Loan
     */
    public LoanController(Loan loan)
    {
        this.loan = loan;
    }

    /**
     * Get the credit score from Credit Bureau by given Loan object.
     * @return int
     */
    public int getCreditScore()
    {
        return this.loan.getCreditScore(); // Skal rettes
    }

    /**
     * Send the credit score from Credit Bureau to Get Banks App.
     * @return boolean
     */
    public boolean sendCreditScore()
    {
        return false;
    }
}
