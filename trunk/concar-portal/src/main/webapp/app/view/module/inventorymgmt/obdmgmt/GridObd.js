Ext.define('BAICClassicApp.view.module.inventorymgmt.obdmgmt.GridObd', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        'BAICClassicApp.view.module.inventorymgmt.obdmgmt.GridToolbar'
    ],
    reference: 'gridobd',
    title: '<i class="fa fa-car" style="color:#da0d0d;"></i> 车辆信息列表',
    // selType: 'checkboxmodel', //checkbox
    // mode: 'SINGLE',
    bind: {
        store: '{myGridObdStore}'
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
        header: '设备号',
        dataIndex: 'imei',
        flex: 1
    }, {
        header: '车牌号',
        dataIndex: 'plateNumber',
        flex: 1
    }, {
        header: '单位代码',
        dataIndex: 'departmentCode',
        flex: 1
    }, {
        header: 'SIM卡号',
        dataIndex: 'simNumber',
        flex: 1
    }, {
        header: '最近登陆时间',
        dataIndex: 'traceTime',
        flex: 1
    }, {
        header: '活跃状态',
        dataIndex: 'active',
        flex: 1
    }, ],
    dockedItems: [
        /*{
                xclass: 'BAICClassicApp.view.module.inventorymgmt.obdmgmt.GridToolbar',
                dock: 'top',
                grid: this
            },*/
        {
            xtype: 'pagingtoolbar',
            dock: 'bottom',
            bind: {
                store: '{myGridObdStore}'
            },
            displayInfo: true
        }
    ],
    initComponent: function() {
        this.callParent();
    }
});
