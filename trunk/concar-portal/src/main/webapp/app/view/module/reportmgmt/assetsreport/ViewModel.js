/**
 * This class is the view model for the Template view of the application.
 */
Ext.define('BAICClassicApp.view.module.reportmgmt.assetsreport.ViewModel', {
    extend: 'Ext.app.ViewModel',
    data: {
        selectedRow: null,
        userNameReadOnly: false,
        HideResetPasswordBtn: false,
    },
    stores: {
        myGridStore: {
            model: 'BAICClassicApp.model.Institution',
            pageSize: 0,
            autoLoad: false,
            remoteSort: false,
            remoteFilter: false,
            sorters: [{
                property: 'name',
                direction: 'ASC'
            }],
            listeners: {
                // beforeload: 'onBeforeLoadVeh'
                // load: 'onLoadMyGridStore'
            }
        },
        //         myMemoryGridStore: {
        //             model: 'BAICClassicApp.model.Institution',
        //             pageSize: 10,
        //             autoLoad: false,
        //             remoteSort: false,
        //             remoteFilter: false,
        //             sorters: [{
        //                 property: 'name',
        //                 direction: 'ASC'
        //             }],
        //             listeners: {
        //                 // beforeload: 'onBeforeLoadVeh'
        //                 // load: 'onLoadMyGridStore'
        //             },
        //             proxy: {
        //                 type: 'memory',
        //                 enablePaging : true,
        //                 reader: {
        //                     type: 'json',
        //                     rootProperty: 'users'
        //                 }

        // }
        // }
        myChartPieByVehTypeStore: {
            fields: ['os', 'data1'],
            data: [{
                os: '10年以上',
                data1: 50
            }, {
                os: '5~10年',
                data1: 150
            }, {
                os: '2~5年',
                data1: 80
            }, {
                os: '0~2年',
                data1: 20
            }]
        },

        myChartPieByInstitutionStore: {
            fields: ['os', 'data1'],
            data: [{
                os: '单位1',
                data1: 100
            }, {
                os: '单位2',
                data1: 120
            }, {
                os: '单位3',
                data1: 40
            }, {
                os: '单位4',
                data1: 20
            }, {
                os: '单位5',
                data1: 80
            }]
        },

        myChartPieByMileageStore: {
            fields: ['os', 'data1'],
            data: [{
                os: '1~3万公里',
                data1: 1000
            }, {
                os: '3~5万公里',
                data1: 1200
            }, {
                os: '5~7万公里',
                data1: 400
            }, {
                os: '7~9万公里',
                data1: 200
            }, {
                os: '9万公里以上',
                data1: 2199
            }]
        },

        /**暂定ind表示车数量（对应于每一种状态）
         * [my3DColumnByVehStatusStore description]
         * @type {Object}
         */
        my3DColumnByVehStatusStore: {
            fields: ['vehStatus', 'ind'],
            data: [{
                vehStatus: 'DTC',
                ind: 10,
            }, {
                vehStatus: '续保',
                ind: 30,
            }, {
                vehStatus: '维保',
                ind: 40,
            }, {
                vehStatus: '休眠',
                ind: 120,
            }, {
                vehStatus: '正常',
                ind: 80,
            }]
        }
    }
});
