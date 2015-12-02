/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.fullmapdisplay.ViewController', {
    extend: 'Ext.app.ViewController',
    requires: [
        'Ext.MessageBox',
        'BAICClassicApp.view.module.fullmapdisplay.MapTrackWindow'
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
            var point;
            if (selections[i].data.departmentName) {
            	point = new BMap.Point(selections[i].data.longitude, selections[i].data.latitude);
            } else if (selections[i].data.plateNumber) {
            	point = new BMap.Point(selections[i].data.y, selections[i].data.x);
            }
            pointArr.push(point);
            var marker = new BMap.Marker(point); // 创建标注
            if (selections[i].data.departmentName) {
                var label = new BMap.Label(selections[i].data.departmentName);
            } else if (selections[i].data.plateNumber) {
                var label = new BMap.Label(selections[i].data.plateNumber);
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
        if (selections.length != 0) { //反向朝上选择也会进来,所以规避这个问题
//        	for (var i = selections.length - 1; i >= 0; i--) {
        		this.transGPStoBaidu(selections[0]);
//        	}
            this.lookupReference('panelTrackingMap').expand(true);
            var map = this.lookupReference('bmappanel').bmap;
            this.addMarkersOnthemap(map, selections);
        }
    },
    
    transGPStoBaidu: function(selection){
		Ext.Ajax.request({
			url : '/concar-portal/web/vehicle/loadPosition',
			async: false, 
			params : {
				flag: 'vehicle',
				longitude: selection.data.longitude, 
				latitude: selection.data.latitude,
			},
			success : function(response,opts) {
				var resp = Ext.decode(response.responseText);
				if (resp.success) {
					selection.data.x = resp.data.lat;
					selection.data.y = resp.data.lon;
				} else {
					Ext.MessageBox.show({
								title : '系统异常',
								msg : '车辆坐标转化异常',
								icon : Ext.MessageBox.ERROR,
								buttons : Ext.Msg.OK
							});
				}
			},
			failure : function(response,opts) {
				Ext.MessageBox.show({
							title : '系统异常',
							msg:  '车辆坐标转化异常',
							icon : Ext.MessageBox.ERROR,
							buttons : Ext.Msg.OK
						});
			}
		}); 
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

    onItemDoubleClickVehGrid: function(it, record, item, index, e, eOpts) {
        this.onTrack('Track', record);
    },

    onTrack: function(title, record) {
        var trackWin = new BAICClassicApp.view.module.fullmapdisplay.MapTrackWindow({
            title: title,
            viewModel: {
                selectedRecord: record //把selectedRow也传到mapWindow的viewModel中
            }
        });
        trackWin.show();
        this.getView().add(trackWin);
    },
});
