package dk.cphsoftdev.app.controller;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver
{
    private String queueName;
    private String hostname;
    private String username;
    private ConnectionFactory factory;
    private Connection connection;
    Channel channel;

    public Receiver( String queueName, String hostname, String username )
    {
        this.queueName = queueName;
        this.hostname = hostname;
        this.username = username;

        connect();
    }

    public Receiver( String queueName )
    {
        this.queueName = queueName;
        this.hostname = "datdb.cphbusiness.dk";
        this.username = "guest";

        connect();
    }

    /**
     * Print received messages
     */
    public void printMessages()
    {
        try
        {
            handleDelivery();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    public String getMessage()
    {
        try
        {
            return handleDelivery();
        }
        catch( IOException e )
        {
            return null;
        }
    }

    public boolean isReady()
    {
        return getMessage().length() > 0;
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
     * Handle delivered messages
     *
     * @return String
     * @throws IOException
     */
    private String handleDelivery() throws IOException
    {
        StringBuilder builder = new StringBuilder();

        channel.queueDeclare( queueName, false, false, false, null );
        System.out.println( "\nWaiting for messages. To exit press CTRL+C" );
        System.out.println( "====================================================" );

        Consumer consumer = new DefaultConsumer( channel )
        {
            @Override
            public void handleDelivery( String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body ) throws IOException
            {
                String message = new String( body, "UTF-8" );
                System.out.println( "[Received] --> '" + message + "'" );

                builder.append( message );
            }
        };

        channel.basicConsume( queueName, true, consumer );

        return builder.toString();
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
