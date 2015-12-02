/**
 * This class is the view model for the Template view of the application.
 */
Ext.define('BAICClassicApp.view.module.reportmgmt.analysisreport.ViewModel', {
    extend: 'Ext.app.ViewModel',
    data: {
        selectedRow: null,
        userNameReadOnly: false,
        HideResetPasswordBtn: false,
    },
    stores: {
        myChartLineByDTCStore: {
            autoLoad: false,
            proxy: {
                type: 'memory'
            },
            fields: ['date', 'data1', 'data2', 'data3', 'data4', 'other'],
            data: [{
                date: '2015/10/1',
                data1: 200,
                data2: 37,
                data3: 35,
                data4: 4,
                other: 4
            }, {
                date: '2015/10/2',
                data1: 210,
                data2: 37,
                data3: 36,
                data4: 5,
                other: 2
            }, {
                date: '2015/10/3',
                data1: 320,
                data2: 36,
                data3: 37,
                data4: 4,
                other: 4
            }, {
                date: '2015/10/4',
                data1: 240,
                data2: 36,
                data3: 38,
                data4: 5,
                other: 3
            }, {
                date: '2015/10/5',
                data1: 270,
                data2: 35,
                data3: 39,
                data4: 4,
                other: 4
            }, {
                date: '2015/10/6',
                data1: 310,
                data2: 34,
                data3: 42,
                data4: 4,
                other: 3
            }, {
                date: '2015/10/7',
                data1: 268,
                data2: 34,
                data3: 43,
                data4: 4,
                other: 3
            }]
        },
        myChartLineByActivityStore: {

            fields: ['date', 'data1', 'data2', 'data3', 'data4', 'other'],
            data: [{
                date: '2015/10/1',
                data1: 300,
                data2: 37,
                data3: 35,
                data4: 4,
                other: 4
            }, {
                date: '2015/10/2',
                data1: 288,
                data2: 37,
                data3: 36,
                data4: 5,
                other: 2
            }, {
                date: '2015/10/3',
                data1: 265,
                data2: 36,
                data3: 37,
                data4: 4,
                other: 4
            }, {
                date: '2015/10/4',
                data1: 310,
                data2: 36,
                data3: 38,
                data4: 5,
                other: 3
            }, {
                date: '2015/10/5',
                data1: 320,
                data2: 35,
                data3: 39,
                data4: 4,
                other: 4
            }, {
                date: '2015/10/6',
                data1: 230,
                data2: 34,
                data3: 42,
                data4: 4,
                other: 3
            }, {
                date: '2015/10/7',
                data1: 280,
                data2: 34,
                data3: 43,
                data4: 4,
                other: 3
            }]

        }
    }
});
