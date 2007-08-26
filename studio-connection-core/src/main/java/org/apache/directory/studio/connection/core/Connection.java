/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */

package org.apache.directory.studio.connection.core;


import org.apache.directory.studio.connection.core.ConnectionParameter.AuthenticationMethod;
import org.apache.directory.studio.connection.core.ConnectionParameter.EncryptionMethod;
import org.apache.directory.studio.connection.core.event.ConnectionEventRegistry;
import org.apache.directory.studio.connection.core.io.jndi.JNDIConnectionWrapper;
import org.eclipse.core.runtime.IAdaptable;


/**
 * The Connection class is the central .
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$, $Date$
 */
public class Connection implements ConnectionPropertyPageProvider, IAdaptable
{

    private ConnectionParameter connectionParameter;

    private JNDIConnectionWrapper jndiConnectionWrapper;


    /**
     * Creates a new instance of Connection.
     *
     * @param connectionParameter
     */
    public Connection( ConnectionParameter connectionParameter )
    {
        this.connectionParameter = connectionParameter;
    }


    /**
     * @see java.lang.Object#clone()
     */
    public Object clone()
    {
        ConnectionParameter cp = new ConnectionParameter( getName(), getHost(), getPort(), getEncryptionMethod(),
            getAuthMethod(), getBindPrincipal(), getBindPassword(), getConnectionParameter().getExtendedProperties() );

        Connection clone = new Connection( cp );

        return clone;
    }


    /**
     * Gets the JNDI connection wrapper.
     * 
     * @return the JNDI connection wrapper
     */
    public JNDIConnectionWrapper getJNDIConnectionWrapper()
    {
        if ( jndiConnectionWrapper == null )
        {
            jndiConnectionWrapper = new JNDIConnectionWrapper( this );
        }
        return jndiConnectionWrapper;
    }


    /**
     * Gets the connection parameter.
     * 
     * @return the connection parameter
     */
    public ConnectionParameter getConnectionParameter()
    {
        return connectionParameter;
    }


    /**
     * Sets the connection parameter.
     * 
     * @param connectionParameter the connection parameter
     */
    public void setConnectionParameter( ConnectionParameter connectionParameter )
    {
        String oldName = this.connectionParameter.getName();
        
        this.connectionParameter = connectionParameter;
        
        ConnectionEventRegistry.fireConnectionUpdated( this, this );
        if(!oldName.equals( connectionParameter.getName() ))
        {
            ConnectionEventRegistry.fireConnectionRenamed( this, oldName, this );
        }
    }


    /**
     * Gets the auth method.
     * 
     * @return the auth method
     */
    public AuthenticationMethod getAuthMethod()
    {
        return connectionParameter.getAuthMethod();
    }


    /**
     * Gets the bind password.
     * 
     * @return the bind password
     */
    public String getBindPassword()
    {
        return connectionParameter.getBindPassword();
    }


    /**
     * Gets the bind principal.
     * 
     * @return the bind principal
     */
    public String getBindPrincipal()
    {
        return connectionParameter.getBindPrincipal();
    }


    /**
     * Gets the encryption method.
     * 
     * @return the encryption method
     */
    public EncryptionMethod getEncryptionMethod()
    {
        return connectionParameter.getEncryptionMethod();
    }


    /**
     * Gets the host.
     * 
     * @return the host
     */
    public String getHost()
    {
        return connectionParameter.getHost();
    }


    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName()
    {
        return connectionParameter.getName();
    }


    /**
     * Gets the port.
     * 
     * @return the port
     */
    public int getPort()
    {
        return connectionParameter.getPort();
    }


    /**
     * Sets the auth method.
     * 
     * @param authMethod the auth method
     */
    public void setAuthMethod( AuthenticationMethod authMethod )
    {
        connectionParameter.setAuthMethod( authMethod );
        ConnectionEventRegistry.fireConnectionUpdated( this, this );
    }


    /**
     * Sets the bind password.
     * 
     * @param bindPassword the bind password
     */
    public void setBindPassword( String bindPassword )
    {
        connectionParameter.setBindPassword( bindPassword );
        ConnectionEventRegistry.fireConnectionUpdated( this, this );
    }


    /**
     * Sets the bind principal.
     * 
     * @param bindPrincipal the bind principal
     */
    public void setBindPrincipal( String bindPrincipal )
    {
        connectionParameter.setBindPrincipal( bindPrincipal );
        ConnectionEventRegistry.fireConnectionUpdated( this, this );
    }


    /**
     * Sets the encryption method.
     * 
     * @param encryptionMethod the encryption method
     */
    public void setEncryptionMethod( EncryptionMethod encryptionMethod )
    {
        connectionParameter.setEncryptionMethod( encryptionMethod );
        ConnectionEventRegistry.fireConnectionUpdated( this, this );
    }


    /**
     * Sets the host.
     * 
     * @param host the host
     */
    public void setHost( String host )
    {
        connectionParameter.setHost( host );
        ConnectionEventRegistry.fireConnectionUpdated( this, this );
    }


    /**
     * Sets the name.
     * 
     * @param name the name
     */
    public void setName( String name )
    {
        String oldName = getName();
        connectionParameter.setName( name );
        ConnectionEventRegistry.fireConnectionRenamed( this, oldName, this );
    }


    /**
     * Sets the port.
     * 
     * @param port the port
     */
    public void setPort( int port )
    {
        connectionParameter.setPort( port );
        ConnectionEventRegistry.fireConnectionUpdated( this, this );
    }
    
    
    /**
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter( Class adapter )
    {
        //        if ( adapter.isAssignableFrom( ISearchPageScoreComputer.class ) )
        //        {
        //            return new LdapSearchPageScoreComputer();
        //        }
        if ( adapter == Connection.class )
        {
            return this;
        }

        return null;
    }

}
