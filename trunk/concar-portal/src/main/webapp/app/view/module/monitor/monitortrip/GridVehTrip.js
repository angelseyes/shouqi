Ext.define('BAICClassicApp.view.module.monitor.monitortrip.GridVehTrip', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        // 'BAICClassicApp.view.module.monitor.monitortrip.GridToolbar'
    ],
    reference: 'gridvehtrip',
    title: '<i class="fa fa-bar-chart-o" style="color:#da0d0d;"></i> 车辆行程记录',
    // selType: 'checkboxmodel',//不允许多选
    bind: {
        store: '{myVehTripStore}'
    },
    listeners: {
        // itemdblclick: 'onItemDoubleClick',
        selectionchange: 'onSelectionChangeGridVehTrip'
    },
    stateful: true,
    columnLines: true, // 加上表格线
    // grid columns
    columns: [{
        flex: 1,
        text: '发生时间',
        dataIndex: 'traceDate'
    }, {
        dataIndex: 'traceId',
        text: 'traceId',
        hidden: true,
        flex: 1
    },],
    dockedItems: [{
        xtype: 'pagingtoolbar',
        dock: 'bottom',
        bind: {
            store: '{myVehTripStore}'
        },
        displayInfo: true
    }],
    initComponent: function() {
        this.callParent();
    }
});
