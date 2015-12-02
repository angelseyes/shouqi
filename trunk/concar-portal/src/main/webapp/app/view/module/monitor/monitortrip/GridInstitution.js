Ext.define('BAICClassicApp.view.module.monitor.monitortrip.GridInstitution', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        // 'BAICClassicApp.view.module.monitor.monitortrip.GridToolbar'
    ],
    reference: 'gridinstitution',
    title: '<i class="fa fa-sitemap" style="color:#da0d0d;"></i> 单位列表',
    // selType: 'checkboxmodel',
    bind: {
        store: '{myInstitutionStore}'
    },
    listeners: {
        // itemdblclick: 'onItemDoubleClick',
        selectionchange: 'onSelectionChangeGridInstitution'
    },
    stateful: true,
    multiSelect: true,
    columnLines: true, // 加上表格线
    // grid columns
    columns: [{
        dataIndex: 'departmentId',
        text: 'id',
        hidden: true,
        flex: 1
    }, {
    	glyph : 0xf007,
		iconAlign : 'top',
		scale : 'large',
        dataIndex: 'departmentName',
        text: '单位名称',
        flex: 1
    }, {
        glyph : 0xf007,
        iconAlign : 'top',
        scale : 'large',
        dataIndex: 'address',
        text: '单位地址',
        flex: 1
    }, ],
    dockedItems: [{
        xtype: 'pagingtoolbar',
        dock: 'bottom',
        bind: {
            store: '{myInstitutionStore}'
        },
        displayInfo: true
    }],
    initComponent: function() {
        this.callParent();
    }
});
