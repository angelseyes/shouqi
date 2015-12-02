/**
 * 一个模块的grid上面显示的toolbar,里面放置了各种操作按钮 暂时还没有考虑到权限
 */
Ext.define('BAICClassicApp.view.module.authoritymgmt.usermgmt.GridToolbar', {
    extend: 'Ext.toolbar.Toolbar',
    initComponent: function() {
        this.items = [{
            text: '新增用户',
            iconCls: 'x-fa fa-plus',
            handler: 'add',
            bind: {
                //如果需要多选的还disable的，就绑定全局
                // disabled: '{!selectedRow}'
            }
        }, {
            text: '编辑用户',
            iconCls: 'x-fa fa-pencil-square-o',
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
