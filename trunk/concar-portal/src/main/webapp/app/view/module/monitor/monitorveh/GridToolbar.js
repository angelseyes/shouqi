/**
 * 一个模块的grid上面显示的toolbar,里面放置了各种操作按钮 暂时还没有考虑到权限
 */
Ext.define('BAICClassicApp.view.module.monitor.monitorveh.GridToolbar', {
    extend: 'Ext.toolbar.Toolbar',
    initComponent: function() {
        this.items = [{
            glyph: 0xf007,
            text: '发车',
            // iconCls: 'create-button', 
            handler: 'add',
            bind: {
                //如果需要多选的还disable的，就绑定全局
                disabled: '{!selectedRow}'
            }
        }, {
            glyph: 0xf007,
            text: '还车',
            // iconCls: 'edit-button', 
            handler: 'edit',
            disabled: true,
            bind: {
                //如果需要多选的还disable的，就绑定全局
                disabled: '{!selectedRow}'
            }
        }];
        this.callParent();
    }

});
