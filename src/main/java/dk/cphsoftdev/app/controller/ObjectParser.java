package dk.cphsoftdev.app.controller;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectParser
{
    public ObjectParser()
    {
    }

    public String objectToJsonString(Object object)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString( object );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        return null;
    }

    public <T> T jsonToObject(String json, Class<T> returnMe)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue( json, returnMe );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        return null;
    }
}
