package dk.cphsoftdev.app.controller;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import dk.cphsoftdev.app.entity.Loan;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

public class SenderController
{
    private String queueName;
    private String hostname;
    private String username;
    private ConnectionFactory factory;
    private Connection connection;
    Channel channel;


    public SenderController(String queueName, String hostname, String username)
    {
        this.queueName = queueName;
        this.hostname = hostname;
        this.username = username;

        connect();
    }


    public String sendMessage(Loan loan)
    {
        String response = "Message could not be sent!";

        try
        {
            response = basicPublish( loan );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        return response;
    }

    public boolean connect()
    {
        try
        {
            return createFactory() && newConnection() && createChannel();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        catch( TimeoutException e )
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean close()
    {
        try
        {
            channel.close();
            connection.close();

            return true;
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        catch( TimeoutException e )
        {
            e.printStackTrace();
        }
        return false;
    }

    private String basicPublish(Loan loan) throws IOException
    {
        ObjectParser parser = new ObjectParser();
        channel.queueDeclare( queueName, false, false, false, null );
        channel.basicPublish( "", queueName, null, parser.objectToJsonString( loan ).getBytes() );

        return " [x] Sent '" + loan + "'";
    }

    private boolean createFactory()
    {
        if( factory == null )
            factory = new ConnectionFactory();

        // datdb.cphbusiness.dk or localhost
        factory.setHost( hostname );

        // student or guest
        factory.setUsername( username );

        // cph or guest
        // factory.setPassword( "cph" );

        // 5672 if local else 15672
        // factory.setPort( 15672 );

        return factory.getHost().equals( hostname );
    }

    private boolean newConnection() throws IOException, TimeoutException
    {
        if( connection == null )
            connection = factory.newConnection();

        return connection.isOpen();
    }

    private boolean createChannel() throws IOException, TimeoutException
    {
        if( channel == null )
            channel = connection.createChannel();

        return channel.isOpen();
    }
}