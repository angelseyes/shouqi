Ext.define('BAICClassicApp.model.Base', {
    extend: 'Ext.data.Model',
    requires: ['Ext.data.proxy.Rest', ],
    fields: [{
        name: 'id',
        type: 'int'
    }],
    schema: {
        namespace: 'BAICClassicApp.model',
        proxy: {
            type: 'ajax',
            reader: {
                type: 'json'
            }
        }
    }
});
