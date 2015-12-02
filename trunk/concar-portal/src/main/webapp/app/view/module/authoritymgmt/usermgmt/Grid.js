Ext.define('BAICClassicApp.view.module.authoritymgmt.usermgmt.Grid', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        'BAICClassicApp.view.module.authoritymgmt.usermgmt.GridToolbar'
    ],
    reference: 'grid',
    title: '<i class="fa fa-user" style="color:#da0d0d;"></i> 用户列表',
    // selType: 'checkboxmodel', //checkbox
    mode: 'SINGLE',
    bind: {
        store: '{myGridStore}'
    },
    listeners: {
        // itemdblclick: 'onItemDoubleClick',
        selectionchange: 'onSelectionChange',
        afterrender: 'onAfterrender'
    },
    stateful: true,
    collapsible: true,
    forceFit: true,
    mask: true,
    multiSelect: true,
    columnLines: true, // 加上表格线
    columns: [{
        dataIndex: '用户名',
        header: '用户名',
        flex: 1,
    }, {
        dataIndex: '姓名',
        header: '姓名',
        flex: 1,
    }, {
        dataIndex: '邮箱',
        header: '邮箱',
        flex: 1,
    }, {
        dataIndex: '手机',
        header: '手机',
        flex: 1,
    }, {
        dataIndex: '部门编码',
        header: '部门编码',
        flex: 1,
    }, ],
    dockedItems: [{
        xclass: 'BAICClassicApp.view.module.authoritymgmt.usermgmt.GridToolbar',
        dock: 'top',
        grid: this
    }, {
        xtype: 'pagingtoolbar',
        dock: 'bottom',
        bind: {
            store: '{myGridStore}'
        },
        displayInfo: true
    }],
    initComponent: function() {
        this.callParent();
    }
});
