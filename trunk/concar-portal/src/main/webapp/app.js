/*
 * This file is generated and updated by Sencha Cmd. You can edit this file as
 * needed for your application, but these edits will have to be merged by
 * Sencha Cmd when upgrading.
 */
Ext.application({
    name: 'BAICClassicApp',

    extend: 'BAICClassicApp.Application',

    requires: [
        'BAICClassicApp.view.main.Main'
    ],

    // The name of the initial view to create. With the classic toolkit this class
    // will gain a "viewport" plugin if it does not extend Ext.Viewport. With the
    // modern toolkit, the main view will be added to the Viewport.

    /**
     * 此处隐藏，否则会自动创建main
     */
    // mainView: 'BAICClassicApp.view.main.Main'
    /**
     * 在6官方例子里面不用这个方法，用了会出现找不到Root的class错误，暂不深究
     */
    // controllers: ['Root'],

    //-------------------------------------------------------------------------
    // Most customizations should be made to BAICClassicApp.Application. If you need to
    // customize this file, doing so below this section reduces the likelihood
    // of merge conflicts when upgrading to new versions of Sencha Cmd.
    //-------------------------------------------------------------------------
});
