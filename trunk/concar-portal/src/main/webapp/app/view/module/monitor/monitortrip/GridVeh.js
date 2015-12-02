Ext.define('BAICClassicApp.view.module.monitor.monitortrip.GridVeh', {
    extend: 'Ext.grid.Panel',
    requires: ['Ext.toolbar.Paging', 'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging', 'Ext.grid.column.Action',
        // 'BAICClassicApp.view.module.monitor.monitortrip.GridToolbar'
    ],
    reference: 'gridVeh',
    title: '<i class="fa fa-car" style="color:#da0d0d;"></i> 车辆列表',
    // selType: 'checkboxmodel',
    bind: {
        store: '{myVehStore}'
    },
    listeners: {
        //itemdblclick: 'onItemDoubleClickVehGrid',
        selectionchange: 'onSelectionChangeVehGrid'
    },
    stateful: true,
    multiSelect: true,
    columnLines: true, // 加上表格线
    // grid columns
    columns: [{
        glyph: 0xf0ae,
        iconAlign: 'top',
        scale: 'large',
        text: '车牌',
        dataIndex: 'plateNumber',
        flex: 1,
    }, {
        glyph: 0xf007,
        iconAlign: 'top',
        scale: 'large',
        text: '车型',
        dataIndex: 'model',
        flex: 1,
    }, {
        glyph: 0xf007,
        iconAlign: 'top',
        scale: 'large',
        text: '车辆颜色',
        dataIndex: 'color',
        flex: 1,
    }, {
        dataIndex: 'imei',
        text: 'imei',
        hidden: true,
        flex: 1
    }, 
    /*{
        glyph: 0xf007,
        iconAlign: 'top',
        scale: 'large',
        text: '登记日期',
        dataIndex: 'registrationDate',
        flex: 1,
    }, {
        glyph: 0xf007,
        iconAlign: 'top',
        scale: 'large',
        text: '活跃状态',
        dataIndex: 'activeStatus',
        flex: 1,
    }, {
        glyph: 0xf007,
        iconAlign: 'top',
        scale: 'large',
        text: '定位状态',
        dataIndex: 'abnormalGPSStatus',
        flex: 1,
    }, {
        glyph: 0xf007,
        iconAlign: 'top',
        scale: 'large',
        text: '出入库状态',
        dataIndex: 'rentStatus',
        flex: 1,
    }*/],
    dockedItems: [{
        xtype: 'pagingtoolbar',
        dock: 'bottom',
        bind: {
            store: '{myVehStore}'
        },
        displayInfo: true
    }],
    initComponent: function() {
        this.callParent();
    }
});
