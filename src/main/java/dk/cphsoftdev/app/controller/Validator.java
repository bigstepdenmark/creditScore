package dk.cphsoftdev.app.controller;

public class Validator
{
    public boolean isValid( String request )
    {
        String[] values = request.split( "," );
        boolean isLengthValid = values.length == 3;
        boolean isValuesValid = isSSN( values[ 0 ] ) &&
                isAmount( values[ 1 ] ) &&
                isDuration( values[ 2 ] );

        return isLengthValid && isValuesValid;
    }

    private boolean isSSN( String ssn )
    {
        return isNumber( ssn ) && ssn.length() == 10;
    }

    private boolean isAmount( String amount )
    {
        return isDouble( amount );
    }

    private boolean isDuration( String duration )
    {
        return isNumber( duration );
    }

    private boolean isDouble( String value )
    {
        try
        {
            double parsedValue = Double.parseDouble( value );
            return parsedValue > 0;
        }
        catch( NumberFormatException e )
        {
            return false;
        }
    }

    private boolean isNumber( String value )
    {
        try
        {
            int parsedValue = Integer.parseInt( value );
            return parsedValue > 0;
        }
        catch( NumberFormatException e )
        {
            return false;
        }
    }
}
