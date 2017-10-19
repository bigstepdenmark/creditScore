package dk.cphsoftdev.app.controller;

import dk.cphsoftdev.app.creditbureau.CreditScoreService_Service;

public class LoanController
{
    /**
     * Get credit score by given SSN number.
     *
     * @param ssn String
     * @return int
     */
    public int getCreditScore(String ssn)
    {
        CreditScoreService_Service service = new CreditScoreService_Service();
        return service.getCreditScoreServicePort().creditScore( ssn );
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
