/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.reportmgmt.analysisreport.View', {
    extend: 'Ext.container.Container',
    requires: [
        'Ext.layout.container.VBox',
        'Ext.layout.container.Fit',
        'Ext.window.MessageBox',
        'Ext.grid.plugin.RowEditing',
        'BAICClassicApp.view.module.reportmgmt.analysisreport.ViewController',
        'BAICClassicApp.view.module.reportmgmt.analysisreport.ViewModel',
        'BAICClassicApp.view.module.reportmgmt.analysisreport.SearchForm',
        'BAICClassicApp.view.module.reportmgmt.analysisreport.Grid',
    ],
    controller: {
        xclass: 'BAICClassicApp.view.module.reportmgmt.analysisreport.ViewController'
    },
    viewModel: {
        xclass: 'BAICClassicApp.view.module.reportmgmt.analysisreport.ViewModel'
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
    },
    items: [{
        xclass: 'BAICClassicApp.view.module.reportmgmt.analysisreport.SearchForm',
    }, {
        // xclass: 'BAICClassicApp.view.module.reportmgmt.analysisreport.SearchForm',
        xtype: 'container',
        layout: {
            type: 'hbox',
            pack: 'start',
            align: 'stretch'
        },
        flex: 1,//将searchform下面的部分填满
        defaults: {
            flex: 1,
        },
        items: [{
            xclass: 'BAICClassicApp.view.module.reportmgmt.analysisreport.ChartLineByDTC',
            margin: '0 3 0 0',
        }, {
            xclass: 'BAICClassicApp.view.module.reportmgmt.analysisreport.ChartLineByActivity',
        }]

    }, ],
    initComponent: function() {
        this.callParent();
    }
});
