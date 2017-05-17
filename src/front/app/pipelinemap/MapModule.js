Ext.define('Line.pipelinemap.MapModule', {
    extend: 'Ext.ux.desktop.Module',
    id:'map-module',
   
     init : function(){
        // 시작 메뉴명
        this.launcher = {
           text : '관망도',
		    iconCls : 'pipeline-icon'
        };
    },

    createWindow : function(){
        var me=this;
        var desktop = me.app.getDesktop();
        var win = desktop.getWindow('map-window');
        if(!win){
            win = desktop.createWindow(me.cfgWindow);
            win.width = desktop.body.getWidth();
            win.height=  desktop.body.getHeight();            
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
        var win = Ext.getCmp('map-window');
        win.mapView=Ext.create("Line.pipelinemap.MapView",{mapId:'build-map'});
    },
    resizeMap:function(){
        var me=this;
        var win = Ext.getCmp('map-window');
        
        if(typeof win.mapView !=='undefined'){
            var viewSize=Ext.getCmp('map-panel').getSize();
            win.mapView.getMap().setSize([viewSize.width,viewSize.height]);
        }
    },
    cfgWindow:{
        id: 'map-window',
        title:'관망도',

        iconCls: 'pipeline-icon',
        animCollapse:false,
        constrainHeader:true,
        layout: 'border',
        /*layout: {
                type: 'border',
                padding: 5
            },*/
        tools:[{
                type:'refresh',
                tooltip: '초기화'
                /*handler: function(event, toolEl, panelHeader) {                        	
                    me.mapView.initMapViewEvent();      
                    me.mapView.refreshLayer();      
                }*/
            }],
        items:[/*{
                region:'north',
                margin : '0 0 0 0',
                xtype:'panel',
                layout : 'hbox',
                items:[{
                    xtype:'button',
                    text:'test'
                },{
                    xtype:'button',
                    text:'test2'
                }]
            },*/{
                region:'center',
                margin : '0 0 0 0',
                xtype:'panel',
                header:false,
                id:'map-panel',
               // layout : 'fit',
                html:"<div id='build-map' class='map' style='height:100%'></div>"+
                        "<div id='popup' class='map-popup'>"+
                            "<a href='#' id='popup-closer' class='map-popup-closer'></a>"+
                        "<div id='popup-content' class='map-popup-content'></div>"+
                        "</div>"
            }]
        /*tbar:[{
            text:'Add Something',
            tooltip:'Add a new row',
            iconCls:'add'
        }, '-', {
            text:'Options',
            tooltip:'Modify options',
            iconCls:'option'
        },'-',{
            text:'Remove Something',
            tooltip:'Remove the selected item',
            iconCls:'remove'
        }]*/
    }
});

