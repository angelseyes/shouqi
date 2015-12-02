/**
 * A basic donut chart functions precisely like a pie chart. The only difference is that
 * the center is blank. This is typically done to increase the readability of the data
 * labels that may be around. The example makes use of two interactions: 'itemhighlight'
 * and 'rotate'. To use the first one, hover over or tap on a pie sector. To use the
 * second one, click or tap and then drag anywhere on the chart.
 */
Ext.define('BAICClassicApp.view.module.reportmgmt.assetsreport.ChartPieByVehType', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.chart.PolarChart',
    ],
    // width: 650,
    // tbar: [
    //     '->', {
    //         text: 'Preview',
    //         handler: 'onPreview'
    //     }
    // ],
    layout: {
        type: 'fit',
    },
    // title: '封存车辆车型比例',
    items: [{
        xtype: 'polar',
        reference: 'piechartbyvehtype',
        insetPadding: 10,
        innerPadding: 20,

        bind: {
            store: '{myChartPieByVehTypeStore}'
        },
        legend: {
            docked: 'right'
        },
        interactions: ['rotate', 'itemhighlight'],
        sprites: [{
            type: 'text',
            text: '封存车辆使用年限比例',
            fontSize: 22,
            width: 100,
            height: 30,
            x: 12, // the sprite x position
            y: 30 // the sprite y position
        }, {
            type: 'text',
            text: '数据截止到：' + new Date().getFullYear() + '年' + (new Date().getMonth() + 1) + '月' + new Date().getDate() + '日',
            x: 12,
            y: 45
        }],
        series: [{
            type: 'pie',
            angleField: 'data1',
            donut: 50,
            label: {
                field: 'os',
                display: 'outside'
            },
            highlight: true,
            tooltip: {
                trackMouse: true,
                renderer: 'onVehTypeSeriesTooltipRender'
            }
        }]
    }],
    initComponent: function() {
        this.callParent();
        // var date = new Date()
        // var spriteText = '数据截止到：' + date.getFullYear() + '年' + date.getMonth() + '月' + date.getDate() + '日';
    }
});
