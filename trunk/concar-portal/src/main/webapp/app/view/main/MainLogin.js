/**
 * This example demonstrates the treelist widget.
 */
Ext.define('BAICClassicApp.view.main.MainLogin', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.plugin.Viewport',
        // 'Ext.window.MessageBox',
    ],
    plugins: 'viewport',
    // width: 500,
    // height: 450,
    xtype: 'app-main-login',
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
        }, ]
    },

    items: [],

    initComponent: function() {
        console.log('Inside initComponent of MainLogin');
        this.callParent();
    },

});
