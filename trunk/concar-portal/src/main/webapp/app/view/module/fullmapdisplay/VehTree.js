/**
 *
 */
Ext.define('BAICClassicApp.view.region.left.VehTree', {
	alias: 'widget.left-navigation-tree', // 调用者引用这一项的属性是：xtype，调用者必须用use引用当前js文件（view）
	extend: 'Ext.tree.Panel', // 扩展自tree.panel
	// controller: 'navigation-tree',
	// MVVM架构的控制器的名称，会在当前路径中根据alias名称，即controller. + alias名称 来确定controll类

	/*viewModel : {
		type : 'navigation-tree'
		// MVVM架构的viewModel的类型，会在当前路径中根据‘Main’ + Model 来确定文件名
	},*/
	requires: [
		'Ext.tree.*',
		'Ext.data.*',
		'BAICClassicApp.view.module.*'
	],
	//    xtype: 'tree-reorder', 没有用，没有引用叫tree-order的东西

	height: 400,
	width: 350,
	title: '功能列表',
	rootVisible: false, //根节点root不可见。
	useArrows: true,
	id: 'navigation-tree-id',

	initComponent: function() {
		Ext.apply(this, {
			store: new Ext.data.TreeStore({
				/*后台取数据的逻辑
				 * proxy: { type: 'ajax', url: '/tree/get-nodes.php' },
				 */
				/*
				 * root: { text: 'Ext JS', id: 'src', expanded: true },
				 */

				root: {
					text: "功能菜单",
					expanded: true,
					leaf: false,
				},
				proxy: {
					type: 'ajax',
					url: '/etruck-portal/web/role/menu',
					actionMethods:{
						read: "POST"
			        },
					reader: {
						type: 'json'
					}
				},
				autoload: false
			}),
			viewConfig: {
				plugins: {
					ptype: 'treeviewdragdrop',
					containerScroll: true
				}
			},
			tbar: [{
				text: '展开菜单',
				scope: this,
				handler: this.onExpandAllClick
			}, {
				text: '折叠菜单',
				scope: this,
				handler: this.onCollapseAllClick
			}]
		});
		this.callParent();
	},
	listeners: {
		selectionchange: 'onVehTreeSelectionchange'
	},
	onExpandAllClick: function() {
		var me = this,
			toolbar = me.down('toolbar');

		me.getEl().mask('Expanding tree...');
		toolbar.disable();

		this.expandAll(function() {
			me.getEl().unmask();
			toolbar.enable();
		});
	},

	onCollapseAllClick: function() {
		var toolbar = this.down('toolbar');

		toolbar.disable();
		this.collapseAll(function() {
			toolbar.enable();
		});
	}
});