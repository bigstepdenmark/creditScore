package dk.cphsoftdev.app;

import dk.cphsoftdev.app.controller.Receiver;
import dk.cphsoftdev.app.controller.Sender;
import dk.cphsoftdev.app.controller.Validator;
import dk.cphsoftdev.app.factory.Factory;

public class Main
{
    public static void main( String[] args )
    {
        if( new Validator().isValid( args[ 0 ] ) )
        {
            Sender sender = new Sender( "group3.creditscore.getbanks" );
            sender.sendMessage( new Factory().createLoan( args[ 0 ] ) );
            sender.close();
        }
        else
        {
            System.out.println( "Error! Invalid information. Please try again." );
            System.exit( 1 );
        }
    }
}
