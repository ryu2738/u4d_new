/*
 * This file is generated and updated by Sencha Cmd. You can edit this file as
 * needed for your application, but these edits will have to be merged by
 * Sencha Cmd when upgrading.
 */
Ext.application({
    name: 'Line',

    extend: 'Line.Application',
    /*
    requires: [
        'Line.App'
    ],
    */
    init: function() {
        var apiStore=Ext.create('Line.store.ApiStore');
    },
    autoCreateViewport : 'Line.desktop.DesktopApp'
});


