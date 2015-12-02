Ext.define('BAICClassicApp.view.module.monitor.monitortrip.MapTrackWindowController', {
    extend: 'Ext.app.ViewController',
    requires: [],
    init: function() {

    },
    onBeforeLoad: function() {
        // this.getViewModel().getStore("myStore").proxy.extraParams = this.lookupReference('searchForm').getValues();
    },
    cancel: function() {
        this.getView().close();
    },

    save: function() {},

    onAfterRender: function(window, options) {
        console.log('inside afterrender of MapTraceWindowController');
        var me = this;
        //直接在这里获取了，下面注册回调函数中就不用重复获取了。
        var bTrackMapPanel = me.lookupReference('btrackmappanel');
        var trackMapWindow = bTrackMapPanel.up('window');
        var trackMapGrid = trackMapWindow.down('grid');
        //实例化地图,注意Map的参数

        // trackHistoryRecord = me.getViewModel().selectedRecord;

        // bTrackMapPanel.bmap = new BMap.Map(bTrackMapPanel.id);
        // bTrackMapPanel.getMap().centerAndZoom('武汉', 15);
        // bTrackMapPanel.getMap().enableScrollWheelZoom();
        // bTrackMapPanel.getMap().addEventListener("tilesloaded", me.onMapReady());
    },
});
