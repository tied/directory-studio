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

package org.apache.directory.studio.aciitemeditor.valueeditors;


import java.util.Arrays;
import java.util.Collection;

import org.apache.directory.studio.aciitemeditor.Activator;
import org.apache.directory.studio.connection.ui.widgets.BaseWidgetUtils;
import org.apache.directory.studio.ldapbrowser.common.widgets.ListContentProposalProvider;
import org.apache.directory.studio.ldapbrowser.core.model.schema.Schema;
import org.apache.directory.studio.ldapbrowser.core.model.schema.SchemaUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.DecoratedField;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.IControlCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * This class provides a dialog to enter or select an attribute type.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$, $Date$
 */
public class AttributeTypeDialog extends Dialog
{

    /** The schema. */
    private Schema schema;

    /** The initial value. */
    private String initialValue;

    /** The attribute type combo field. */
    private DecoratedField attributeTypeComboField;

    /** The attribute type combo. */
    private Combo attributeTypeCombo;

    /** The attribute type content proposal adapter */
    private ContentProposalAdapter attributeTypeCPA;

    /** The return value. */
    private String returnValue;


    /**
     * Creates a new instance of AttributeTypeDialog.
     * 
     * @param parentShell the parent shell
     * @param schema the schema
     * @param initialValue the initial value
     */
    public AttributeTypeDialog( Shell parentShell, Schema schema, String initialValue )
    {
        super( parentShell );
        super.setShellStyle( super.getShellStyle() | SWT.RESIZE );
        this.initialValue = initialValue;
        this.schema = schema;
        this.returnValue = null;
    }


    /**
     * {@inheritDoc}
     */
    protected void configureShell( Shell shell )
    {
        super.configureShell( shell );
        shell.setText( Messages.getString("AttributeTypeDialog.title") ); //$NON-NLS-1$
        shell.setImage( Activator.getDefault().getImage( Messages.getString("AttributeTypeDialog.icon") ) ); //$NON-NLS-1$
    }


    /**
     * {@inheritDoc}
     */
    protected void createButtonsForButtonBar( Composite parent )
    {
        super.createButtonsForButtonBar( parent );
    }


    /**
     * {@inheritDoc}
     */
    protected void okPressed()
    {
        returnValue = attributeTypeCombo.getText();
        super.okPressed();
    }


    /**
     * {@inheritDoc}
     */
    protected Control createDialogArea( Composite parent )
    {
        // create composite
        Composite composite = ( Composite ) super.createDialogArea( parent );
        GridData gd = new GridData( GridData.FILL_BOTH );
        gd.widthHint = convertHorizontalDLUsToPixels( IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH );
        composite.setLayoutData( gd );

        // combo widget
        Collection<String> names = SchemaUtils.getNames( schema.getAttributeTypeDescriptions() );
        String[] allAtNames = names.toArray( new String[names.size()] );
        Arrays.sort( allAtNames );

        final FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(
            FieldDecorationRegistry.DEC_CONTENT_PROPOSAL );
        attributeTypeComboField = new DecoratedField( composite, SWT.NONE, new IControlCreator()
        {
            public Control createControl( Composite parent, int style )
            {
                Combo combo = BaseWidgetUtils.createCombo( parent, new String[0], -1, 1 );
                combo.setVisibleItemCount( 20 );
                return combo;
            }
        } );
        attributeTypeComboField.addFieldDecoration( fieldDecoration, SWT.TOP | SWT.LEFT, true );
        attributeTypeComboField.getLayoutControl().setLayoutData( new GridData( SWT.FILL, SWT.CENTER, true, false ) );
        attributeTypeCombo = ( Combo ) attributeTypeComboField.getControl();
        attributeTypeCombo.setItems( allAtNames );
        attributeTypeCombo.setText( initialValue );

        // content proposal adapter
        attributeTypeCPA = new ContentProposalAdapter( attributeTypeCombo, new ComboContentAdapter(),
            new ListContentProposalProvider( attributeTypeCombo.getItems() ), null, null );
        attributeTypeCPA.setFilterStyle( ContentProposalAdapter.FILTER_NONE );
        attributeTypeCPA.setProposalAcceptanceStyle( ContentProposalAdapter.PROPOSAL_REPLACE );

        applyDialogFont( composite );
        return composite;
    }


    /**
     * Gets the attribute type.
     * 
     * @return the attribute type, null if canceled
     */
    public String getAttributeType()
    {
        return returnValue;
    }
}
