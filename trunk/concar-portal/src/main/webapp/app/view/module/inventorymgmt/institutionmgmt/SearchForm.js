/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.inventorymgmt.institutionmgmt.SearchForm', {
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
        labelWidth: 30,
        margin: '0 20 0 0', //label和上一个输入框的样式，间隔5
        msgTarget: Ext.supports.Touch ? 'side' : 'qtip'
    },
    defaultType: 'textfield',
    items: [{
        xtype: 'container',
        layout: 'hbox',
        defaultType: 'textfield',
        items: [{
            name: '单位代码',
            fieldLabel: '单位代码',
            labelWidth: 60,
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
