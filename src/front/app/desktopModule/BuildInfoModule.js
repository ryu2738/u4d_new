Ext.define('Line.buildInfo.BuildInfoModule', {
    extend: 'Ext.ux.desktop.Module',
    id:'buildInfo-module',
   
     init : function(){
        // 시작 메뉴명
        this.launcher = {
           text : '시공정보',
		    iconCls : 'build-icon'
        };
    },

    createWindow : function(){
        var me=this;
        var desktop = me.app.getDesktop();
        var win = desktop.getWindow('buildInfo-window');
        if(!win){
            win = desktop.createWindow(me.cfgWindow);
            win.width = desktop.body.getWidth()-10;
            win.height=  desktop.body.getHeight()-10;            
            win.on({
                resize:me.resizeMap,
                show:me.initMap,
                scope:me
                });
        }

        return win;
    },
    initMap:function(){
        var me=this;
    },
    resizeMap:function(){
        var me=this;
    },
    cfgWindow:{
        id: 'buildInfo-window',
        title:'시공정보',

        iconCls: 'build-icon',
        animCollapse:true,
        constrainHeader:true,
        layout: 'border',        
        tools:[{
                type:'refresh',
                tooltip: '초기화'
            }],        items:[{
                region:'center',
                margin : '0 0 0 0',
                header:false,
                xtype:'buildInfo-grid'
                //id:'buildInfo-panel',
               // items:'buildInfo-panel'
            }]
    }
});

