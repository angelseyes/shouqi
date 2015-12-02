/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.inventorymgmt.obdmgmt.View', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.VBox',
        'Ext.layout.container.Fit',
        'Ext.window.MessageBox',
        'Ext.grid.plugin.RowEditing',
        'BAICClassicApp.view.module.inventorymgmt.obdmgmt.ViewController',
        'BAICClassicApp.view.module.inventorymgmt.obdmgmt.ViewModel',
        'BAICClassicApp.view.module.inventorymgmt.obdmgmt.SearchForm',
        'BAICClassicApp.view.module.inventorymgmt.obdmgmt.GridSummary',
        'BAICClassicApp.view.module.inventorymgmt.obdmgmt.GridObd',
    ],
    controller: {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.obdmgmt.ViewController'
    },
    viewModel: {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.obdmgmt.ViewModel'
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
        xclass: 'BAICClassicApp.view.module.inventorymgmt.obdmgmt.SearchForm',
    }, {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.obdmgmt.GridSummary',
    }, {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.obdmgmt.GridObd',
        margin: '0 0 0 0',
        flex: 1,
    }],
    initComponent: function() {
        this.callParent();
    }
});
