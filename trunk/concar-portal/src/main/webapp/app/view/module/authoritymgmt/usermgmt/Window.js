Ext.define('BAICClassicApp.view.module.authoritymgmt.usermgmt.Window', {
    extend: 'Ext.window.Window',
    requires: ['BAICClassicApp.view.module.authoritymgmt.usermgmt.WindowController', 'Ext.form.Panel'],
    controller: {
        xclass: 'BAICClassicApp.view.module.authoritymgmt.usermgmt.WindowController'
    },
    layout: 'fit',
    width: 400,
    resizable: true,
    constrain: true,
    modal: true,
    autoShow: true,
    shadow: true,
    ghost: false,
    iconCls: 'fa fa-user',
    bodyPadding: 0,
    items: [{
        xtype: 'form',
        bodyPadding: 10,

        defaultType: 'textfield',
        defaults: {
            anchor: '100%'
        },

        fieldDefaults: {
            msgTarget: 'side'
        },

        items: [{
            name: 'id',
            hidden: true
        }, {
            name: 'createdTime',
            hidden: true
        }, {
            name: 'userName',
            fieldLabel: '用户名',
            allowBlank: false,
            vtype: 'uniqueName'
        }, {
            name: 'password',
            fieldLabel: '新密码',
            inputType: 'password',
            allowBlank: false,
            vtype: "alphanum"
        }, {
            name: 'rePassword',
            fieldLabel: '确认新密码',
            inputType: 'password',
            validator: function(value) {
                var newPassword = this.previousSibling('[name=password]');
                return (value === newPassword.getValue()) ? true : '两次输入密码不一致';
            },
            allowBlank: false
        }, {
            name: 'realName',
            fieldLabel: '姓名',
            allowBlank: false
        }, {
            name: 'mobileNumber',
            fieldLabel: '手机',
            vtype: "alphanum",
            allowBlank: false
        }, {
            name: 'email',
            fieldLabel: '电子邮箱',
            vtype: "email",
            allowBlank: false
        }, {
            xtype: 'combobox',
            fieldLabel: '部门',
            name: 'enable',
            forceSelection: true,
            editable: false,
            //value: '0',
            store: Ext.create('Ext.data.ArrayStore', {
                fields: ['display', 'value'],
                data: [
                    ['部门1', 0],
                    ['部门2', 1],
                    ['部门3', 0],
                    ['部门4', 1],
                    ['部门5', 0],
                    ['部门6', 1],
                    ['部门7', 0],
                    ['部门8', 1],
                    ['部门9', 0],
                    ['部门10', 1],
                ]
            }),
            valueField: 'value',
            displayField: 'display',
            queryMode: 'local',
            allowBlank: false
        }, ],

        dockedItems: [{
            xtype: 'toolbar',
            dock: 'bottom',
            items: ['->', {
                xtype: 'button',
                text: '保存',
                formBind: true,
                handler: 'save'
            }, {
                xtype: 'button',
                text: '关闭',
                handler: 'close'
            }]
        }]
    }],

    initComponent: function() {
        this.callParent();
    }

});
