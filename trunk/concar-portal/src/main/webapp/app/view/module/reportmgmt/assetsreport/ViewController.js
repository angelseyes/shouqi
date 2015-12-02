/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.reportmgmt.assetsreport.ViewController', {
    extend: 'Ext.app.ViewController',
    requires: [
        'Ext.window.MessageBox',
        'BAICClassicApp.view.module.reportmgmt.assetsreport.Window',
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
                id: 'institutionIdFilter',
                property: '单位代码',
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
            url: 'app/store/mockdata/Institutions.json',
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

    /**Pie Chart related methods, 3 methods in total.
     * [onPreview description]
     * @return {[type]} [description]
     */
    /**Don't use
     * [onPreview description]
     * @return {[type]} [description]
     */
    // onPreview: function() {
    //     var chart = this.lookupReference('piechartbyvehtype');
    //     chart.preview();
    // },

    onDataRender: function(v) {
        return v + '%';
    },

    onVehTypeSeriesTooltipRender: function(tooltip, record, item) {
        var totalCountInStore = this.getViewModel().getStore('myChartPieByVehTypeStore').getCount();
        var recordsInStore = this.getViewModel().getStore('myChartPieByVehTypeStore').getRange(0, totalCountInStore - 1);
        var totalNum = 0;
        for (var i = recordsInStore.length - 1; i >= 0; i--) {
            totalNum = totalNum + recordsInStore[i].getData().data1;
        };
        tooltip.setHtml(record.get('os') + '数量: ' + record.get('data1') + '，所占比例' + Math.round(record.getData().data1 / totalNum * 100) + '%');
    },

    /**
     * 3d chart column methods, 4 involved.
     * [onDownload description]
     * @return {[type]} [description]
     */
    // Don't use
    // onDownload: function() {
    //     var chart = this.lookupReference('chart3dcolumnbyvehstatus');

    //     if (Ext.os.is.Desktop) {
    //         chart.download({
    //             filename: 'Industry size in major economies for 2011'
    //         });
    //     } else {
    //         chart.preview();
    //     }
    // },

    /**
     * 柱子上的数据格式化成千位
     * @param  {[type]} v [description]
     * @return {[type]}   [description]
     */
    onSeriesLabelRender: function(v) {
        // return Ext.util.Format.number(v / 1000, '0,000');//格式化成千位+,
        return v;
    },

    /**
     * 鼠标移动到柱子上，提示的tooltip，这里在那是不用
     * @param  {[type]} tooltip [description]
     * @param  {[type]} record  [description]
     * @param  {[type]} item    [description]
     * @return {[type]}         [description]
     */
    onTooltipRender: function(tooltip, record, item) {
        // tooltip.setHtml(record.get('vehStatus') + ': ' +
        //     Ext.util.Format.number(record.get('ind'), '0,000 (millions of USD)'));
        tooltip.setHtml('上报' + record.get('vehStatus') + '状态的车辆有: ' +
            Ext.util.Format.number(record.get('ind'), '0,000 (台)'));
    },

    /**
     * 渲染y轴的指标，这里暂时不用
     * @param  {[type]} axis          [description]
     * @param  {[type]} label         [description]
     * @param  {[type]} layoutContext [description]
     * @return {[type]}               [description]
     */
    onAxisLabelRender: function(axis, label, layoutContext) {
        // Custom renderer overrides the native axis label renderer.
        // Since we don't want to do anything fancy with the value
        // ourselves except adding a thousands separator, but at the same time
        // don't want to loose the formatting done by the native renderer,
        // we let the native renderer process the value first.
        // return Ext.util.Format.number(layoutContext.renderer(label) / 1000, '0,000');
        return label;
    },

    /**institution Pie Chart related methods, 3 methods in total.
     * [onPreview description]
     * @return {[type]} [description]
     */
    onInstitutionSeriesTooltipRender: function(tooltip, record, item) {
        var totalCountInStore = this.getViewModel().getStore('myChartPieByInstitutionStore').getCount();
        var recordsInStore = this.getViewModel().getStore('myChartPieByInstitutionStore').getRange(0, totalCountInStore - 1);
        var totalNum = 0;
        for (var i = recordsInStore.length - 1; i >= 0; i--) {
            totalNum = totalNum + recordsInStore[i].getData().data1;
        };
        tooltip.setHtml(record.get('os') + '数量: ' + record.get('data1') + '，所占比例' + Math.round(record.getData().data1 / totalNum * 100) + '%');
    },

    /**Mileage Pie Chart related methods, 3 methods in total.
     * [onPreview description]
     * @return {[type]} [description]
     */
    onMileageSeriesTooltipRender: function(tooltip, record, item) {
        var totalCountInStore = this.getViewModel().getStore('myChartPieByMileageStore').getCount();
        var recordsInStore = this.getViewModel().getStore('myChartPieByMileageStore').getRange(0, totalCountInStore - 1);
        var totalNum = 0;
        for (var i = recordsInStore.length - 1; i >= 0; i--) {
            totalNum = totalNum + recordsInStore[i].getData().data1;
        };
        tooltip.setHtml(record.get('os') + '数量: ' + record.get('data1') + '，所占比例' + Math.round(record.getData().data1 / totalNum * 100) + '%');
    },

});
