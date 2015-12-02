Ext.define('BAICClassicApp.model.FullMapVehTrip', {
	extend: 'BAICClassicApp.model.Base',
	// requires: ['Ext.data.proxy.Rest',],
	fields: [],

	proxy: { //在base.js 里面有其他字段定义
		type: 'ajax',
		actionMethods : {
			read: 'POST', create: 'POST', update: 'POST', destroy: 'POST'
		},
		api: {
			read: '/concar-portal/web/record/loadTraceByImei',
			// read: 'app/store/mockdata/FuelMainInfo.json',
			// create: '/etruck-portal/web/vehicle/add',
			// update: '/etruck-portal/web/vehicle/update',
			// destroy: '/etruck-portal/web/vehicle/delete'
		},
		reader: {
			type: 'json',
            rootProperty: 'data',
            successProperty: 'status',
            totalProperty: 'total'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			encode: true,
			rootProperty: 'data'
		},
		listeners: {
			exception: function(proxy, response, operation) {
				Ext.MessageBox.show({
					title: 'REMOTE EXCEPTION',
					msg: operation.getError(),
					icon: Ext.MessageBox.ERROR,
					buttons: Ext.Msg.OK
				});
			}
			/*write: function(store, operation) {
				var record = operation.getRecords()[0],
					name = Ext.String.capitalize(operation.action),
					verb;
				if (name == 'Destroy') {
					record = operation.records[0];
					verb = 'Destroyed';
				} else {
					verb = name + 'd';
				}
				Ext.example.msg(name, Ext.String.format("{0} data: {1}", verb, record.getId()));
			},*/
		}
	}
});