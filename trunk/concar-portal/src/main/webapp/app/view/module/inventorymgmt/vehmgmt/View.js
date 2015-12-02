/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.inventorymgmt.vehmgmt.View', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.VBox',
        'Ext.layout.container.Fit',
        'Ext.window.MessageBox',
        'Ext.grid.plugin.RowEditing',
        'BAICClassicApp.view.module.inventorymgmt.vehmgmt.ViewController',
        'BAICClassicApp.view.module.inventorymgmt.vehmgmt.ViewModel',
        'BAICClassicApp.view.module.inventorymgmt.vehmgmt.SearchForm',
        'BAICClassicApp.view.module.inventorymgmt.vehmgmt.GridSummary',
        'BAICClassicApp.view.module.inventorymgmt.vehmgmt.GridVeh',
    ],
    controller: {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.vehmgmt.ViewController'
    },
    viewModel: {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.vehmgmt.ViewModel'
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
        xclass: 'BAICClassicApp.view.module.inventorymgmt.vehmgmt.SearchForm',
    }, {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.vehmgmt.GridSummary',
    }, {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.vehmgmt.GridVeh',
        margin: '0 0 0 0',
        flex: 1,
    }],
    initComponent: function() {
        this.callParent();
    }
});
