/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.authoritymgmt.usermgmt.View', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.VBox',
        'Ext.layout.container.Fit',
        'Ext.window.MessageBox',
        'Ext.grid.plugin.RowEditing',
        'BAICClassicApp.view.module.authoritymgmt.usermgmt.ViewController',
        'BAICClassicApp.view.module.authoritymgmt.usermgmt.ViewModel',
        'BAICClassicApp.view.module.authoritymgmt.usermgmt.SearchForm',
        'BAICClassicApp.view.module.authoritymgmt.usermgmt.Grid',
    ],
    controller: {
        xclass: 'BAICClassicApp.view.module.authoritymgmt.usermgmt.ViewController'
    },
    viewModel: {
        xclass: 'BAICClassicApp.view.module.authoritymgmt.usermgmt.ViewModel'
    },
    autoScroll: true,
    layout: {
        type: 'vbox',
        pack: 'start',
        align: 'stretch'
    },
    bodyPadding: 0,
    defaults: {
        frame: true,
        collapsible: true,
        margin: '0 0 3 0'
    },
    items: [{
        xclass: 'BAICClassicApp.view.module.authoritymgmt.usermgmt.SearchForm',
    }, {
        xclass: 'BAICClassicApp.view.module.authoritymgmt.usermgmt.Grid',
        margin: '0 0 0 0',
        flex: 1,
    }],
    initComponent: function() {
        this.callParent();
    }
});
