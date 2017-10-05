package dk.cphsoftdev.app.factory;

import dk.cphsoftdev.app.controller.LoanController;
import dk.cphsoftdev.app.entity.Loan;

import java.util.Date;

public class ObjectFactory
{
    /**
     * Create an instance of {@link Loan} and set credit score
     * @param ssn String
     * @param amount double
     * @param duration Date
     * @return Loan
     */
    public Loan createLoan(String ssn, double amount, Date duration)
    {
        Loan loan = new Loan( ssn, amount, duration );
        loan.setCreditScore( new LoanController().getCreditScore( loan ) );

        return loan;
    }
}
