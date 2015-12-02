Ext.define('BAICClassicApp.view.module.fullmapdisplay.GridVeh', {
    extend: 'Ext.grid.Panel',
    requires: ['Ext.toolbar.Paging', 'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging', 'Ext.grid.column.Action',
        // 'BAICClassicApp.view.module.fullmapdisplay.GridToolbar'
    ],
    reference: 'gridVeh',
    title: '<i class="fa fa-car" style="color:#da0d0d;"></i> 车辆列表',
    // selType: 'checkboxmodel',
    bind: {
        store: '{myVehStore}'
    },
    listeners: {
        itemdblclick: 'onItemDoubleClickVehGrid',
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
        glyph: 0xf007,
        iconAlign: 'top',
        scale: 'large',
        text: '登记日期',
        dataIndex: 'registeredDate',
        flex: 1,
    }, {
        glyph: 0xf007,
        iconAlign: 'top',
        scale: 'large',
        text: '活跃状态',
        dataIndex: 'active',
        flex: 1,
        renderer:function(v){
			if(v==1){
				return "活跃";
			}else
				return "休眠";
		},
    }, {
        glyph: 0xf007,
        iconAlign: 'top',
        scale: 'large',
        text: '定位状态',
        dataIndex: 'location',
        flex: 1,
        renderer:function(v){
			if(v==true){
				return "定位";
			}else
				return "未定位";
		},
    }],
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
