/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('BAICClassicApp.Application', {
    extend: 'Ext.app.Application',

    name: 'BAICClassicApp',

    stores: [
        // TODO: add global / shared stores here
    ],

    launch: function() {
        console.log('inside onLaunch of Root.js');
        if (Ext.isIE8) {
            Ext.Msg.alert('Not Supported', 'This example is not supported on Internet Explorer 8. Please use a different browser.');
            return;
        }
        splashscreen = Ext.getBody().mask('正在载入应用程序...', 'splashscreen');
        console.log(splashscreen);
        Ext.DomHelper.insertFirst(Ext.query('.x-mask-msg.splashscreen')[0], {
            cls: 'x-splash-icon'
        });
        splashscreen.addCls('splashscreen');

        var me = this;
        me.session = new Ext.data.Session({
            autoDestroy: false
        });

        var view = "BAICClassicApp.view.login.Login";
        me.loginWindow = Ext.create(view, {
            session: this.session,
            listeners: {
                scope: this,
                afterLogin: 'onAfterLogin'
            }
        });

        me.loginMain = new BAICClassicApp.view.main.MainLogin({});

        Ext.Ajax.on('requestcomplete', function(conn, resp, options) {
            if (resp && resp.getResponseHeader && resp.getResponseHeader('errorMsg')) {
                location.href = "/demo-portal/";
            }
        }, this);

        // Ext.Ajax.request({
        //     url: 'web/auth/access',
        //     params: {
        //         usage: 'checkSession'
        //     },
        //     success: function(response) {
        //         var text = response.responseText;
        //         resp = Ext.decode(response.responseText);
        //         console.log(text);
        //         // process server response here
        //         if (resp.success) { //已登录
        //             console.log('inside response.success 已登录');
        //             me.loginWindow.destroy();
        //             me.loginMain.destroy();
        //             me.username = "alreadyLoggedinUser";
        //             CRental.view.globalAuthority = resp.data;
        //             var task = new Ext.util.DelayedTask(function() {
        //                 splashscreen.fadeOut({
        //                     duration: 1000,
        //                     remove: true
        //                 });

        //                 splashscreen.next().fadeOut({
        //                     duration: 1000,
        //                     remove: true
        //                 });

        //                 console.log('launch!');
        //             });
        //             task.delay(2000);
        //             me.showUI();
        //         } else { //未登录或超时
        //             console.log('inside response.success.else 未登录或超时');
        //             var taskFadeoutSplashScreen = new Ext.util.DelayedTask(function() {
        //                 splashscreen.fadeOut({
        //                     duration: 1000,
        //                     remove: true
        //                 });

        //                 splashscreen.next().fadeOut({
        //                     duration: 1000,
        //                     remove: true
        //                 });

        //                 console.log('launch login window!');
        //             });
        //             var taskShowLoginWindow = new Ext.util.DelayedTask(function() {
        //                 me.loginWindow.show();
        //                 me.loginMain.show();
        //             });
        //             taskFadeoutSplashScreen.delay(1000);
        //             taskShowLoginWindow.delay(2000);
        //         }
        //     },
        //     failure: function(response, opts) {
        //         Ext.MessageBox.show({
        //             title: 'REMOTE EXCEPTION',
        //             msg: opts.getError(),
        //             icon: Ext.MessageBox.ERROR,
        //             buttons: Ext.Msg.OK
        //         });
        //     },
        // });

        //刷新都会到这个地方来，屏蔽了检测session部分。
        console.log('inside response.success.else 未登录或超时');
        var taskFadeoutSplashScreen = new Ext.util.DelayedTask(function() {
            splashscreen.fadeOut({
                duration: 1000,
                remove: true
            });

            // splashscreen.next().fadeOut({
            //     duration: 1000,
            //     remove: true
            // });

            console.log('launch login window!');
        });
        var taskShowLoginWindow = new Ext.util.DelayedTask(function() {
            me.loginWindow.show();
            me.loginMain.show();
        });
        taskFadeoutSplashScreen.delay(1000);
        taskShowLoginWindow.delay(2000);
    },

    loadingText: 'Loading...',
    /**
     * Called when the login controller fires the "login" event.
     *
     * @param loginController
     * @param username
     */
    onAfterLogin: function(username, loginController) {
        console.log('inside onLogin of Root');
        console.log('username: ' + username);
        this.loginWindow.destroy();
        this.loginMain.destroy();
        this.username = username; //this.username中的this是什么？username是在这里才定义的吗？this指的是root.js
        Ext.getBody().unmask();
        this.showUI();
    },

    /**
     * 登陆成功后，显示真实UI。不能传入viewmodel，否则会覆盖写死的菜单。
     * @return {[type]} [description]
     */
    showUI: function() {
        console.log('show ui started');
        this.viewport = new BAICClassicApp.view.main.Main({
            session: this.session, //传入session, 暂时不知道用处
            // viewModel: { //用户信息传入视图
            //     data: {
            //         currentUser: this.username
            //     }
            // }
        });
    },

    /**
     * 暂时不知道有什么用
     * @return {[type]} [description]
     */
    getSession: function() {
        return this.session;
    },
    onAppUpdate: function() {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function(choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
