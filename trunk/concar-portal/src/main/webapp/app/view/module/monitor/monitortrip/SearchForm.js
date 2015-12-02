/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.monitor.monitortrip.SearchForm', {
	extend: 'Ext.form.Panel',
	requires: [
		'Ext.layout.container.HBox',
		'Ext.form.field.Date',
		'Ext.form.FieldContainer',
		'Ext.form.field.ComboBox',
		'Ext.form.FieldSet',
	],
	reference: 'searchForm',
	bodyPadding: 10,
	fieldDefaults: {
		// labelAlign: 'right',
		labelWidth: 70,
		margin: '0 20 0 0', //label和上一个输入框的样式，间隔5
		msgTarget: Ext.supports.Touch ? 'side' : 'qtip'
	},
	defaultType: 'textfield',
	items: [{
		xtype: 'container',
		layout: 'hbox',
		defaultType: 'textfield',
		items: [{
			xtype: 'fieldcontainer',
			fieldLabel: '车牌号',
			combineErrors: true,
			msgTarget: 'side',
			layout: 'hbox',
			defaults: {
				hideLabel: true
			},
			items: [{
				reference: 'vehiclePlate',
				xtype: 'textfield',
				readOnly: true,
				name: 'vehiclePlate'
			}, {
				xtype: 'button',
				text: '多选',
				handler: 'onPlateMultiClick'
			}]
		}, {				
			xtype: 'treecombo',
			id: 'treecombo_institution_vehiclemgnt',
			selectChildren: true,
			canSelectFolders: true,
			treeWidth: 180,
			treeHeight: 200,
			multiselect: false,
			rootVisible: false, //不显示root
			store: Ext.create('Ext.data.TreeStore', {
				root: {
					text: 'Root',
					id: '0'
				},
				proxy: {
					type: 'ajax',
					url: '/etruck-portal/web/institution/load',
					actionMethods:{
						read: "POST"
			        },
					reader: {
						type: 'json'
					}
				},
				autoload: true
			}),
			name: 'institutionId',
			fieldLabel: '所属单位'
		}, {
			xtype: 'treecombo',
			id: 'treecombo_gpsstatus_vehiclemgnt',
			selectChildren: false,
			canSelectFolders: true,
			treeWidth: 180,
			treeHeight: 200,
			multiselect: true,
			rootVisible: false, //不显示root
			store: Ext.create('Ext.data.TreeStore', {
				root: {
					text: 'Root',
					id: '0'
				},
				proxy: {
					type: 'ajax',
					url: 'app/store/data/GPSComboStore.json',
					reader: {
						type: 'json'
					}
				},
				autoload: true
			}),
			name: 'gpsStatus',
			fieldLabel: 'GPS状态'
		}]
	}],
	buttons: [{
		text: '<img src ="resources/icon/searchs.png" align="top" height=18 width=18/>&nbsp;&nbsp;搜索',
		handler: 'onSearchClick'
	}, {
		text: '<img src ="resources/icon/resets.png" align="top" height=18 width=18/>&nbsp;&nbsp;重置',
		handler: function(){
			this.up('form').getForm().reset();
			Ext.getCmp('treecombo_institution_vehiclemgnt').setValue('');
			Ext.getCmp('treecombo_gpsstatus_vehiclemgnt').setValue('');
		}
	}],
	initComponent: function() {
		this.callParent();
	}
});