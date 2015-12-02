/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.monitor.monitorveh.View', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.VBox',
        'Ext.layout.container.Fit',
        'Ext.window.MessageBox',
        'Ext.grid.plugin.RowEditing',
        'BAICClassicApp.view.module.monitor.monitorveh.ViewController',
        'BAICClassicApp.view.module.monitor.monitorveh.ViewModel',
        'BAICClassicApp.view.module.monitor.monitorveh.SearchForm',
        'BAICClassicApp.view.module.monitor.monitorveh.Grid',
    ],
    controller: {
        xclass: 'BAICClassicApp.view.module.monitor.monitorveh.ViewController'
    },
    viewModel: {
        xclass: 'BAICClassicApp.view.module.monitor.monitorveh.ViewModel'
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
        xclass: 'BAICClassicApp.view.module.monitor.monitorveh.SearchForm',
    }, {
        xclass: 'BAICClassicApp.view.module.monitor.monitorveh.Grid',
        margin: '0 0 0 0',
        flex: 1,
    }],
    initComponent: function() {
        this.callParent();
    }
});
