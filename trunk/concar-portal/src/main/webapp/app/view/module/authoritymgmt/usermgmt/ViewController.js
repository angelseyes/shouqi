/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.authoritymgmt.usermgmt.ViewController', {
    extend: 'Ext.app.ViewController',
    requires: [
        'Ext.window.MessageBox',
        'BAICClassicApp.view.module.authoritymgmt.usermgmt.Window',
    ],
    onSearchClick: function() {
        //这是远程搜索，一次载入（通过searchfForm）
        this.nativeLoad(this.lookupReference('searchForm').getValues());
        //这是本地搜索，给store增加filter
        var grid = this.getView(),
            // Access the field using its "reference" property name.
            filterField = this.lookupReference('searchForm').down('field'),
            filters = this.getViewModel().getStore("myGridStore").getFilters();

        if (this.lookupReference('searchForm').down('field').value) {
            this.nameFilter = filters.add({
                id: 'nameFilter',
                property: '姓名',
                value: filterField.value,
                anyMatch: true,
                caseSensitive: false
            });
        } else if (this.nameFilter) {
            filters.remove(this.nameFilter);
            this.nameFilter = null;
        }
    },
    // onBeforeLoad: function() {
    //     // this.getViewModel().getStore("myStore").proxy.extraParams = this.lookupReference('searchForm').getValues();
    // },
    onBeforeLoadFuelMain: function() {
        // this.getViewModel().getStore("myGridFuelMainStore").proxy.extraParams = this.lookupReference('searchForm').getValues();
    },
    // onBeforeLoadFuelDetail: function() {
    //     if (this.lookupReference('gridFuelMain').getSelection().length != 0) {
    //         this.getViewModel().getStore("myGridObdStore").proxy.extraParams = {
    //             "imei": this.lookupReference('gridFuelMain').getSelection()[0].data.imei
    //         }
    //     }
    // },
    // onSelectionChangeGridFuelMain: function(sm, selections) {
    //     this.getViewModel().getStore("myGridObdStore").load();
    // },

    onAfterrender: function(it, eOpts) {
        this.nativeLoad(this.lookupReference('searchForm').getValues());
    },

    /*    onLoadMyGridStore: function(it, records, successful, eOpts) {

            var newStore = this.getViewModel().getData().myGridStore;
            newStore.add(records);
            this.lookupReference('grid').reconfigure(newStore);
            debugger;
        },*/

    nativeLoad: function(params) {
        var store = this.getViewModel().getStore("myGridStore");
        Ext.Ajax.request({
            url: 'app/store/mockdata/Users.json',
            params: params,
            method: 'POST',
            success: function(response) {
                store.setProxy({
                    type: 'memory',
                    data: Ext.JSON.decode(response.responseText),
                    enablePaging: true,
                    reader: {
                        type: 'json',
                        rootProperty: 'data',
                        totalProperty: 'total'
                    }
                });
                // debugger;
                store.load();
            }
        });
    },

    onSelectionChange: function(sm, selections) {
        var me = this,
            grid = me.lookupReference('grid');
        if (selections.length == 1) {
            me.getViewModel().setData({
                selectedRow: selections[0]
            });
        } else {
            me.getViewModel().setData({
                selectedRow: null
            });
        }
        //需要多选后还enable的按钮
        //grid.down('toolbar').child("button[handler='onDelete']").setDisabled(selections.length === 0);
    },

    add: function() {
        this.getViewModel().set('selectedRow', null);
        this.onEdit("新建", Ext.create('BAICClassicApp.model.User', {
            id: null
        }), false);
    },

    edit: function() {
        this.onEdit('编辑', this.getViewModel().get('selectedRow'), true);
    },
    onEdit: function(title, record, isUpdate) {
        var editWin = new BAICClassicApp.view.module.authoritymgmt.usermgmt.Window({
            title: title
        });
        // editWin.show();
        var form = editWin.down('form');
        // Ext.getCmp('roleId').getStore().load();
        // if (isUpdate) {
        //     form.child("textfield[name='userName']").setDisabled(true);
        //     Ext.getCmp('roleId').setValue(record.data.roleId);
        // }
        // this.getView().add(editWin);
        // form.loadRecord(record);
    },

});
