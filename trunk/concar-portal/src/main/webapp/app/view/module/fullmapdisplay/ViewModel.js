/**
 * This class is the view model for the Template view of the application.
 */
Ext.define('BAICClassicApp.view.module.fullmapdisplay.ViewModel', {
    extend: 'Ext.app.ViewModel',
    data: {
        selectedRow: null,
        userNameReadOnly: false,
        HideResetPasswordBtn: false,
    },
    stores: {
        myInstitutionStore: {
            model: 'BAICClassicApp.model.FullMapInstitution',
            pageSize: 999,
            autoLoad: true,
            remoteSort: false,
            remoteFilter: true,
            sorters: [{
                property: 'name',
                direction: 'ASC'
            }],
            listeners: {
                // 不用
                // beforeload: 'onBeforeShopStoreLoad'
            }
        },

        myVehStore: {
            model: 'BAICClassicApp.model.FullMapVeh',
            pageSize: 999,
            autoLoad: false,
            remoteSort: false,
            remoteFilter: true,
            sorters: [{
                property: 'plateNumber',
                direction: 'ASC'
            }],
            listeners: {
                beforeload: 'onBeforeMyVehStoreLoad',
                load: 'onLoadMyVehStore'//Load之后解除gridveh的mask
            }
        },
    }
});
