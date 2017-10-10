package dk.cphsoftdev.app.controller;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class MessageController
{
    private String json;
    private Object object;
    private ObjectMapper mapper;

    /**
     * Parse object
     *
     * @param object Object
     */
    public MessageController(Object object)
    {
        this.object = object;
        this.mapper = new ObjectMapper();
    }

    /**
     * Parse String
     *
     * @param json String
     */
    public MessageController(String json)
    {
        this.json = json;
        this.mapper = new ObjectMapper();
    }

    /**
     * Parse to JSON String
     *
     * @return String
     */
    public String asString()
    {
        try
        {
            json = mapper.writeValueAsString( object );
            return json;
        }
        catch( IOException e )
        {
            return null;
        }
    }

    /**
     * Parse to byte array
     *
     * @return byte[]
     */
    public byte[] asByteArray()
    {
        try
        {
            return mapper.writeValueAsBytes( object );
        }
        catch( IOException e )
        {
            return null;
        }
    }

    /**
     * Get attribute from JSON String
     *
     * @param name String
     * @return JsonNode
     */
    public JsonNode getAttribute(String name)
    {
        try
        {
            return mapper.readTree( json ).get( name );
        }
        catch( IOException e )
        {
            return null;
        }
    }
}
