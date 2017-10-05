package dk.cphsoftdev.app.controller;

import dk.cphsoftdev.app.creditbureau.CreditScoreService_Service;
import dk.cphsoftdev.app.entity.Loan;

public class LoanController
{
    /**
     * Get credit score by given SSN number.
     * @param loan Loan
     * @return int
     */
    public int getCreditScore(Loan loan)
    {
        CreditScoreService_Service service = new CreditScoreService_Service();
        return service.getCreditScoreServicePort().creditScore( loan.getSsn() );
    }

    /**
     * Send the credit score from Credit Bureau to Get Banks App.
     *
     * @return boolean
     */
    public boolean sendCreditScore()
    {
        return false;
    }
}
