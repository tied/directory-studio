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

package org.apache.directory.ldapstudio.schemas.controller;


import org.apache.directory.ldapstudio.schemas.Activator;
import org.apache.directory.ldapstudio.schemas.view.preferences.GeneralPreferencePage;
import org.apache.directory.ldapstudio.schemas.view.preferences.HierarchyViewPreferencePage;
import org.apache.directory.ldapstudio.schemas.view.preferences.SchemaPreferencePage;
import org.apache.directory.ldapstudio.schemas.view.preferences.SchemasViewPreferencePage;
import org.apache.directory.ldapstudio.schemas.view.viewers.HierarchyViewSortDialog;
import org.apache.directory.ldapstudio.schemas.view.viewers.SchemasViewSortDialog;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;


public class PreferenceInitializer extends AbstractPreferenceInitializer
{

    public PreferenceInitializer()
    {
        super();
    }


    @Override
    public void initializeDefaultPreferences()
    {
        IEclipsePreferences defaults = new DefaultScope().getNode( Activator.PLUGIN_ID );
        defaults.put( GeneralPreferencePage.COMPANY_OID, "1.2.3.4.5.6" ); //$NON-NLS-1$
        defaults.putBoolean( GeneralPreferencePage.AUTO_OID, true );
        defaults.put( SchemaPreferencePage.DEFAULT_DIRECTORY, System.getProperty( "user.home" ) ); //$NON-NLS-1$
        defaults.putBoolean( SchemaPreferencePage.SAVE_WORKSPACE, true );
        defaults.putBoolean( SchemaPreferencePage.SPECIFIC_CORE, false );
        defaults.put( SchemaPreferencePage.SPECIFIC_CORE_DIRECTORY, System.getProperty( "user.home" ) ); //$NON-NLS-1$

        IPreferenceStore store = Activator.getDefault().getPreferenceStore();

        // Hierarchy View Preference Page
        store.setDefault( HierarchyViewPreferencePage.PREFS_HIERARCHY_VIEW_LABEL,
            HierarchyViewPreferencePage.PREFS_HIERARCHY_VIEW_LABEL_ALL_ALIASES );
        store.setDefault( HierarchyViewPreferencePage.PREFS_HIERARCHY_VIEW_ABBREVIATE, true );
        store.setDefault( HierarchyViewPreferencePage.PREFS_HIERARCHY_VIEW_ABBREVIATE_MAX_LENGTH, "50" );
        store.setDefault( HierarchyViewPreferencePage.PREFS_HIERARCHY_VIEW_SECONDARY_LABEL_DISPLAY, true );
        store.setDefault( HierarchyViewPreferencePage.PREFS_HIERARCHY_VIEW_SECONDARY_LABEL,
            HierarchyViewPreferencePage.PREFS_HIERARCHY_VIEW_LABEL_OID );
        store.setDefault( HierarchyViewPreferencePage.PREFS_HIERARCHY_VIEW_SECONDARY_LABEL_ABBREVIATE, false );
        store.setDefault( HierarchyViewPreferencePage.PREFS_HIERARCHY_VIEW_SECONDARY_LABEL_ABBREVIATE_MAX_LENGTH, "50" );

        // Hierarchy View Sorting
        store.setDefault( HierarchyViewSortDialog.PREFS_HIERARCHY_VIEW_GROUPING,
            HierarchyViewSortDialog.PREFS_HIERARCHY_VIEW_GROUPING_ATFIRST );
        store.setDefault( HierarchyViewSortDialog.PREFS_HIERARCHY_VIEW_SORTING_BY,
            HierarchyViewSortDialog.PREFS_HIERARCHY_VIEW_SORTING_BY_FIRSTNAME );
        store.setDefault( HierarchyViewSortDialog.PREFS_HIERARCHY_VIEW_SORTING_ORDER,
            HierarchyViewSortDialog.PREFS_HIERARCHY_VIEW_SORTING_ORDER_ASCENDING );

        // Schemas View Preference Page
        store.setDefault( SchemasViewPreferencePage.PREFS_SCHEMAS_VIEW_LABEL,
            SchemasViewPreferencePage.PREFS_SCHEMAS_VIEW_LABEL_ALL_ALIASES );
        store.setDefault( SchemasViewPreferencePage.PREFS_SCHEMAS_VIEW_ABBREVIATE, true );
        store.setDefault( SchemasViewPreferencePage.PREFS_SCHEMAS_VIEW_ABBREVIATE_MAX_LENGTH, "50" );

        // Schemas View Sorting
        store.setDefault( SchemasViewSortDialog.PREFS_SCHEMAS_VIEW_GROUPING,
            SchemasViewSortDialog.PREFS_SCHEMAS_VIEW_GROUPING_FOLDERS );
        store.setDefault( SchemasViewSortDialog.PREFS_SCHEMAS_VIEW_SORTING_BY,
            SchemasViewSortDialog.PREFS_SCHEMAS_VIEW_SORTING_BY_FIRSTNAME );
        store.setDefault( SchemasViewSortDialog.PREFS_SCHEMAS_VIEW_SORTING_ORDER,
            SchemasViewSortDialog.PREFS_SCHEMAS_VIEW_SORTING_ORDER_ASCENDING );
    }
}
