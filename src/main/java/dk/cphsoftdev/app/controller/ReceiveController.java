package dk.cphsoftdev.app.controller;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveController
{
    private String queueName;
    private String hostname;
    private String username;
    private ConnectionFactory factory;
    private Connection connection;
    Channel channel;

    public ReceiveController(String queueName, String hostname, String username)
    {
        this.queueName = queueName;
        this.hostname = hostname;
        this.username = username;

        connect();
    }

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

    private void handleDelivery() throws IOException
    {
        channel.queueDeclare( queueName, false, false, false, null );
        System.out.println( " [*] Waiting for messages. To exit press CTRL+C" );

        Consumer consumer = new DefaultConsumer( channel )
        {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException
            {
                String message = new String( body, "UTF-8" );
                System.out.println( " [x] Received '" + message + "'" );
            }
        };

        channel.basicConsume( queueName, true, consumer );
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
