/**
 * Marked lines are multi-series lines displaying trends across multiple categories.
 * Markers are placed at each point to clearly depict their position on the chart.
 */
Ext.define('BAICClassicApp.view.module.reportmgmt.assetsreport.Chart3DColumnByVehStatus', {
    extend: 'Ext.panel.Panel',
    requires: ['Ext.chart.theme.Muted', 'Ext.chart.theme.Red', 'Ext.chart.theme.Blue', 'Ext.chart.theme.Yellow'],
    // width: 650,
    // height: 500,
    layout: 'fit',
    // tbar: [
    //     '->', {
    //         text: Ext.os.is.Desktop ? 'Download' : 'Preview',
    //         handler: 'onDownload'
    //     }
    // ],

    items: {
        xtype: 'cartesian',
        reference: 'chart3dcolumnbyvehstatus',
        theme: {
            type: 'yellow'
        },
        bind: {
            store: '{my3DColumnByVehStatusStore}'
        },
        insetPadding: '40 40 20 20',
        animation: Ext.isIE8 ? false : {
            easing: 'backOut',
            duration: 500
        },
        axes: [{
            type: 'numeric3d',
            position: 'left',
            fields: 'ind',
            maximum: 150,
            majorTickSteps: 10,
            label: {
                textAlign: 'right'
            },
            renderer: 'onAxisLabelRender',
            title: '车数量',
            grid: {
                odd: {
                    fillStyle: 'rgba(255, 255, 255, 0.06)'
                },
                even: {
                    fillStyle: 'rgba(0, 0, 0, 0.03)'
                }
            }
        }, {
            type: 'category3d',
            position: 'bottom',
            fields: 'vehStatus',
            grid: true
        }],
        series: [{
            type: 'bar3d',
            xField: 'vehStatus',
            yField: 'ind',
            style: {
                minGapWidth: 20
            },
            highlightCfg: {
                saturationFactor: 1.5
            },
            label: {
                field: 'ind',
                display: 'insideEnd',
                renderer: 'onSeriesLabelRender'
            },
            tooltip: {
                trackMouse: true,
                renderer: 'onTooltipRender'
            }
        }],
        sprites: [{
            type: 'text',
            text: '封存车辆状态比例',
            fontSize: 22,
            width: 100,
            height: 30,
            x: 12, // the sprite x position
            y: 30 // the sprite y position
        }, {
            type: 'text',
            text: '数据截止到：' + new Date().getFullYear() + '年' + (new Date().getMonth() + 1) + '月' + new Date().getDate() + '日',
            fontSize: 10,
            x: 12,
            y: 45
        }]
    }

});
