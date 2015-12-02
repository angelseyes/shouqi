Ext.define('BAICClassicApp.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.main',

    // formulas: {
    //     selectionText: function(get) {
    //         var selection = get('treelist.selection'),
    //             path;
    //         if (selection) {
    //             path = selection.getPath('text');
    //             path = path.replace(/^\/Root/, '');
    //             console.log(path);
    //             return '<br/><br/><br/>Selected: ' + path;
    //         } else {
    //             return '<br/><br/><br/>No node selected';
    //             console.log(path);
    //         }
    //     }
    // },

    stores: {
        navItems: {
            type: 'tree',
            root: {
                expanded: true,
                children: [{
                    text: '&nbsp;全图展示',
                    iconCls: 'x-fa fa-map',
                    leaf: true,
                    tabIcon: '<i class="fa fa-map" style="color:#da0d0d;"></i> ',
                    view: "BAICClassicApp.view.module.fullmapdisplay.View",
                }, {
                    text: '&nbsp;资产管理',
                    iconCls: 'x-fa fa-home',
                    children: [{
                        text: '&nbsp;车辆管理',
                        iconCls: 'x-fa fa-car',
                        leaf: true,
                        tabIcon: '<i class="fa fa-car" style="color:#da0d0d;"></i> ',
                        view: "BAICClassicApp.view.module.inventorymgmt.vehmgmt.View",
                    }, {
                        text: '&nbsp;设备管理',
                        iconCls: 'x-fa fa-fax',
                        leaf: true,
                        tabIcon: '<i class="fa fa-fax" style="color:#da0d0d;"></i> ',
                        view: "BAICClassicApp.view.module.inventorymgmt.obdmgmt.View",
                    }, {
                        text: '&nbsp;单位管理',
                        iconCls: 'x-fa fa-sitemap',
                        leaf: true,
                        tabIcon: '<i class="fa fa-sitemap" style="color:#da0d0d;"></i> ',
                        view: "BAICClassicApp.view.module.inventorymgmt.institutionmgmt.View",
                    }]
                }, {
                    text: '&nbsp;监控管理',
                    iconCls: 'x-fa fa-camera',
                    children: [{
                        text: '&nbsp;车辆监控',
                        iconCls: 'x-fa fa-map-pin',
                        leaf: true,
                        tabIcon: '<i class="fa fa-map-pin" style="color:#da0d0d;"></i> ',
                        view: "BAICClassicApp.view.module.monitor.monitorveh.View",
                    }, {
                        text: '&nbsp;行程监控',
                        iconCls: 'x-fa fa-road',
                        leaf: true,
                        tabIcon: '<i class="fa fa-road" style="color:#da0d0d;"></i> ',
                        view: "BAICClassicApp.view.module.monitor.monitortrip.View",
                    }]
                }, {
                    text: '&nbsp;报表管理',
                    iconCls: 'x-fa fa-area-chart',
                    children: [{
                        text: '&nbsp;资产报表',
                        iconCls: 'x-fa fa-pie-chart',
                        leaf: true,
                        tabIcon: '<i class="fa fa-pie-chart" style="color:#da0d0d;"></i> ',
                        view: "BAICClassicApp.view.module.reportmgmt.assetsreport.View",
                    }, {
                        text: '&nbsp;分析报表',
                        iconCls: 'x-fa fa-line-chart',
                        leaf: true,
                        tabIcon: '<i class="fa fa-line-chart" style="color:#da0d0d;"></i> ',
                        view: "BAICClassicApp.view.module.reportmgmt.analysisreport.View",
                    }]
                }, {
                    text: '&nbsp;权限管理',
                    iconCls: 'x-fa fa-group',
                    children: [{
                        text: '&nbsp;用户管理',
                        iconCls: 'x-fa fa-user',
                        leaf: true,
                        tabIcon: '<i class="fa fa-user" style="color:#da0d0d;"></i> ',
                        view: "BAICClassicApp.view.module.authoritymgmt.usermgmt.View",
                    }]
                }]
            }
        }
    }
});
