package dk.cphsoftdev.app.controller;

import dk.cphsoftdev.app.creditbureau.CreditScoreService_Service;

public class CreditScore
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
        String ssnDate = ssn.substring( 0, 6 );
        String ssnCode = ssn.substring( 6 );
        return service.getCreditScoreServicePort().creditScore( ssnDate + "-" + ssnCode );
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
