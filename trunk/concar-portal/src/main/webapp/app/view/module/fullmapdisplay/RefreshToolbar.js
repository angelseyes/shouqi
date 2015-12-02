/**
 * 一个模块的grid上面显示的toolbar,里面放置了各种操作按钮 暂时还没有考虑到权限
 */
Ext.define('BAICClassicApp.view.module.fullmapdisplay.RefreshToolbar', {
	extend: 'Ext.toolbar.Toolbar',
	initComponent: function() {
		this.items = [{
			glyph: 0xf008,
			text: '刷新',
			handler: 'refersh'
		}];
		this.callParent();
	}

});