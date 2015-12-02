Ext.define('BAICClassicApp.view.module.inventorymgmt.vehmgmt.GridSummary', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        // 'BAICClassicApp.view.module.inventorymgmt.vehmgmt.GridToolbar'
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
    mask: true,
    collapsible: true,
    multiSelect: true,
    columnLines: true, // 加上表格线
    // height: 350,
    columns: [{
        header: '封存车总数量',
        dataIndex: 'theTotalNumberOfSealedCar',
        flex: 1
    }, {
        header: '完成封存时间',
        dataIndex: 'completeStorageTime',
        flex: 1
    }, ],
    initComponent: function() {
        this.callParent();
    }
});
