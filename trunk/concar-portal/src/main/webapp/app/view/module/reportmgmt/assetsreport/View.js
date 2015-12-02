/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.reportmgmt.assetsreport.View', {
    extend: 'Ext.container.Container',
    requires: [
        'Ext.layout.container.VBox',
        'Ext.layout.container.Fit',
        'Ext.window.MessageBox',
        'Ext.grid.plugin.RowEditing',
        'BAICClassicApp.view.module.reportmgmt.assetsreport.ViewController',
        'BAICClassicApp.view.module.reportmgmt.assetsreport.ViewModel',
        'BAICClassicApp.view.module.reportmgmt.assetsreport.SearchForm',
        'BAICClassicApp.view.module.reportmgmt.assetsreport.Grid',
    ],
    controller: {
        xclass: 'BAICClassicApp.view.module.reportmgmt.assetsreport.ViewController'
    },
    viewModel: {
        xclass: 'BAICClassicApp.view.module.reportmgmt.assetsreport.ViewModel'
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
        flex: 1,
    },
    items: [{
        // xclass: 'BAICClassicApp.view.module.reportmgmt.assetsreport.SearchForm',
        xtype: 'container',
        layout: {
            type: 'hbox',
            pack: 'start',
            align: 'stretch'
        },
        margin: '0 0 3 0',
        defaults: {
            flex: 1,
        },
        items: [{
            xclass: 'BAICClassicApp.view.module.reportmgmt.assetsreport.ChartPieByVehType',
            margin: '0 3 0 0',
        }, {
            xclass: 'BAICClassicApp.view.module.reportmgmt.assetsreport.ChartPieByInstitution',
        }]

    }, {
        // xclass: 'BAICClassicApp.view.module.reportmgmt.assetsreport.SearchForm',
        xtype: 'container',
        layout: {
            type: 'hbox',
            pack: 'start',
            align: 'stretch'
        },
        defaults: {
            flex: 1,
        },
        items: [{
            xclass: 'BAICClassicApp.view.module.reportmgmt.assetsreport.ChartPieByMileage',
            margin: '0 3 0 0',
        }, {
            xclass: 'BAICClassicApp.view.module.reportmgmt.assetsreport.Chart3DColumnByVehStatus',
        }]

    }, ],
    initComponent: function() {
        this.callParent();
    }
});
