package dk.cphsoftdev.app;

import dk.cphsoftdev.app.controller.ReceiveController;
import dk.cphsoftdev.app.controller.SenderController;
import dk.cphsoftdev.app.entity.Loan;
import dk.cphsoftdev.app.factory.ObjectFactory;

import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        Loan loan = new ObjectFactory().createLoan( "120985-2354", 250000, new Date() );
        System.out.println( "Credit Score: " + loan.getCreditScore() );

        SenderController sender = new SenderController( "que578", "datdb.cphbusiness.dk", "guest" );
        ReceiveController receive = new ReceiveController( "que578", "datdb.cphbusiness.dk", "guest" );

        System.out.println( sender.sendMessage( loan.toString() ) );
        receive.printMessages();
    }
}
