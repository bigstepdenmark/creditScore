package dk.cphsoftdev.app;

import dk.cphsoftdev.app.controller.ReceiveController;
import dk.cphsoftdev.app.controller.SenderController;
import dk.cphsoftdev.app.controller.Validator;
import dk.cphsoftdev.app.entity.Loan;
import dk.cphsoftdev.app.factory.ObjectFactory;

import java.util.Date;

public class Main
{
    public static void main( String[] args )
    {
        // Test only
/*        SenderController loanRequest = new SenderController( "group3.loanrequest.creditscore" );
        String request = "1209856785,120.0,36";
        if( new Validator().isValid( request ) )
        {
            Loan loan = new Loan( "1209856785,120.0,36" );
            loan.setCreditScore( 300 );
            loanRequest.sendMessage( loan );
        }*/

        // Production
        ReceiveController receiver = new ReceiveController( "group3.loanrequest.creditscore" );
        receiver.printMessages();

        SenderController sender = new SenderController( "group3.creditscore.getbanks" );
        sender.sendMessage( new ObjectFactory().createLoan( receiver.getMessage() ) );

        // Close
        receiver.close();
        sender.close();
    }
}
