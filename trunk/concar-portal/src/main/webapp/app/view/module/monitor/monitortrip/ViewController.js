/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.monitor.monitortrip.ViewController', {
    extend: 'Ext.app.ViewController',
    requires: [
        'Ext.MessageBox',
        'BAICClassicApp.view.module.monitor.monitortrip.MapTrackWindow'
    ],

    onBeforeMyVehStoreLoad: function() {
        if (this.lookupReference('gridinstitution').getSelection().length != 0) {
            this.getViewModel().getStore("myVehStore").proxy.extraParams = {
            	"departmentId": this.lookupReference('gridinstitution').getSelection()[0].data.departmentId
            }
        }
        this.lookupReference('gridVeh').mask('Loading...');
    },

    onLoadMyVehStore: function(it, records, successful, eOpts) {
        this.lookupReference('gridVeh').unmask();
    },

    offsetMap: function(map) {
        var xOffset = -(this.lookupReference('bmappanel').getWidth() - this.lookupReference('panelTrackingMap').getWidth()) / 2;
        var yOffset = -(this.lookupReference('bmappanel').getHeight() - this.lookupReference('panelTrackingMap').getHeight()) / 2;
        var panOptions = {
            noAnimation: true
        };
        map.panBy(xOffset, yOffset, panOptions);
    },

    addMarkersOnthemap: function(map, selections) {
        map.clearOverlays();
        var pointArr = new Array();
        var markerArr = new Array()
        for (var i = selections.length - 1; i >= 0; i--) {
            var point = new BMap.Point(selections[i].data.longitude, selections[i].data.latitude);
            pointArr.push(point);
            var marker = new BMap.Marker(point); // 创建标注
            if (selections[i].data.departmentName) {
                var label = new BMap.Label(selections[i].data.departmentName);
            } else if (selections[i].data.vehplate) {
                var label = new BMap.Label(selections[i].data.vehplate);
            }

            marker.setLabel(label); //设置label
            markerArr.push(marker);
        };
        for (var i = markerArr.length - 1; i >= 0; i--) {
            map.addOverlay(markerArr[i]);
            markerArr[i].setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        };
        map.setViewport(pointArr);
        map.zoomOut();
        map.zoomOut();
        this.offsetMap(map)
    },

    onSelectionChangeGridInstitution: function(sm, selections) {
        if (selections.length != 0) { //解决刷新键问题
            this.getViewModel().getStore("myVehStore").load();
            this.lookupReference('panelTrackingMap').expand(true);
            var map = this.lookupReference('bmappanel').bmap;
            this.addMarkersOnthemap(map, selections);
        }
    },

    onSelectionChangeVehGrid: function(sm, selections) {
        //地图上显示点
//        if (selections.length != 0) { //反向朝上选择也会进来,所以规避这个问题
//            this.lookupReference('panelTrackingMap').expand(true);
//            var map = this.lookupReference('bmappanel').bmap;
//            this.addMarkersOnthemap(map, selections);
//        }
//        console.log(selections);
        //加载车辆行程日志
        if (selections.length != 0) { //反向朝上选择也会进来,所以规避这个问题
            this.getViewModel().getStore("myVehTripStore").load({
                params: {
                    imei: selections[0].data.imei
                }
            });
        }
    },

    /**
     * 在地图上画trace信息。没有使用创建好的store(myVehTriplineStore)和对应的model，因为懒得改代码了。
     * 等第一次演示完成后，根据需求后续整理代码再进行修改。
     *
     * 下面的代码条例还是很清晰的，来自于capital rental，差别在于第一次加载没有使用store自动加载，而是手动写了ajax请求，完成后再success
     * 回调函数中处理地图上画线。而用mvvm来加载差别在于，mvvm store加载后，写一个store的load事件，在这个事件中在地图上画图。
     * @param  {[type]} sm         [description]
     * @param  {[type]} selections [description]
     * @return {[type]}            [description]
     */
    onSelectionChangeGridVehTrip: function(sm, selections) {
        // debugger;
        // this.getViewModel().getStore("myVehTriplineStore").load({
        //     params: {
        //         traceId: 'hardcode'
        //     }
        // });

        var traceMap = this.lookupReference('bmappanel').bmap;
        if (selections.length != 0) { //反向朝上选择也会进来,所以规避这个问题
            /**
             * 获取精简后的点，用于地图画线
             * @param  {[type]} response [description]
             * @param  {[type]} opts)    {                                                   var resp [description]
             * @return {[type]}          [description]
             */
            Ext.Ajax.request({
                // url: 'web/tnt/loadTraceData',
                url: '/concar-portal/web/record/loadTraceLineByTraceId',
                method: 'POST',
                params: {
//                	"traceId": this.lookupReference('gridVeh').getSelection()[0].data.traceId,
                    "traceId": selections[0].data.traceId,
                },
                success: function(response, opts) {
                    // console.log(response.responseText);
                    var resp = Ext.decode(response.responseText);
                    if (resp.success) {
                        //处理地图
                        traceMap.clearOverlays();
                        var BPointArr = new Array();
                        var pstart, pend;
                        //找起点
                        for (var i = 0; i < resp.data.length; i++) {
                            if (resp.data[i].lon != null &&
                                resp.data[i].lon != '' &&
                                resp.data[i].lon.length != 0 &&
                                resp.data[i].lat != null &&
                                resp.data[i].lat != '' &&
                                resp.data[i].lat.length != 0) {
                                pstart = new BMap.Point(resp.data[i].lon, resp.data[i].lat);
                                break;
                            } else {
                                i++;
                            }

                        };
                        //找终点
                        for (var i = resp.data.length - 1; i >= 0; i--) {
                            if (resp.data[i].lon != null &&
                                resp.data[i].lon != '' &&
                                resp.data[i].lon.length != 0 &&
                                resp.data[i].lat != null &&
                                resp.data[i].lat != '' &&
                                resp.data[i].lat.length != 0) {
                                pend = new BMap.Point(resp.data[i].lon, resp.data[i].lat);
                                break;
                            } else {
                                i--;
                            }
                        };
                        var mstart = new BMap.Marker(pstart);
                        var mend = new BMap.Marker(pend);
                        var lstart = new BMap.Label("起点", {
                            offset: new BMap.Size(20, -10)
                        });
                        var lend = new BMap.Label("终点", {
                            offset: new BMap.Size(-20, -10)
                        });
                        traceMap.addOverlay(mstart);
                        traceMap.addOverlay(mend);
                        mstart.setLabel(lstart);
                        mend.setLabel(lend);
                        for (var i = resp.data.length - 1; i >= 0; i--) {
                            if (resp.data[i].lon != null &&
                                resp.data[i].lon != '' &&
                                resp.data[i].lon.length != 0 &&
                                resp.data[i].lat != null &&
                                resp.data[i].lat != '' &&
                                resp.data[i].lat.length != 0) {
                                //点不为空才放入
                                BPointArr.push(new BMap.Point(resp.data[i].lon, resp.data[i].lat));
                            }
                        }
                        var polyline = new BMap.Polyline(BPointArr, {
                            strokeColor: "red",
                            strokeWeight: 5,
                            strokeOpacity: 0.8
                        });
                        traceMap.addOverlay(polyline);
                        traceMap.setViewport(BPointArr); //先设置viewport，再设置中心点
                        traceMap.setCenter(pstart);
                        // traceMap.zoomOut();
                        this.offsetMap(traceMap); //对地图做偏移，以保证起点显示在正中
                    } else {
                        Ext.MessageBox.show({
                            title: resp.status,
                            msg: resp.failureMsg,
                            icon: Ext.MessageBox.ERROR,
                            buttons: Ext.Msg.OK
                        });
                    }

                },
                failure: function(response, opts) {
                    Ext.MessageBox.show({
                        title: 'REMOTE EXCEPTION',
                        msg: op.getError(),
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                },
                scope: this
            });

        }

    },

    onAfterRenderPanelTrackingMap: function(panel, options) {
        // console.log('inside afterrender of PanelTrackingMap');
        // var me = this;
        // var inventoryMap = me.lookupReference('bmappanel').bmap;
        // inventoryMap.clearOverlays();
        // /**
        //  * 这个地方尝试了afterlayout, task给参数传参数, 都因为没有先于map加载无法实现偏移功能, 只能在这里hardcode了.
        //  * @return {[type]} [description]
        //  */
        // var offsetSideMap = function() {
        //     Ext.ComponentQuery.query('#panelTrackingMap')
        //     var xOffset = (Ext.ComponentQuery.query('#panelInventoryTrackingMap')[0].getWidth() - Ext.ComponentQuery.query('#bmappanelInventoryTrackingMap')[0].getWidth()) / 2;
        //     var yOffset = (Ext.ComponentQuery.query('#panelInventoryTrackingMap')[0].getHeight() - Ext.ComponentQuery.query('#bmappanelInventoryTrackingMap')[0].getHeight()) / 2;
        //     var panOptions = {
        //         noAnimation: true
        //     };
        //     Ext.ComponentQuery.query('#bmappanelInventoryTrackingMap')[0].bmap.panBy(xOffset, yOffset, panOptions);
        // };
        //repeat为2是workaround的方法
        //     var runner = new Ext.util.TaskRunner(),
        //         task = runner.start({
        //             run: offsetSideMap,
        //             interval: 500,
        //             repeat: 2,
        //         });
    },
});
