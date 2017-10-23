package dk.cphsoftdev.app.factory;

import dk.cphsoftdev.app.controller.LoanController;
import dk.cphsoftdev.app.controller.Validator;
import dk.cphsoftdev.app.entity.Loan;

public class ObjectFactory
{
    /**
     * Create an instance of {@link Loan} and set credit score
     *
     * @param ssn      String
     * @param amount   double
     * @param duration int
     * @return Loan
     */
    public Loan createLoan( String ssn, double amount, int duration )
    {
        return new Loan( ssn, new LoanController().getCreditScore( ssn ), amount, duration );
    }

    /**
     * Create an instance of {@link Loan} and set credit score
     * @param commaSeparatedValues String
     * @return Loan
     */
    public Loan createLoan( String commaSeparatedValues )
    {
        if( new Validator().isValid( commaSeparatedValues ) )
        {
            Loan loan = new Loan( commaSeparatedValues );
            loan.setCreditScore( new LoanController().getCreditScore( loan.getSsn() ) );
            return loan;
        }

        return null;
    }
}
