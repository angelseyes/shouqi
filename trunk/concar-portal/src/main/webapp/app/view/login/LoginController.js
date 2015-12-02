/**
 * This View Controller is associated with the Login view.
 */
Ext.define('BAICClassicApp.view.login.LoginController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.login',

    requires: [
        'Ext.MessageBox',
        'Ext.state.CookieProvider',
    ],
    loginText: '登录中...',

    init: function() {
        //get cookie
        var cp = new Ext.state.CookieProvider();
        var username = cp.get("username");
        var password = cp.get("password");
        var rememberMe = cp.get("rememberMe");
        console.log('get cookie userame : ' + username);
        console.log('get cookie password : ' + password);
        console.log('get cookie rememberMe : ' + rememberMe);
        this.lookupReference("username").setValue(username);
        this.lookupReference("password").setValue(password);
        this.lookupReference("rememberMe").setValue(rememberMe);
    },

    onSpecialKey: function(field, e) {
        if (e.getKey() === e.ENTER) {
            this.doLogin();
        }
    },

    login: function(options) {
        console.log('inside login of LoginController');
        // Ext.Ajax.request({
        //     url: 'web/auth/login',
        //     method: 'POST',
        //     params: 'data=' + Ext.encode(options.data),
        //     scope: this,
        //     callback: this.onLoginReturn,
        //     original: options
        // });
        if (options.data) {
            console.log(options.data);
        }
        if (options.data.username == 'admin' && options.data.password == 'bqpl123') {
            this.onLoginSuccess(options.data.username, options.data.password, null);
        } else {
            this.onLoginFailure();
        }
    },

    // onLoginReturn: function(options, success, response) {
    //     options = options.original;
    //     //200ok
    //     if (success) {
    //         console.log('Login success');
    //         console.log(response);
    //         resp = Ext.decode(response.responseText);
    //         if (resp.success) {
    //             BAICClassicApp.view.globalAuthority = resp.data;
    //             Ext.callback(options.success, options.scope, [options.data.username, options.data.password, options.data.isRememberMe]);
    //         } else {
    //             Ext.callback(options.failure, options.scope, [resp.status, resp.failureMsg]);
    //         }
    //         return;
    //     }

    // },

    //入口
    onLoginClick: function() {
        console.log('inside doLogin of LoginController');
        this.doLogin();
    },

    //入口-setp1
    doLogin: function() {
        console.log('inside onLoginClick of LoginController');
        var form = this.lookupReference('form');
        if (form.isValid()) {
            Ext.getBody().mask(this.loginText);
            console.log(form.getValues());
            this.login({
                data: form.getValues(),
                scope: this,
                success: 'onLoginSuccess',
                failure: 'onLoginFailure'
            });
        }
    },

    onLoginFailure: function(status, failureMsg) {
        // Do something
        Ext.MessageBox.show({
            title: '登录失败',
            msg: '请核对用户名密码！',
            icon: Ext.MessageBox.ERROR,
            buttons: Ext.Msg.OK
        });
    },

    onLoginSuccess: function(logname, logpass, isRememberMe) {
        console.log('登录成功，用户名： ' + logname);
        console.log('登录成功，密  码： ' + logpass);
        console.log('登录成功，isRememberMe： ' + isRememberMe);
        var rememberMe = this.lookupReference('rememberMe').getValue();
        console.log('========== remeberMe : ' + rememberMe);
        this.fireViewEvent('afterLogin', logname);

        //保存cookie
        var cp = new Ext.state.CookieProvider();
        if (rememberMe) {
            cp.set("username", logname);
            cp.set("password", logpass);
            cp.set("rememberMe", rememberMe);
            console.log('set cookie username : ' + cp.get("username"));
            console.log('set cookie password : ' + cp.get("password"));
            console.log('set cookie rememberMe : ' + cp.get("rememberMe"));
        } else {
            cp.set("username", null);
            cp.set("password", null);
            cp.set("rememberMe", null);
            console.log('set cookie username : ' + cp.get("username"));
            console.log('set cookie password : ' + cp.get("password"));
            console.log('set cookie rememberMe : ' + cp.get("rememberMe"));
        }
    }

});
