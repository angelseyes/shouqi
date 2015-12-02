Ext.define('BAICClassicApp.view.module.inventorymgmt.obdmgmt.GridSummary', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        // 'BAICClassicApp.view.module.inventorymgmt.obdmgmt.GridToolbar'
    ],
    reference: 'gridsummary',
    title: '<i class="fa fa-list" style="color:#da0d0d;"></i> 摘要',
    // selType: 'checkboxmodel', //checkbox
    // mode: 'SINGLE',
    bind: {
        store: '{myGridSummaryStore}'
    },
    listeners: {
        // itemdblclick: 'onItemDoubleClick',
    },
    stateful: true,
    collapsible: true,
    multiSelect: true,
    columnLines: true, // 加上表格线
    // height: 350,
    columns: [{
        header: '设备总数量',
        dataIndex: '设备总数量',
        flex: 1
    }, {
        header: '完成接入时间',
        dataIndex: '完成接入时间',
        flex: 1
    }, ],
    initComponent: function() {
        this.callParent();
    }
});
