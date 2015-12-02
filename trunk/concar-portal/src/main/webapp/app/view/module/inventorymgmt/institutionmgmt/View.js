/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.inventorymgmt.institutionmgmt.View', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.VBox',
        'Ext.layout.container.Fit',
        'Ext.window.MessageBox',
        'Ext.grid.plugin.RowEditing',
        'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.ViewController',
        'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.ViewModel',
        'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.SearchForm',
        'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.Grid',
    ],
    controller: {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.ViewController'
    },
    viewModel: {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.ViewModel'
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
        xclass: 'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.SearchForm',
    }, {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.Grid',
        margin: '0 0 0 0',
        flex: 1,
    }],
    initComponent: function() {
        this.callParent();
    }
});
