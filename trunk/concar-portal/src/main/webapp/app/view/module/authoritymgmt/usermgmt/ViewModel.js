/**
 * This class is the view model for the Template view of the application.
 */
Ext.define('BAICClassicApp.view.module.authoritymgmt.usermgmt.ViewModel', {
    extend: 'Ext.app.ViewModel',
    data: {
        selectedRow: null,
        userNameReadOnly: false,
        HideResetPasswordBtn: false,
    },
    stores: {
        myGridStore: {
            model: 'BAICClassicApp.model.User',
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
    }
});
