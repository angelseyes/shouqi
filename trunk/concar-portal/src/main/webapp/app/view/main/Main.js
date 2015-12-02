/**
 * This example demonstrates the treelist widget.
 */
Ext.define('BAICClassicApp.view.main.Main', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',
        'BAICClassicApp.view.main.MainController',
        'BAICClassicApp.view.main.MainModel',
        // 'BAICClassicApp.view.module.fullmapdisplay.View',
        // 'BAICClassicApp.view.module.inventorymgmt.obdmgmt.View',
        // 'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.View',
        // 'BAICClassicApp.view.module.monitor.monitorveh.View',
        // 'BAICClassicApp.view.main.List',
    ],
    xtype: 'app-main',

    controller: 'main',
    viewModel: 'main',
    plugins: 'viewport',
    // width: 500,
    // height: 450,
    title: '——公车封存监控管理平台',
    iconCls: 'fa fa-2x',
    layout: 'border',
    header: {
        ui: 'main-header',
        height: 68,
        margin: '0 0 5 0',
        items: [{
            xtype: 'box',
            reference: 'chinamobilelogo',
            autoEl: 'div',
            cls: 'chinamobilelogo'
        }, {
            xtype: 'box',
            reference: 'chinamobilelogofill',
            autoEl: 'div',
            cls: 'chinamobilelogofill'
        },]
    },

    items: [{
        region: 'west',
        title: '功能列表',
        collapsible: true,
        width: 140,
        split: true,
        reference: 'treelistContainer',
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        border: true,
        scrollable: 'y',
        items: [{
            xtype: 'treelist',
            ui: 'nav-normal',
            expanderOnly: false,
            reference: 'treelist',
            bind: '{navItems}',
            // ui: 'nav',
            listeners: {
                itemclick: 'onTreeItemClick' //注意treelist的listener默认在这里注册。
            },
        }],

    }, {
        region: 'center',
        xtype: 'tabpanel',
        itemId: 'center-tabpanel',
        reference: 'centertabpanel',
        bodyPadding: '3 0 0 0',
        defaults: {
            frame: true
        },
        split: false,
        items: [],
        listeners: {
            tabchange: 'onTabChange'
        }
    }],

    initComponent: function() {
        console.log('Inside initComponent of Main');
        this.callParent();
    },

    listeners: {
        afterrender: 'onAfterrender'
    },
});
