/**
 * This class is the view model for the Template view of the application.
 */
Ext.define('BAICClassicApp.view.module.monitor.monitorveh.ViewModel', {
    extend: 'Ext.app.ViewModel',
    data: {
        selectedRow: null,
        userNameReadOnly: false,
        HideResetPasswordBtn: false,
    },
    stores: {
        myGridStore: {
            model: 'BAICClassicApp.model.MonitorVeh',
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
