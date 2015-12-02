Ext.define('BAICClassicApp.view.module.inventorymgmt.institutionmgmt.Grid', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.GridToolbar'
    ],
    reference: 'grid',
    title: '<i class="fa fa-car" style="color:#da0d0d;"></i> 车辆信息列表',
    // selType: 'checkboxmodel', //checkbox
    // mode: 'SINGLE',
    bind: {
        store: '{myGridDepartmentStore}'
    },
    listeners: {
        // itemdblclick: 'onItemDoubleClick',
        // selectionchange: 'onSelectionChange'
        afterrender: 'onAfterrender'
    },
    stateful: true,
    collapsible: true,
    forceFit: true,
    mask: true,
    multiSelect: true,
    columnLines: true, // 加上表格线
    columns: [{
        header: "单位代码",
        dataIndex: "departmentCode",
        flex:1
    }, {
        header: "单位名称",
        dataIndex: "departmentName",
        flex:1
    }, {
        header: "单位地址",
        dataIndex: "address",
        flex:1
    }, {
        header: "车辆数",
        dataIndex: "vehicleNumber",
        flex:1
    }, {
        header: "正常车数量",
        dataIndex: "normalNumber",
        flex:1
    }, {
        header: "故障车数量",
        dataIndex: "abnormalNumber",
        flex:1
    }, {
        header: "正常车占比",
        dataIndex: "normalRate",
        flex:1
    }, {
        header: "活跃车数量",
        dataIndex: "activeNumber",
        flex:1
    }, {
        header: "休眠车数量",
        dataIndex: "inactiveNumber",
        flex:1
    }, {
        header: "活跃车占比",
        dataIndex: "activeRate",
        flex:1
    }],
    dockedItems: [
        /*{
                xclass: 'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.GridToolbar',
                dock: 'top',
                grid: this
            },*/
        {
            xtype: 'pagingtoolbar',
            dock: 'bottom',
            bind: {
                store: '{myGridDepartmentStore}'
            },
            displayInfo: true
        }
    ],
    initComponent: function() {
        this.callParent();
    }
});
