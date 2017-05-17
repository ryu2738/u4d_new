/*!
 * Ext JS Library
 * Copyright(c) 2006-2014 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

/**
 * @class Ext.ux.desktop.ShortcutModel
 * @extends Ext.data.Model
 * This model defines the minimal set of fields for desktop shortcuts.
 */
Ext.define('Line.desktop.LineModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'name'}, 
        {name: 'iconCls'}, 
        {name: 'module'}, 
        {name: 'value'}
    ]
});

Ext.define('Line.desktop.MapModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'baseUrl'}, 
        {name: 'baseLayer'}, 
        {name: 'qrLayer'}, 
        {name: 'linkLayer'}, 
        {name: 'accLayer'}, 
        {name: 'zoneLayer'}
    ]
});

Ext.define('Line.global',{
    singleton:true,
    alternateClassName: 'LineGlobal',
    apiUrls:null,
    setApiUrls:function(paramApiUrls){
        var me=this;
        me.apiUrls=paramApiUrls;
    },
    getApi:function(id){
        var me=this;
        return me.apiUrls.get(id).getData().url;
    }

})