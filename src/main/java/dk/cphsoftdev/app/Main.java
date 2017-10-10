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
        SenderController sender = new SenderController( "que55", "datdb.cphbusiness.dk", "guest" );
        ReceiveController receiver = new ReceiveController( "que55", "datdb.cphbusiness.dk", "guest" );

        Loan loan = new ObjectFactory().createLoan( "120985-2354", 250000, new Date() );
        sender.sendMessage( loan );
        receiver.printMessages();
    }
}
