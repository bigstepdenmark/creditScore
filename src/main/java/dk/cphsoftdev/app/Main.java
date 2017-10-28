package dk.cphsoftdev.app;

import dk.cphsoftdev.app.controller.Receiver;
import dk.cphsoftdev.app.controller.Sender;
import dk.cphsoftdev.app.factory.Factory;

public class Main
{
    public static void main( String[] args )
    {
        // Test only
//        SenderController loanRequest = new SenderController( "group3.loanrequest.creditscore" );
//        String request = "1209856785,120.0,36";
//        if( new Validator().isValid( request ) )
//        {
//            Loan loan = new Loan( "1209856785,120.0,36" );
//            loan.setCreditScore( 300 );
//            loanRequest.sendMessage( loan );
//        }

        // Production
        //Receiver receiver = new Receiver( "group3.loanrequest.creditscore" );
        Sender sender = new Sender( "group3.creditscore.getbanks" );

        String dummy = "1209852398,125000.0,12";
        sender.sendMessage( new Factory().createLoan( dummy ) );

        /*if( receiver.isReady() )
        {
            receiver.printMessages();
            sender.sendMessage( new Factory().createLoan( receiver.getMessage() ) );
        }*/

        // Close connections
        //receiver.close();
        sender.close();
    }
}
