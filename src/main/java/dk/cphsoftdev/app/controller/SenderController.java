package dk.cphsoftdev.app.controller;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import dk.cphsoftdev.app.entity.Loan;

import java.io.IOException;
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


    /**
     * Send message
     *
     * @param loan Loan
     */
    public void sendMessage(Loan loan)
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

        System.out.println( response );
    }

    /**
     * Create Factory, connection and channel
     *
     * @return boolean
     */
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

    /**
     * Close channel and connection
     *
     * @return boolean
     */
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

    /**
     * Declare queue and publish message to channel
     *
     * @param loan Loan
     * @return String
     * @throws IOException
     */
    private String basicPublish(Loan loan) throws IOException
    {
        MessageController messageController = new MessageController( loan );
        channel.queueDeclare( queueName, false, false, false, null );
        channel.basicPublish( "", queueName, null, messageController.asByteArray() );

        return "[Sent] --> '" + messageController.asString() + "'";
    }

    /**
     * Create and set Factory properties
     *
     * @return boolean
     */
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

    /**
     * Create Connection
     *
     * @return boolean
     * @throws IOException
     * @throws TimeoutException
     */
    private boolean newConnection() throws IOException, TimeoutException
    {
        if( connection == null )
            connection = factory.newConnection();

        return connection.isOpen();
    }

    /**
     * Create Channel
     *
     * @return boolean
     * @throws IOException
     * @throws TimeoutException
     */
    private boolean createChannel() throws IOException, TimeoutException
    {
        if( channel == null )
            channel = connection.createChannel();

        return channel.isOpen();
    }
}
