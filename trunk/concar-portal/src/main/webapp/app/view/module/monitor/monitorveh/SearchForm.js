/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.monitor.monitorveh.SearchForm', {
    extend: 'Ext.form.Panel',
    requires: [
        'Ext.layout.container.HBox',
        'Ext.form.field.Date',
        'Ext.form.FieldContainer',
        'Ext.form.field.ComboBox',
        'Ext.form.FieldSet'
    ],
    reference: 'searchForm',
    bodyPadding: 8,
    header: false,
    fieldDefaults: {
        // labelAlign: 'right',
        labelWidth: 60,
        width: 140,
        margin: '0 20 0 0', //label和上一个输入框的样式，间隔5
        msgTarget: Ext.supports.Touch ? 'side' : 'qtip'
    },
    defaultType: 'textfield',
    items: [{
        xtype: 'container',
        layout: 'hbox',
        defaultType: 'textfield',
        items: [{
            name: '车牌号',
            fieldLabel: '车牌号',
            labelWidth: 50,
            width: 140,
            emptyText: '请填写',
        }, {
            name: '单位代码',
            fieldLabel: '单位代码',
            emptyText: '请填写',
        }, {
            name: '故障码',
            fieldLabel: '故障码',
            emptyText: '请填写',
        }, {
            xtype: 'combobox',
            fieldLabel: '故障状态',
            name: '故障状态',
            emptyText: '请选择',
            store: Ext.create('Ext.data.Store', {
                fields: ['abbr', 'name'],
                data: [{
                    "abbr": "是",
                    "name": "1"
                }, {
                    "abbr": "否",
                    "name": "1"
                }, ]
            }),
            queryMode: 'local',
            displayField: 'abbr',
            valueField: 'name',
            forceSelection: false,
            allowBlank: true,
        }, {
            xtype: 'combobox',
            fieldLabel: '续保状态',
            name: '续保状态',
            emptyText: '请选择',
            store: Ext.create('Ext.data.Store', {
                fields: ['abbr', 'name'],
                data: [{
                    "abbr": "是",
                    "name": "1"
                }, {
                    "abbr": "否",
                    "name": "1"
                }, ]
            }),
            queryMode: 'local',
            displayField: 'abbr',
            valueField: 'name',
            forceSelection: false,
            allowBlank: true,
        }, {
            xtype: 'combobox',
            fieldLabel: '维修状态',
            name: '维修状态',
            emptyText: '请选择',
            store: Ext.create('Ext.data.Store', {
                fields: ['abbr', 'name'],
                data: [{
                    "abbr": "是",
                    "name": "1"
                }, {
                    "abbr": "否",
                    "name": "1"
                }, ]
            }),
            queryMode: 'local',
            displayField: 'abbr',
            valueField: 'name',
            forceSelection: false,
            allowBlank: true,
        }, {
            xtype: 'combobox',
            fieldLabel: '活跃状态',
            name: '活跃状态',
            emptyText: '请选择',
            store: Ext.create('Ext.data.Store', {
                fields: ['abbr', 'name'],
                data: [{
                    "abbr": "是",
                    "name": "1"
                }, {
                    "abbr": "否",
                    "name": "1"
                }, ]
            }),
            queryMode: 'local',
            displayField: 'abbr',
            valueField: 'name',
            forceSelection: false,
            allowBlank: true,
        }, {
            xtype: 'combobox',
            fieldLabel: '综合状态',
            name: '综合状态',
            emptyText: '请选择',
            store: Ext.create('Ext.data.Store', {
                fields: ['abbr', 'name'],
                data: [{
                    "abbr": "是",
                    "name": "1"
                }, {
                    "abbr": "否",
                    "name": "1"
                }, ]
            }),
            queryMode: 'local',
            displayField: 'abbr',
            valueField: 'name',
            forceSelection: false,
            allowBlank: true,
        }]
    }],
    buttons: [{
        text: '<i class="fa fa-search" style="color:#da0d0d;"></i>&nbsp;搜索',
        //用iconCls会出现图标偏移
        handler: 'onSearchClick'
    }, {
        text: '<i class="fa fa-reply" style="color:#da0d0d;"></i>&nbsp;重置',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }],
    initComponent: function() {
        this.callParent();
    }
});
