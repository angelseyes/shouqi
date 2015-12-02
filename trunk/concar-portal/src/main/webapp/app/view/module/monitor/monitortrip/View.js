/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.monitor.monitortrip.View', {
    extend: 'Ext.container.Container',
    requires: [
        'Ext.layout.container.VBox',
        // 'Ext.layout.container.Border',
        'Ext.layout.container.Fit',
        'Ext.grid.plugin.RowEditing',
        'BAICClassicApp.view.module.monitor.monitortrip.ViewController',
        'BAICClassicApp.view.module.monitor.monitortrip.ViewModel',
        'BAICClassicApp.view.module.monitor.monitortrip.GridInstitution',
        'BAICClassicApp.view.module.monitor.monitortrip.GridVeh',
        'BAICClassicApp.view.module.monitor.monitortrip.GridVehTrip',
        'BAICClassicApp.view.module.monitor.monitortrip.PanelTrackingMap',
    ],
    controller: {
        xclass: 'BAICClassicApp.view.module.monitor.monitortrip.ViewController'
    },
    viewModel: {
        xclass: 'BAICClassicApp.view.module.monitor.monitortrip.ViewModel'
    },
    layout: {
        type: 'hbox',
        pack: 'start',
        align: 'stretch'
    },
    defaults: {
        frame: true,
    },

    items: [{
        xtype: 'container',
        flex: 1,
        layout: {
            type: 'vbox',
            pack: 'start',
            align: 'stretch'
        },
        defaults: {
            collapsible: true,
            flex: 1,
            frame: true,
            margin: '0 0 3 0'
        },
        items: [{
            xclass: 'BAICClassicApp.view.module.monitor.monitortrip.GridInstitution',
        }, {
            xclass: 'BAICClassicApp.view.module.monitor.monitortrip.GridVeh',
        }, {
            xclass: 'BAICClassicApp.view.module.monitor.monitortrip.GridVehTrip',
            margin: '0 0 0 0',
        }, ],
    }, {
        xclass: 'BAICClassicApp.view.module.monitor.monitortrip.PanelTrackingMap',
        flex: 1,
        split: true,
    }, ],
    initComponent: function() {
        this.callParent();
    }
});
