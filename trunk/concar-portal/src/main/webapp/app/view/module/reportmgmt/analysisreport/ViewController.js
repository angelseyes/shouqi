/**
 * This class is the template view for the application.
 */
Ext.define('BAICClassicApp.view.module.reportmgmt.analysisreport.ViewController', {
    extend: 'Ext.app.ViewController',
    requires: [
        'Ext.window.MessageBox',
        'BAICClassicApp.view.module.reportmgmt.analysisreport.Window',
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

    /**以下2个方法为DTC表所用，setSeriesLineWidth为公用
     * 出于暂时的逻辑考虑，暂不使用，先直接返回
     * @param  {[type]} axis          [description]
     * @param  {[type]} label         [description]
     * @param  {[type]} layoutContext [description]
     * @return {[type]}               [description]
     */
    onDTCAxisLabelRender: function(axis, label, layoutContext) {
        // Custom renderer overrides the native axis label renderer.
        // Since we don't want to do anything fancy with the value
        // ourselves except appending a '%' sign, but at the same time
        // don't want to loose the formatting done by the native renderer,
        // we let the native renderer process the value first.
        // return layoutContext.renderer(label) + '%';
        return layoutContext.renderer(label);
    },

    onDTCSeriesTooltipRender: function(tooltip, record, item) {
        tooltip.setHtml(record.get('date') + '上报DTC车辆数目为: ' + record.get('data1'));
    },

    /**以下2个方法为Activity表所用，setSeriesLineWidth为公用
     * 出于暂时的逻辑考虑，暂不使用，先直接返回
     * @param  {[type]} axis          [description]
     * @param  {[type]} label         [description]
     * @param  {[type]} layoutContext [description]
     * @return {[type]}               [description]
     */
    onActivityAxisLabelRender: function(axis, label, layoutContext) {
        // Custom renderer overrides the native axis label renderer.
        // Since we don't want to do anything fancy with the value
        // ourselves except appending a '%' sign, but at the same time
        // don't want to loose the formatting done by the native renderer,
        // we let the native renderer process the value first.
        // return layoutContext.renderer(label) + '%';
        return layoutContext.renderer(label);
    },

    onActivitySeriesTooltipRender: function(tooltip, record, item) {
        tooltip.setHtml(record.get('date') + '活动车辆数目为: ' + record.get('data1'));
    },



    /**
     * 公用方法
     * @param {[type]} item      [description]
     * @param {[type]} lineWidth [description]
     */
    setSeriesLineWidth: function(item, lineWidth) {
        if (item) {
            item.series.setStyle({
                lineWidth: lineWidth
            });
        }
    },

    onItemHighlightChange: function(chart, newHighlightItem, oldHighlightItem) {
        this.setSeriesLineWidth(newHighlightItem, 4);
        this.setSeriesLineWidth(oldHighlightItem, 2);
    },

});
