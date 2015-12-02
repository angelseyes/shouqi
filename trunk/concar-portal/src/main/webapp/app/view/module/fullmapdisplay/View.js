/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.fullmapdisplay.View', {
    extend: 'Ext.container.Container',
    requires: [
        'Ext.layout.container.VBox',
        // 'Ext.layout.container.Border',
        'Ext.layout.container.Fit',
        'Ext.grid.plugin.RowEditing',
        'BAICClassicApp.view.module.fullmapdisplay.ViewController',
        'BAICClassicApp.view.module.fullmapdisplay.ViewModel',
        'BAICClassicApp.view.module.fullmapdisplay.GridInstitution',
        'BAICClassicApp.view.module.fullmapdisplay.GridVeh',
        'BAICClassicApp.view.module.fullmapdisplay.PanelTrackingMap',
    ],
    controller: {
        xclass: 'BAICClassicApp.view.module.fullmapdisplay.ViewController'
    },
    viewModel: {
        xclass: 'BAICClassicApp.view.module.fullmapdisplay.ViewModel'
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
            xclass: 'BAICClassicApp.view.module.fullmapdisplay.GridInstitution',
        }, {
            xclass: 'BAICClassicApp.view.module.fullmapdisplay.GridVeh',
            margin: '0 0 0 0',
        }],
    }, {
        xclass: 'BAICClassicApp.view.module.fullmapdisplay.PanelTrackingMap',
        flex: 1,
        split: true,
    }, ],
    initComponent: function() {
        this.callParent();
    }
});
