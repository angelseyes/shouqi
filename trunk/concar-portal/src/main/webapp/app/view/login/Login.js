Ext.define('BAICClassicApp.view.login.Login', {
    extend: 'Ext.window.Window',

    requires: [
        'BAICClassicApp.view.login.LoginController',
        'Ext.form.Panel',
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.ComboBox',
        'Ext.state.CookieProvider'
    ],

    xtype: 'login',
    controller: 'login',

    closable: false,
    resizable: false,
    modal: true,
    draggable: false,
    autoShow: false,
    bodyPadding: 0,
    title: '请登录-公务车辆执行执法管理平台',
    // Fields will be arranged vertically, stretched to full width
    layout: 'anchor',
    width: 320,
    defaults: {
        anchor: '100%'
    },

    items: [{
        xtype: 'form',
        reference: 'form',
        bodyPadding: 10,
        defaults: {
            anchor: '100%'
        },
        items: [{
            xtype: 'component',
            id: 'login-logo'
        }, {
            xtype: 'textfield',
            name: 'username',
            reference: 'username',
            value: '',
            labelWidth: 50,
            fieldLabel: '用户名',
            allowBlank: false,
            // emptyText: etruck.string.login.username.emptyText,
            enableKeyEvents: true,
            listeners: {
                specialKey: 'onSpecialKey'
            }
        }, {
            xtype: 'textfield',
            name: 'password',
            reference: 'password',
            value: '',
            labelWidth: 50,
            inputType: 'password',
            fieldLabel: '密码',
            allowBlank: false,
            // emptyText: etruck.string.login.password.emptyText,
            enableKeyEvents: true,

            listeners: {
                specialKey: 'onSpecialKey'
            }
        }, {
            xtype: 'checkboxfield',
            boxLabel: '记住我',
            name: 'rememberMe',
            value: '',
            inputValue: '1',
            reference: 'rememberMe'
        }, ]
    }],

    dockedItems: [{
        xtype: 'toolbar',
        dock: 'bottom',
        items: [{
            xtype: 'tbfill',
        }, {
            xtype: 'button',
            text: '登录',
            listeners: {
                click: 'onLoginClick'
            },
        }, {
            xtype: 'button',
            text: '取消',
            listeners: {
                // click: 'onLoginClick'
            },
        }],
    }]
});
