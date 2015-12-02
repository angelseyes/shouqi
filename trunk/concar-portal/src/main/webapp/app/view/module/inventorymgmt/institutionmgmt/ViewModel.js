/**
 * This class is the view model for the Template view of the application.
 */
Ext.define('BAICClassicApp.view.module.inventorymgmt.institutionmgmt.ViewModel', {
    extend: 'Ext.app.ViewModel',
    data: {
        selectedRow: null,
        userNameReadOnly: false,
        HideResetPasswordBtn: false,
    },
    stores: {
        myGridDepartmentStore: {
            model: 'BAICClassicApp.model.Institution',
            pageSize: 0,
            autoLoad: false,
            remoteSort: false,
            remoteFilter: false,
            sorters: [{
                property: 'name',
                direction: 'ASC'
            }],
            listeners: {
                // beforeload: 'onBeforeLoadVeh'
                // load: 'onLoadMyGridStore'
            }
        },
        //         myMemoryGridStore: {
        //             model: 'BAICClassicApp.model.Institution',
        //             pageSize: 10,
        //             autoLoad: false,
        //             remoteSort: false,
        //             remoteFilter: false,
        //             sorters: [{
        //                 property: 'name',
        //                 direction: 'ASC'
        //             }],
        //             listeners: {
        //                 // beforeload: 'onBeforeLoadVeh'
        //                 // load: 'onLoadMyGridStore'
        //             },
        //             proxy: {
        //                 type: 'memory',
        //                 enablePaging : true,
        //                 reader: {
        //                     type: 'json',
        //                     rootProperty: 'users'
        //                 }

        // }
        // }
    }
});
