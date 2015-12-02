Ext.define('BAICClassicApp.view.module.monitor.monitorveh.Grid', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        'BAICClassicApp.view.module.monitor.monitorveh.GridToolbar'
    ],
    reference: 'grid',
    title: '<i class="fa fa-map-pin" style="color:#da0d0d;"></i> 车辆信息列表',
    // selType: 'checkboxmodel', //checkbox
    // mode: 'SINGLE',
    bind: {
        store: '{myGridStore}'
    },
    listeners: {
        // itemdblclick: 'onItemDoubleClick',
        // selectionchange: 'onSelectionChange'
    },
    stateful: true,
    collapsible: true,
    forceFit: true,
    // mask: true,
    // collapsed: true,
    multiSelect: true,
    columnLines: true, // 加上表格线
    // height: 350,
    columns: [{
        header: '车牌号',
        dataIndex: 'plateNumber',
        flex: 1
    }, {
        header: '单位代码',
        dataIndex: 'departmentCode',
        flex: 1
    }, {
        header: '电压',
        dataIndex: 'voltage',
        flex: 1
    }, {
        header: '是否欠压',
        dataIndex: 'lackVoltage',
        flex: 1,
        renderer:function(v){
			if(v==0){
				return "否";
			}else
				return "是";
		},
    }, {
        header: '故障码',
        dataIndex: 'dtc',
        flex: 1,
        renderer:function(v){
			if(v==null){
				return "空";
			}else
				return v;
		},
    }, {
        header: '是否故障',
        dataIndex: 'error',
        flex: 1,
        renderer:function(v){
			if(v==0){
				return "否";
			}else
				return "是";
		},
    }, {
        header: '保险日期',
        dataIndex: 'insuranceDate',
        flex: 1
    }, {
        header: '是否需要续保',
        dataIndex: 'needInsurance',
        flex: 1,
        renderer:function(v){
			if(v==1){
				return "否";
			}else
				return "是";
		},
    }, {
        header: '保养日期',
        dataIndex: 'inspectionDate',
        flex: 1
    }, {
        header: '是否需要保养',
        dataIndex: 'needInspection',
        flex: 1,
        renderer:function(v){
			if(v==1){
				return "否";
			}else
				return "是";
		},
    }, {
        header: '停活时间（天）',
        dataIndex: 'sleepDayNumber',
        flex: 1
    }, {
        header: '是否活跃',
        dataIndex: 'needCheck',
        flex: 1,
        renderer:function(v){
			if(v==1){
				return "否";
			}else
				return "是";
		},
    }, {
        header: '综合状态',
        dataIndex: 'status',
        flex: 1,
        renderer:function(v){
			if(v==1){
				return "正常";
			}else
				return "异常";
		},
    }, ],
    dockedItems: [
        /*{
                xclass: 'BAICClassicApp.view.module.monitor.monitorveh.GridToolbar',
                dock: 'top',
                grid: this
            },*/
        {
            xtype: 'pagingtoolbar',
            dock: 'bottom',
            bind: {
                store: '{myGridStore}'
            },
            displayInfo: true
        }
    ],
    initComponent: function() {
        this.callParent();
    }
});
