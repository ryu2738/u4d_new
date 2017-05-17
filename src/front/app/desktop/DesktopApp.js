/*
 * ! Ext JS Library Copyright(c) 2006-2014 Sencha Inc. licensing@sencha.com http://www.sencha.com/license
 */
Ext.define('Line.desktop.DesktopApp', {
    extend: 'Ext.ux.desktop.App',
    requires: [
        'Ext.window.MessageBox',
        'Ext.layout.container.boxOverflow.Menu',
        'Desktop.VideoWindow',
        'Desktop.BogusMenuModule',
        'Desktop.BogusModule',
        'Desktop.Settings',
		'Line.desktop.LineModel',
		'Line.pipelinemap.MapModule',
        'Line.buildInfo.BuildInfoModule'
    ],

    init: function() {
        // custom logic before getXYZ methods get called...

        this.callParent();

        // now ready...
    },

    getModules : function(){
        return [
            new Desktop.VideoWindow(),
            new Desktop.BogusMenuModule(),
            new Desktop.BogusModule(),
			new Line.pipelinemap.MapModule(),
            new Line.buildInfo.BuildInfoModule()
        ];
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {

            contextMenuItems: [
                { text: 'Change Settings', handler: me.onSettings, scope: me }
            ],

            shortcuts: Ext.create('Line.store.ShortcutStore'),

            wallpaper: 'resources/images/wallpapers/samsung.jpg',
            wallpaperStretch: true
        });
    },

    // config for the start menu
    getStartConfig : function() {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            title: 'Don Griffin',
            iconCls: 'user',
            height: 300,
            toolConfig: {
                width: 100,
                items: [
                    {
                        text:'Settings',
                        iconCls:'settings',
                        handler: me.onSettings,
                        scope: me
                    },
                    '-',
                    {
                        text:'Logout',
                        iconCls:'logout',
                        handler: me.onLogout,
                        scope: me
                    }
                ]
            }
        });
    },

    getTaskbarConfig: function () {
        var ret = this.callParent();

        return Ext.apply(ret, {
            quickStart: [
                { name: '관망도', iconCls: 'icon-grid', module: 'map-module' },
                 { name: '관망도', iconCls: 'icon-grid', module: 'map-module' }
            ],
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('Logout', 'Are you sure you want to logout?');
    },

    onSettings: function () {
        var dlg = new Desktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});
