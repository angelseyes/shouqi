Ext.define('BAICClassicApp.view.module.monitor.monitortrip.GridTrack', {
    extend: 'Ext.grid.Panel',
    requires: [
        'BAICClassicApp.model.FullMapVehTrack',
    ],
    selType: 'checkboxmodel',

    store: {
        model: 'BAICClassicApp.model.FullMapVehTrack', //请考虑自己创建model,注意,model中的fields可以不写,后台返回什么,这些field就是什么.只要写清楚增删改查的url就可以了.
        pageSize: 999,
        autoLoad: true,
        remoteSort: true,
        remoteFilter: true,
        sorters: [{
            property: 'gpsTime',
            direction: 'DESC'
        }],
        listeners: {
            load: function(it, records, successful, eOpts) {
                console.log('Inside store of BAICClassicApp.view.module.monitor.monitortrip.GridTrack');
                var trackMap = Ext.ComponentQuery.query('#bmappanelTrack')[0].bmap;
                var trackMapWindow = Ext.ComponentQuery.query('#windowTrackmap')[0];

                //#Draw end point
                var lng = records[0].getData().longtitude;
                var lat = records[0].getData().latitude;
                var point = new BMap.Point(lng, lat);
                var marker = new BMap.Marker(point); // 创建标注点
                trackMap.addOverlay(marker);
                // var overrideOffsetSideMap = function() {
                //         trackMap.addOverlay(args[0]);
                //     } // 将标注添加到地图中};
                //     //repeat为2是workaround的方法
                // var runnerOverrideOffsetSideMap = new Ext.util.TaskRunner(),
                //     taskOverrideOffsetSideMap = runner.start({
                //         run: overrideOffsetSideMap,
                //         interval: 800,
                //         repeat: 2,
                //         args: [marker],
                //     });


                //#Draw line from start point to end point
                if (typeof(keyPointsArr) == 'undefined' || keyPointsArr == null) {
                    keyPointsArr = new Array();
                }
                keyPointsArr.push(point);

                //延迟重置地图中心点
                var delayedTask = new Ext.util.DelayedTask();
                delayedTask.delay(100, function(args) {
                    trackMap.setViewport(args);
                }, this, [keyPointsArr]);

                if (keyPointsArr.length > 1) {
                    var polyline = new BMap.Polyline(keyPointsArr, {
                        strokeColor: "blue",
                        strokeWeight: 2,
                        strokeOpacity: 0.5
                    }); //创建折线
                    trackMap.addOverlay(polyline);
                }


                var initTime = new Date();
                var ss;
                var updateClock = function() {
                    var ts = new Date() - initTime;
                    ss = 29 - parseInt(ts / 1000 % 60, 10); //计算剩余的秒数
                    trackMapWindow.setTitle(' 车辆TRACK-' + trackMapWindow.getViewModel().selectedRecord.data.vehplate + '-窗口将在' + ss + '秒后刷新');
                    if (ss == 0) {
                        // Ext.ComponentQuery.query('#alarm-drivedetail-maptrackwindow')[0].down('grid').getStore().load();

                        trackMapWindow.down('grid').getStore().load();
                        trackMap.clearOverlays();
                        keyPointsArr = null
                    }
                };

                var runner = new Ext.util.TaskRunner(),
                    task = runner.start({
                        run: updateClock,
                        interval: 1000,
                        repeat: 30
                    });

                if (ss == 0) {
                    Ext.TaskManager.stop(task); //stop this task after 60 seconds, cause the load method will be executed every 60 seconds, and new task will be created.
                }

                trackMapWindow.on('close', function() { //stop task when close window
                    Ext.TaskManager.stop(task);
                    keyPointsArr = null;
                });
            },

            beforeload: function(store, operation, eOpts) {
                store.proxy.extraParams = {
                    'vehPlate': Ext.ComponentQuery.query('#windowTrackmap')[0].getViewModel().selectedRecord.data.vehplate
                }
            }

        }
    },

    listeners: {
        // itemdblclick: 'onItemDoubleClick',
        // selectionchange: 'onSelectionChange'
        // afterrender: 'onAfterrenderGridTrack',
    },
    stateful: true,
    collapsible: true,
    multiSelect: true,
    columnLines: true, // 加上表格线
    // height: 160,
    forceFit: true,
    viewConfig: {
        enableTextSelection: true
    },
    itmeId: 'departureplan-gridtrack',
    header: false,
    // title: '行驶警报明细',
    // grid columns
    columns: [{
        flex: 2,
        text: '时间',
        dataIndex: 'collectTime',
    }, {
        flex: 1,
        text: '里程',
        dataIndex: 'mileage',
    }, {
        flex: 1,
        text: '纬度',
        dataIndex: 'latitude',
    }, {
        flex: 1,
        text: '径度',
        dataIndex: 'longtitude',
    }, {
        flex: 1,
        text: '速度',
        dataIndex: 'speed',
    }, {
        flex: 1,
        text: '转速',
        dataIndex: 'rpm',
    }, /*{
        flex: 1,
        text: '油量',
        dataIndex: 'fuelLevel',
    }, */{
        flex: 1,
        text: '油耗',
        dataIndex: 'fuelCons',
    }, {
        flex: 1,
        text: '温度',
        dataIndex: 'temperature',
    }, {
        flex: 1,
        text: '电压',
        dataIndex: 'vehBatteryVoltage',
    }],
    // dockedItems: [{
    //     xtype: 'pagingtoolbar',
    //     dock: 'bottom',
    //     bind: {
    //         store: '{myStoreGridComment}'
    //     },
    //     displayInfo: true
    // }],
    initComponent: function() {
        this.callParent();
        this.trackStartTime = new Date();
    }
});
