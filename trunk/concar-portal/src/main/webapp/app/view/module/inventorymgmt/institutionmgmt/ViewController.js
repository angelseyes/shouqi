/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.inventorymgmt.institutionmgmt.ViewController', {
    extend: 'Ext.app.ViewController',
    requires: [
        'Ext.window.MessageBox',
        'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.Window',
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
                id            : 'institutionIdFilter',
                property      : '单位代码',
                value         : filterField.value,
                anyMatch      : true,
                caseSensitive : false
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
        var store = this.getViewModel().getStore("myGridDepartmentStore");
        Ext.Ajax.request({
            url: '/concar-portal/web/department/loadDepartmentStat',
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

});
