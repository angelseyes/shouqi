/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.monitor.monitorveh.ViewController', {
    extend: 'Ext.app.ViewController',
    requires: [
        'Ext.window.MessageBox',
        'BAICClassicApp.view.module.monitor.monitorveh.Window',
    ],
    // onSearchClick: function() {
    //     this.getViewModel().getStore("myGridFuelMainStore").load();
    // },
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

});
