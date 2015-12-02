Ext.define('BAICClassicApp.view.module.fullmapdisplay.MapTrackWindow', {
    extend: 'Ext.window.Window',
    xtype: 'map-window',
    requires: [
        'BAICClassicApp.ux.map.MapPanel',
        'BAICClassicApp.view.module.fullmapdisplay.MapTrackWindowController',
        'BAICClassicApp.view.module.fullmapdisplay.GridTrack',
    ],
    id: 'track-map-window',
    reference: 'trackmapwindow',
    itemId: 'windowTrackmap',
    height: 522,
    width: 920,
    title: '百度地图',
    autoScroll: true,
    plain: true,
    resizable: false,
    bodyPadding: 5,
    // constrain: true,
    closable: true,
    layout: {
        type: 'vbox',
        pack: 'start',
        align: 'stretch'
    },
    controller: {
        xclass: 'BAICClassicApp.view.module.fullmapdisplay.MapTrackWindowController'
    },

    listeners: {
        restore: {
            fn: function(win) {
                Ext.each(me.dashWindows, function(w) {
                    w.doConstrain();
                });
            }
        }
    },
    initComponent: function() {
        console.log(this);
        var me = this,
            // record = me.getViewModel().get('selectedRow'),
            grid = {
                autoShow: true,
                xclass: 'BAICClassicApp.view.module.fullmapdisplay.GridTrack',
            };
        // if (record === null) {
        //  form.autoShow = false;
        // }
        me.items = [{
            xtype: 'mappanel',
            // xtype: 'panel',
            reference: 'btrackmappanel',
            itemId: 'bmappanelTrack',
            width: 900,
            height: 400,
            plain: true,
            border: false,
            zoomLevel: 12,
            gmapType: 'map',
            overlays: [],
            markers: [],
            ContextMenus: ['在此添加标注', '放大', '缩小', '放置到最大级', '查看全国'],
            mapControls: ['NavigationControl', 'ScaleControl', 'OverviewMapControl', 'MapTypeControl'],
            listeners: {
                afterrender: 'onAfterRender',
            }
        }, grid];
        me.callParent();
    }
});
