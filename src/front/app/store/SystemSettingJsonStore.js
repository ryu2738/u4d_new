
Ext.define('Line.data.Store',{
     extend: 'Ext.data.Store',
     alias: 'store.linestore',
     constructor : function (arguments){         
         var me=this;
         arguments=arguments||{};
         me.callParent(arguments);
         me.setApiUrl(arguments.apiUrlId);


      },
    pageSize: 50,
    //jsonRootProperty:null,
     save:function(entity){
         var me=this;
     },
     findOne:function(pk){
         console.log(pk);
     },
     findAll:function(){
         var me=this;
         console.log(me.proxy);
         me.load();
     },
     count:function(){
        console.log(this);
     },
     delete:function(entity){
         var me=this;
         me.remove(entity);     
         me.sync(); 
         // TODO reload도록 수정(store 내에서 처리) 
         me.reload();
     },
     exists:function(pk){
        console.log(this); 
     },
     setApiUrl:function(id, actionMethod){
        var me=this;
        me.proxy.setApi(LineGlobal.getApi(id));
     },
    listeners:{
        beforeload :function( store , operation , eOpts ){
            operation.setPage(operation.getPage()-1);  // 스프링 페이징과 연계를 위해 -1
        }
    },
     proxy: {
            type: 'rest',
             reader: {
                type: 'json',
                rootProperty: 'content',
                totalProperty:'totalElements'
            },
            writer:{
              type:'json',
               writeAllFields:true
            },
            extraParams:{
                size:50
            },
            listeners:{
                exception :function ( proxy , request , operation , eOpts ) {
                    Ext.Msg.alert('test', operation.error.statusText);

                }
            }
        }

});

Ext.define('Line.store.ShortcutStore', {
	extend : 'Ext.data.Store',
	model: 'Line.desktop.LineModel',
    autoLoad: true,
    proxy: {
        type: 'ajax',                    
        url : 'http://localhost:8080/json/setting.json',
        reader: {
            type: 'json',
            rootProperty: 'shortcut'
        }
    }
});

Ext.define('Line.store.MapLayerStore', {
	extend : 'Ext.data.Store',
    autoLoad: true,
    model: 'Line.desktop.MapModel', 
    proxy: {
        type: 'ajax',
        async:false,
        method:'GET',
        url : 'http://localhost:8080/json/setting.json',
        reader: {
            type: 'json',
            rootProperty: 'mapLayer'
        }
    }
});


Ext.define('Line.store.MapExtentStore', {
	extend : 'Ext.data.Store',
    autoLoad: true,
    model: 'Line.desktop.MapModel', 
    proxy: {
        type: 'ajax',
        async:false,
        method:'GET',
        url : 'http://localhost:8080/json/setting.json',
        reader: {
            type: 'json',
            rootProperty: 'extent'
        }
    }
});



Ext.define('Line.store.ApiStore', {
	extend : 'Ext.data.Store',
    autoLoad: true,
    asynchronousLoad:false,
    //model: 'Line.desktop.MapModel', 
    proxy: {
        type: 'rest',
        async:false,
        method:'GET',
        url : 'http://localhost:8080/json/setting.json',
        reader: {
            type: 'json',
            rootProperty: 'apiUrls'
        },
        writer: {
            type: 'json'
        }
    },
    listeners:{
        load:function(obj ,records,successful,operation,eOpts){
            console.log(obj);
            LineGlobal.setApiUrls(obj.getData());

        }
    }
});


