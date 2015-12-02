Ext.define('BAICClassicApp.view.module.inventorymgmt.institutionmgmt.Window', {
    extend: 'Ext.window.Window',
    requires: ['BAICClassicApp.view.module.inventorymgmt.institutionmgmt.WindowController'],
    controller: {
        xclass: 'BAICClassicApp.view.module.inventorymgmt.institutionmgmt.WindowController'
    },
    items: [{
        xclass: 'Ext.ux.form',
        padding: 5,
        bodyPadding: 10,
        listeners: {
            // beforerender: 'beforerenderForm',
        },
        defaultType: 'textfield',
        defaults: {
            anchor: '100%'
        },
        frame: true,
        width: 800,
        fieldDefaults: {
            labelWidth: 120
        },
        items: [{ //挂车基本信息
            xtype: 'fieldset',
            title: '驾驶员基本信息',
            layout: 'anchor',
            defaults: {
                anchor: '100%'
            },
            collapsible: true,
            collapsed: false,
            items: [{
                    xtype: 'container',
                    margin: '0 0 10',
                    layout: 'hbox',
                    items: [{ //3
                        xtype: 'container',
                        flex: 1,
                        defaultType: 'textfield',
                        layout: 'anchor',
                        defaults: {
                            anchor: '100%',
                            hideEmptyLabel: false
                        },
                        items: [{
                            xtype: 'textfield',
                            name: 'id',
                            hidden: true
                        }, { //挂车车牌号
                            name: 'driverName',
                            fieldLabel: '姓名',
                            emptyText: '',
                            allowBlank: false,
                        }, { //手机号
                            name: 'tel',
                            fieldLabel: '手机号',
                            emptyText: '',
                        }, { //家庭联系电话
                            name: 'homeTel',
                            fieldLabel: '家庭联系电话',
                            emptyText: '',
                        }, { //户口所在地
                            name: 'registeredResidenceLocation',
                            fieldLabel: '户口所在地',
                            emptyText: '',
                        }, { //当前居住地
                            name: 'currentLiveLocation',
                            fieldLabel: '当前居住地',
                            emptyText: '',
                        }, { //员工状态,使用哪个url?
                            xtype: 'combobox',
                            name: 'workerStatus',
                            fieldLabel: '员工状态',
                            emptyText: '',
                            store: Ext.create('Ext.data.ArrayStore', {
                                fields: ['display', 'value'],
                                autoLoad: false,
                                proxy: {
                                    type: 'ajax',
                                    url: '/etruck-portal/web/dictionary/getDictionaryByType?data=employee_status',
                                    simpleSortMode: true,
                                    reader: {
                                        type: 'json',
                                        rootProperty: 'data',
                                    }
                                }
                            }),
                            valueField: 'value',
                            displayField: 'display',
                            mode: 'remote',
                            forceSelection: true,
                            allowBlank: false,
                        }, ],
                    }, { //第一行第二列
                        xtype: 'component',
                        width: 20
                    }, { //第一行第三列
                        xtype: 'container',
                        flex: 1,
                        defaultType: 'textfield',
                        layout: 'anchor',
                        defaults: {
                            anchor: '100%',
                            hideEmptyLabel: false
                        },
                        items: [{ //所属组织
                            xtype: 'treecombo',
                            name: 'institutionId',
                            fieldLabel: '所属组织',
                            emptyText: '请选择车辆所属组织',
                            rootVisible: false, //不显示root
                            store: Ext.create('Ext.data.TreeStore', {
                                root: {
                                    text: 'Root',
                                    id: '0'
                                },
                                proxy: {
                                    type: 'ajax',
                                    url: '/etruck-portal/web/institution/load',
                                    actionMethods: {
                                        read: "POST"
                                    },
                                    reader: {
                                        type: 'json'
                                    }
                                },
                                autoload: true
                            }),
                            allowBlank: false,
                        }, { //身份证号
                            name: 'citizenId',
                            fieldLabel: '身份证号',
                            emptyText: '',
                            vtype: 'uniqueIdentity',
                            allowBlank: false,
                            itemId: 'item-citizenId'
                        }, { //家庭住址
                            name: 'homeAddress',
                            fieldLabel: '家庭住址',
                            emptyText: '',
                        }, { //籍贯
                            name: 'hometown',
                            fieldLabel: '籍贯',
                            emptyText: '',
                        }, { //工号
                            name: 'workId',
                            fieldLabel: '工号',
                            emptyText: '',
                            allowBlank: false,
                            itemId: 'item-workId',
                            vtype: 'uniqueWorkId',
                        }, { //入职日期
                            xtype: 'datefield',
                            name: 'onboardDate',
                            fieldLabel: '入职日期',
                            emptyText: '',
                            value: new Date(), // defaults to today
                            allowBlank: false,
                            format: 'Y-m-d',
                        }, ],
                    }], //endOf 3
                }] //endof 2
        }, { //1
            xtype: 'fieldset',
            title: '证照信息',
            layout: 'anchor',
            defaults: {
                anchor: '100%'
            },
            collapsible: true,
            collapsed: false,
            items: [{ //1
                    xtype: 'fieldset',
                    title: '驾驶证信息',
                    layout: 'anchor',
                    defaults: {
                        anchor: '100%'
                    },
                    collapsible: true,
                    collapsed: false,
                    items: [{ //2
                            /*====================================================================
                             * Individual checkbox/radio examples
                             *====================================================================*/

                            // Using checkbox/radio groups will generally be more convenient and flexible than
                            // using individual checkbox and radio controls, but this shows that you can
                            // certainly do so if you only have a single control at a time.
                            xtype: 'container',
                            margin: '0 0 10',
                            layout: 'hbox',
                            items: [{ //3
                                xtype: 'container',
                                flex: 1,
                                defaultType: 'textfield',
                                layout: 'anchor',
                                defaults: {
                                    anchor: '100%',
                                    hideEmptyLabel: false
                                },
                                items: [{ //驾驶证号
                                    name: 'driverLicenseId',
                                    fieldLabel: '驾驶证号',
                                    emptyText: '',
                                    allowBlank: false,
                                }, { //驾驶证档案号
                                    name: 'driverLicenseProfileId',
                                    fieldLabel: '驾驶证档案号',
                                    emptyText: '',
                                    allowBlank: false,
                                }, { //从业类型,使用哪个url?
                                    xtype: 'combobox',
                                    name: 'employeeType',
                                    fieldLabel: '从业类型',
                                    emptyText: '',
                                    store: Ext.create('Ext.data.ArrayStore', {
                                        fields: ['display', 'value'],
                                        autoLoad: false,
                                        proxy: {
                                            type: 'ajax',
                                            url: '/etruck-portal/web/dictionary/getDictionaryByType?data=quali_cert_type',
                                            simpleSortMode: true,
                                            reader: {
                                                type: 'json',
                                                rootProperty: 'data',
                                            }
                                        }
                                    }),
                                    valueField: 'value',
                                    displayField: 'display',
                                    mode: 'remote',
                                    forceSelection: true,
                                    allowBlank: false,
                                }, { //从业资格证有效期
                                    xtype: 'datefield',
                                    name: 'qualificationCertificateExpireDate',
                                    fieldLabel: '从业资格证有效期',
                                    emptyText: '',
                                    format: 'Y-m-d',
                                    value: new Date(), // defaults to today
                                    allowBlank: false
                                }, { //从业资格发证机关
                                    name: 'issueQualificationCertificateDept',
                                    fieldLabel: '从业资格发证机关',
                                    emptyText: '',
                                    allowBlank: false
                                }],
                            }, { //第一行第二列
                                xtype: 'component',
                                width: 20
                            }, { //第一行第三列
                                xtype: 'container',
                                flex: 1,
                                defaultType: 'textfield',
                                layout: 'anchor',
                                defaults: {
                                    anchor: '100%',
                                    hideEmptyLabel: false
                                },
                                items: [{ //发证机关
                                    name: 'issueLicenseDept',
                                    fieldLabel: '发证机关',
                                    emptyText: '',
                                    allowBlank: false,
                                }, {
                                    xtype: 'datefield',
                                    name: 'driverLicenseExpireDate',
                                    fieldLabel: '驾驶证有效期',
                                    format: 'Y-m-d',
                                    emptyText: '',
                                    value: new Date(), // defaults to today
                                    allowBlank: false
                                }, { //准驾车型,使用哪个url?
                                    xtype: 'combobox',
                                    name: 'vehicleType',
                                    fieldLabel: '准驾车型',
                                    emptyText: '',
                                    store: Ext.create('Ext.data.ArrayStore', {
                                        fields: ['display', 'value'],
                                        autoLoad: false,
                                        proxy: {
                                            type: 'ajax',
                                            url: '/etruck-portal/web/dictionary/getDictionaryByType?data=approved_veh_type',
                                            simpleSortMode: true,
                                            reader: {
                                                type: 'json',
                                                rootProperty: 'data',
                                            }
                                        }
                                    }),
                                    valueField: 'value',
                                    displayField: 'display',
                                    mode: 'remote',
                                    forceSelection: true,
                                    allowBlank: false,
                                }, { //从业资格证号
                                    name: 'qualificationCertificateId',
                                    fieldLabel: '从业资格证号',
                                    emptyText: '',
                                    allowBlank: false,
                                }],
                            }], //endOf 3
                        }] //endof 2
                }] //endof 2
        }],
        dockedItems: [{
            xtype: 'toolbar',
            dock: 'bottom',
            items: ['->', {
                xtype: 'button',
                text: '保存',
                glyph: 0xf0c7,
                formBind: true,
                handler: 'save'
            }, {
                xtype: 'button',
                text: '取消',
                glyph: 0xf057,
                handler: 'close'
            }]
        }]
    }],
    initComponent: function() {
        this.callParent();
    }
});
