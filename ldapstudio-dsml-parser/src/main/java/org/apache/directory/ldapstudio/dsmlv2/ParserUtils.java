
package org.apache.directory.ldapstudio.dsmlv2;

import org.apache.directory.shared.ldap.ldif.LdifUtils;
import org.apache.directory.shared.ldap.util.Base64;
import org.xmlpull.v1.XmlPullParser;

public class ParserUtils
{
    public static final String XML_SCHEMA_URI = "http://www.w3c.org/2001/XMLSchema";
    public static final String XML_SCHEMA_INSTANCE_URI = "http://www.w3c.org/2001/XMLSchema-instance";
    public static final String BASE64BINARY = "base64Binary";

    
    /**
     * Returns the value of the attribute 'type' of the "XMLSchema-instance' namespace if it exists
     *
     * @param xpp the XPP parser to use
     * @return the value of the attribute 'type' of the "XMLSchema-instance' namespace if it exists
     */
    public static String getXsiTypeAttributeValue( XmlPullParser xpp )
    {
        String type = null;
        int nbAttributes = xpp.getAttributeCount();
        for ( int i = 0; i < nbAttributes; i++ )
        {
            // Checking if the attribute 'type' from XML Schema Instance namespace is used.
            if ( xpp.getAttributeName( i ).equals( "type" ) && xpp.getNamespace( xpp.getAttributePrefix( i ) ).equals( XML_SCHEMA_INSTANCE_URI ))
            {
                type = xpp.getAttributeValue( i );
                break;
            }
        }
        return type;
    }
    
    /**
     * Returns true if the value of the current tag is Base64Binary encoded
     *
     * @param parser the XPP parser to use
     * @param attrValue the attribute value
     * @return true if the value of the current tag is Base64BinaryEncoded, false if not
     */
    public static boolean isBase64BinaryValue( XmlPullParser parser, String attrValue )
    {
        if ( attrValue == null )
        {
            return false;
        }
        // We are looking for something that should look like that: "aNameSpace:base64Binary"
        // We split the String. The first element should be the namespace prefix and the second "base64Binary"
        String [] splitedString = attrValue.split( ":" );
        return ( splitedString.length == 2 ) 
                && ( XML_SCHEMA_URI.equals( parser.getNamespace( splitedString[0] ) ) ) 
                && ( BASE64BINARY.equals( splitedString[1] ) );
    }
    
    /**
     * Indicates if the value needs to be encoded as Base64
     *
     * @param value the value to check
     * @return true if the value needs to be encoded as Base64
     */
    public static boolean needsBase64Encoding( Object value )
    {
        if ( value instanceof byte[] )
        {
            return true;
        }
        else if ( value instanceof String )
        {
            return !LdifUtils.isLDIFSafe( (String) value );
        }
        return true;
    }
    
    /**
     * Encodes the value as a Base64 String
     *
     * @param value the value to encode
     * @return the value encoded as a Base64 String 
     */
    public static String base64Encode( Object value )
    {
        if ( value instanceof byte[] )
        {
            return new String( Base64.encode( (byte[]) value ) );
        }
        else if ( value instanceof String )
        {
            return new String( Base64.encode( (( String ) value).getBytes() ) );
        }
        
        return "";
    }
}
