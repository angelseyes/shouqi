Ext.define('BAICClassicApp.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    requires: [
        'BAICClassicApp.view.module.inventorymgmt.vehmgmt.View'
    ],

    onToggleConfig: function(menuitem) {
        var treelist = this.lookupReference('treelist');
        treelist.setConfig(menuitem.config, menuitem.checked);
    },

    onToggleMicro: function(button, pressed) {
        var treelist = this.lookupReference('treelist'),
            navBtn = this.lookupReference('navBtn'),
            ct = treelist.ownerCt;

        treelist.setMicro(pressed);

        if (pressed) {
            navBtn.setPressed(true);
            // navBtn.disable();
            this.oldWidth = ct.width;
            ct.setWidth(44);
        } else {
            ct.setWidth(this.oldWidth);
            // navBtn.enable();
        }

        // IE8 has an odd bug with handling font icons in pseudo elements;
        // it will render the icon once and not update it when something
        // like text color is changed via style addition or removal.
        // We have to force icon repaint by adding a style with forced empty
        // pseudo element content, (x-sync-repaint) and removing it back to work
        // around this issue.
        // See this: https://github.com/FortAwesome/Font-Awesome/issues/954
        // and this: https://github.com/twbs/bootstrap/issues/13863
        if (Ext.isIE8) {
            this.repaintList(treelist, pressed);
        }
    },

    onToggleNav: function(button, pressed) {
        var treelist = this.lookupReference('treelist'),
            ct = this.lookupReference('treelistContainer');

        treelist.setExpanderFirst(!pressed);
        treelist.setUi(pressed ? 'nav' : null);
        treelist.setHighlightPath(pressed);
        ct[pressed ? 'addCls' : 'removeCls']('treelist-with-nav');

        if (Ext.isIE8) {
            this.repaintList(treelist);
        }
    },

    repaintList: function(treelist, microMode) {
        treelist.getStore().getRoot().cascadeBy(function(node) {
            var item, toolElement;

            item = treelist.getItem(node);

            if (item && item.isTreeListItem) {
                if (microMode) {
                    toolElement = item.getToolElement();

                    if (toolElement && toolElement.isVisible(true)) {
                        toolElement.syncRepaint();
                    }
                } else {
                    if (item.element.isVisible(true)) {
                        item.iconElement.syncRepaint();
                        item.expanderElement.syncRepaint();
                    }
                }
            }
        });
    },

    /**
     * 主页点击tree中菜单的响应方法，定义在treeitem中
     * @param  {[type]} sender [description]
     * @param  {[type]} info   [description]
     * @param  {[type]} eOpts  [description]
     * @return {[type]}        [description]
     */
    onTreeItemClick: function(sender, info, eOpts) {
        var view = info.node.data.view;
        //根据View中的reference定义，找到tabPanel对象，在这里是Main中定义的xtype: 'tabpanel'。
        var tabPanel = this.lookupReference('centertabpanel');
        var tabTitle = info.node.data.tabIcon + info.node.data.text;
        var tabName = info.node.data.text;
        //如果View在NavigationTree中已经定义，则进入处理流程，否则不开新tab。
        if (view) {
            //child是查找指令。
            var theNewTab = tabPanel.child('container[tabName=' + tabName + ']');
            //此处找到tabPanel对象下的container，如果该tab已经打开，则不再打开。
            if (!theNewTab) {
                var viewObject = Ext.create(view, {
                    // icon: selected.data.icon,
                    viewName: view,
                    first: true,
                    // treePath: this.getPath(selected),
                    tabName: tabName,
                    title: tabTitle,
                    closable: true,
                    reorderable: true
                });
                theNewTab = tabPanel.add(viewObject);
            }
            // debugger;
            this.activeTab = theNewTab;
            tabPanel.setActiveTab(theNewTab);
        }
    },

    /**
     * 必要的刷新, 留着
     * @param  {[type]} tabpanel         [description]
     * @param  {[type]} newViewContainer [description]
     * @return {[type]}                  [description]
     */
    onTabChange: function(tabpanel, newViewContainer) {
        // if (newViewContainer.getItemId() == 'devicelist-tab') {
        //     newViewContainer.down('grid').getStore('myStore').reload();
        // }
        // 
    },

    onAfterrender: function(it, eOpts) {
        console.log('onAfterrender');

        var tabPanel = this.lookupReference('centertabpanel');
        var fullmapdisplayMap = tabPanel.child('container[tabName=' + '&nbsp;全图展示' + ']');
        var view = "BAICClassicApp.view.module.fullmapdisplay.View";
        //此处找到tabPanel对象下的container，如果该tab已经打开，则不再打开。
        if (!fullmapdisplayMap) {
            var viewObject = Ext.create(view, {
                // icon: selected.data.icon,
                viewName: view,
                first: true,
                // treePath: this.getPath(selected),
                tabName: '&nbsp;全图展示',
                title: '<i class="fa fa-map" style="color:#da0d0d;"></i>&nbsp;全图展示',
                closable: true,
                reorderable: true
            });
            fullmapdisplayMap = tabPanel.add(viewObject);
        }
        this.activeTab = fullmapdisplayMap;
        tabPanel.setActiveTab(fullmapdisplayMap);


        //测试用，默认打开两个tab，关掉最左边的功能列表
        // var viewObject = Ext.create('BAICClassicApp.view.module.inventorymgmt.vehmgmt.View', {
        //     // icon: selected.data.icon,
        //     viewName: 'BAICClassicApp.view.module.inventorymgmt.vehmgmt.View',
        //     first: true,
        //     // treePath: this.getPath(selected),
        //     tabName: '&nbsp;车辆管理',
        //     title: '<i class="fa fa-car" style="color:#da0d0d;"></i> &nbsp;车辆管理',
        //     closable: true,
        //     reorderable: true
        // });
        // tabPanel.add(viewObject);
    }
});
