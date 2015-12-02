Ext.define('BAICClassicApp.view.module.inventorymgmt.vehmgmt.GridVeh', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.Paging',
        'Ext.grid.column.RowNumberer',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Action',
        'BAICClassicApp.view.module.inventorymgmt.vehmgmt.GridToolbar'
    ],
    reference: 'gridveh',
    title: '<i class="fa fa-car" style="color:#da0d0d;"></i> 车辆信息列表',
    // selType: 'checkboxmodel', //checkbox
    // mode: 'SINGLE',
    bind: {
        store: '{myGridVehStore}'
    },
    listeners: {
        // itemdblclick: 'onItemDoubleClick',
        // selectionchange: 'onSelectionChange'
    },
    stateful: true,
    collapsible: true,
    forceFit: true,
    mask: true,
    // collapsed: true,
    multiSelect: true,
    columnLines: true, // 加上表格线
    // height: 350,
    columns: [{
            header: '车辆信息',
            columns: [{
                header: 'id',
                dataIndex: 'vehicleId',
                flex: 1,
                hidden: true,
            }, {
                header: '序号',
                dataIndex: 'vehicleId',
                flex: 1,
                width: 50,
            }, {
                header: '车牌号',
                dataIndex: 'plateNumber',
                flex: 1,
                width: 100,
            }, {
                header: '车架号',
                dataIndex: 'vin',
                flex: 1,
                width: 150,
            }, {
                header: '车型',
                dataIndex: 'model',
                flex: 1,
                width: 100,
            }, {
                header: '排量(升)',
                dataIndex: 'displacement',
                flex: 1,
            }, {
                header: '颜色',
                dataIndex: 'color',
                flex: 1
            }, {
                header: '公里数(KM)',
                dataIndex: 'mileage',
                flex: 1
            }, {
                header: '车辆登记日期',
                dataIndex: 'registeredDate',
                flex: 1
            }, ]
        }, {
            header: '车辆资料',
            columns: [{
                header: '组织单位代码证',
                columns: [{
                    header: '是否在有效期',
                    dataIndex: 'valid',
                    flex: 1
                }, {
                    header: '名称是否与行驶证统一',
                    dataIndex: 'sameName',
                    flex: 1
                },]
            }, {
                header: '车钥匙(数量)',
                dataIndex: 'keyNumber',
                flex: 1
            }, {
                header: '行驶证',
                dataIndex: 'license',
                flex: 1
            }, {
                header: '登记证书',
                dataIndex: 'certificate',
                flex: 1
            }, {
                header: '购车发票或调拨单复印',
                dataIndex: 'purchase',
                flex: 1
            }, {
                header: '购置税凭证',
                dataIndex: 'tax',
                flex: 1
            }, {
                header: '交强保单',
                columns: [{
                    header: '保单',
                    dataIndex: 'clivta',
                    flex: 1
                }, {
                    header: '保单期限',
                    dataIndex: 'clivtaDate',
                    flex: 1
                }, ]
            }, {
                header: '商业保单',
                columns: [{
                    header: '保单',
                    dataIndex: 'insurance',
                    flex: 1
                }, {
                    header: '保单期限',
                    dataIndex: 'insuranceDate',
                    flex: 1
                }, ]
            }],
        }, {
            header: '车辆状态',
            columns: [{
                header: '年检期限',
                dataIndex: 'inspectionDate',
                flex: 1,
                width: 100,
            }, {
                header: '违章记录',
                dataIndex: 'violation',
                flex: 1
            }, ]
        }],
    dockedItems: [
        /*{
                xclass: 'BAICClassicApp.view.module.inventorymgmt.vehmgmt.GridToolbar',
                dock: 'top',
                grid: this
            },*/
        {
            xtype: 'pagingtoolbar',
            dock: 'bottom',
            bind: {
                store: '{myGridVehStore}'
            },
            displayInfo: true
        }
    ],
    initComponent: function() {
        this.callParent();
    }
});
