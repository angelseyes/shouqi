Ext.define('BAICClassicApp.view.module.reportmgmt.analysisreport.ChartLineByActivity', {
    extend: 'Ext.panel.Panel',
    layout: {
        type: 'fit',
    },
    items: {
        xtype: 'cartesian',
        reference: 'chartlinebyactivity',
        width: '100%',
        height: 500,
        interactions: {
            type: 'panzoom',
            zoomOnPanGesture: true
        },
        animation: {
            duration: 200
        },
        bind: {
            store: '{myChartLineByActivityStore}'
        },
        insetPadding: '80 40 20 40',
        innerPadding: {
            left: 40,
            right: 40,
        },
        sprites: [{
            type: 'text',
            text: '分析报表 - 活动车辆',
            fontSize: 22,
            width: 100,
            height: 30,
            x: 40, // the sprite x position
            y: 40 // the sprite y position
        }, {
            type: 'text',
            text: '数据截止到：' + new Date().getFullYear() + '年' + new Date().getMonth() + '月' + new Date().getDate() + '日',
            fontSize: 10,
            x: 42,
            y: 60
        }],
        axes: [{
            type: 'numeric',
            position: 'left',
            title: '活动车辆数目（单位：辆）',
            grid: true,
            minimum: 0,
            maximum: 400,
            renderer: 'onActivityAxisLabelRender'
        }, {
            type: 'category',
            position: 'bottom',
            grid: true,
            label: {
                rotate: {
                    degrees: -45
                }
            }
        }],
        series: [{
            type: 'line',
            xField: 'date',
            yField: 'data1',
            style: {
                lineWidth: 2
            },
            marker: {
                radius: 4,
                lineWidth: 2
            },
            label: {
                field: 'data1',
                display: 'over'
            },
            highlight: {
                fillStyle: '#000',
                radius: 5,
                lineWidth: 2,
                strokeStyle: '#fff'
            },
            tooltip: {
                trackMouse: true,
                showDelay: 0,
                dismissDelay: 0,
                hideDelay: 0,
                renderer: 'onActivitySeriesTooltipRender'
            }
        }],
        listeners: {
            itemhighlightchange: 'onItemHighlightChange'
        }
    },

});
