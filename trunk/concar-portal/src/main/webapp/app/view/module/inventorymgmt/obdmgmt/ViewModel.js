/**
 * This class is the view model for the Template view of the application.
 */
Ext.define('BAICClassicApp.view.module.inventorymgmt.obdmgmt.ViewModel', {
    extend: 'Ext.app.ViewModel',
    data: {
        selectedRow: null,
        userNameReadOnly: false,
        HideResetPasswordBtn: false,
    },
    stores: {
        myGridSummaryStore: {
            model: 'BAICClassicApp.model.ObdSummary',
            pageSize: 999,
            autoLoad: true,
            remoteSort: false,
            remoteFilter: true,
            sorters: [{
                property: 'name',
                direction: 'ASC'
            }],
            listeners: {
                // beforeload: 'onBeforeLoadFuelMain'
            }
        },
        myGridObdStore: {
            model: 'BAICClassicApp.model.Obd',
            pageSize: 999,
            autoLoad: true,
            remoteSort: false,
            remoteFilter: true,
            sorters: [{
                property: 'name',
                direction: 'ASC'
            }],
            listeners: {
                // beforeload: 'onBeforeLoadVeh'
            }
        },
    }
});
