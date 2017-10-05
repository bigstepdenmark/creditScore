package dk.cphsoftdev.app;

import dk.cphsoftdev.app.controller.LoanController;
import dk.cphsoftdev.app.creditbureau.CreditScore;
import dk.cphsoftdev.app.creditbureau.CreditScoreService_Service;
import dk.cphsoftdev.app.creditbureau.ObjectFactory;
import dk.cphsoftdev.app.entity.Loan;

import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        Loan loan = new Loan( "1209852233", 300, 245000.00, new Date() );
        LoanController loanController = new LoanController( loan );
        int score = loanController.getCreditScore();

//        CreditScore creditScore = new CreditScore();
//        creditScore.setSsn( "120985-2625" );
        ObjectFactory of = new ObjectFactory();
        of.createCreditScore().setSsn( "120985-2543" );
        System.out.println( of.createCreditScoreResponse().getReturn() );
    }
}
