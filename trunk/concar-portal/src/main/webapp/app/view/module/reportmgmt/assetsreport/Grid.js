Ext.define('BAICClassicApp.view.module.reportmgmt.assetsreport.Grid', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        'BAICClassicApp.view.module.reportmgmt.assetsreport.GridToolbar'
    ],
    reference: 'grid',
    title: '<i class="fa fa-car" style="color:#da0d0d;"></i> 车辆信息列表',
    // selType: 'checkboxmodel', //checkbox
    // mode: 'SINGLE',
    bind: {
        store: '{myGridStore}'
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
        dataIndex: "单位代码",
        flex:1
    }, {
        header: "单位名称",
        dataIndex: "单位名称",
        flex:1
    }, {
        header: "单位地址",
        dataIndex: "单位地址",
        flex:1
    }, {
        header: "车辆数",
        dataIndex: "车辆数",
        flex:1
    }, {
        header: "正常车数量",
        dataIndex: "正常车数量",
        flex:1
    }, {
        header: "故障车数量",
        dataIndex: "故障车数量",
        flex:1
    }, {
        header: "正常车占比",
        dataIndex: "正常车占比",
        flex:1
    }, {
        header: "故障车占比",
        dataIndex: "故障车占比",
        flex:1
    }, {
        header: "活跃车数量",
        dataIndex: "活跃车数量",
        flex:1
    }, {
        header: "休眠车数量",
        dataIndex: "休眠车数量",
        flex:1
    }, {
        header: "活跃车占比",
        dataIndex: "活跃车占比",
        flex:1
    }, {
        header: "休眠车占比",
        dataIndex: "休眠车占比",
        flex:1
    }, ],
    dockedItems: [
        /*{
                xclass: 'BAICClassicApp.view.module.reportmgmt.assetsreport.GridToolbar',
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
