Ext.define('Line.pipelinemap.MapLayer', {
    alias:'widget.mapLayer',
    id:'mainMapLayer',
    map:null,
    mapLayerInfo:null,
    extent:null,
    zoomLevel:null,
    constructor : function (config){
        var me=this;
        //셋팅 정보 로딩
        me.loadConfigData(config);      
        // 맵생성
        me.map=me.createMap(config.mapId);
        // 레이어 생성(geoserver:WMS레이어)
        me.createLayers();
        // 벡터레이어 생성(openlayer)
        me.createVectorLayer('zoomLevelLayer');

    },    
    loadConfigData:function(config){
        var me=this;
        Ext.Ajax.request({
                    url : 'http://localhost:8080/json/setting.json',
                    method : 'GET',
                    async:false,
                    success : function(response) {
                        var result = Ext.JSON.decode(response.responseText);
                        me.mapLayerInfo=result.mapLayer;
                        me.extent=result.extent;
                        me.zoomLevel=result.zoomLevel;
                    },
                    failure : function(){
                        alert("에러발생");
                    }
                });
    },
    
    createMap:function(buildMapId){
        var me=this;       
        //var extent=me.gExtent;
	    var minX=me.extent.minX;
	    var minY=me.extent.minY;
	    var maxX=me.extent.maxX;
	    var maxY=me.extent.maxY;
	    
        var centerX=(minX+maxX)/2; 
	    var centerY=(minY+maxY)/2; 
	    
	    var mapExtent=[minX, minY, maxX, maxY];
	    var mainMap=new ol.Map(me.getCfgMap(buildMapId,mapExtent,[centerX, centerY]));
        //center: new ol.geom.Point(centerPoint).getCoordinates(),
        return mainMap;
	},
   
    createLayers:function(){
        var me=this;
        var layerNms=me.mapLayerInfo;
        // 배경지도
        me.map.addLayer(
            new ol.layer.Tile({
            layerId:'baseMapLayer',
            source: new ol.source.TileWMS({
                url : layerNms.baseUrl,
                params: {'LAYERS': layerNms.baseLayer, 'TILED': true},
                serverType: 'geoserver'
                })
            })
        );

        me.map.addLayer(me.createWMSLayer(layerNms.baseUrl, layerNms.zoneLayer));
        me.map.addLayer(me.createWMSLayer(layerNms.baseUrl, layerNms.linkLayer));
        me.map.addLayer(me.createWMSLayer(layerNms.baseUrl, layerNms.qrLayer));    
        
    },
    createWMSLayer:function(baseUrl,layerNm){
	    var me=this;
	    var wmsLayer =new ol.layer.Tile({
            layerId:layerNm,
	        source: new ol.source.TileWMS({
	            preload: Infinity,
	            url:baseUrl,
	            serverType:'geoserver',
	            params:{
	                'LAYERS':layerNm, 'TILED':true
	            }
	        })
	    });
	    return wmsLayer;
	},
    createVectorLayer:function(layerId){
        var me=this;
        // 설정한 Zoom레벨에따라 피처를 추가하는 레이어
       var  zoomLevelLayer=new ol.layer.Vector({
            layerId:layerId,
            source: new ol.source.Vector({})
      })
      me.map.addLayer(zoomLevelLayer);    
    },
    getLayerById:function(layerId){
        var me=this;
        var tmpLys=me.getLayers();
        var retLayer=null;
        tmpLys.forEach(function(lyr, cfg){
            if(lyr.get('layerId')===layerId){
                retLayer=lyr;
            }
        })
        return retLayer;
    },
    getBaseMapView:function(){
        var me=this;
        return me.map.getView();
    },
    getLayers:function(){
        var me=this;
        return me.map.getLayers();
    },
    removeAndAddFeatureOnVectorlayer:function(layerId, features){
        var me=this;
        me.removeAllFeatureOnVectorLayer(layerId);
        me.addFeatureOnVectorLayer(layerId,features);

    },
    addFeatureOnVectorLayer:function(layerId, features){
        var me=this;
        var layer=me.getLayerById(layerId);
        layer.getSource().addFeatures(features);
    },
    removeAllFeatureOnVectorLayer:function(layerId){
        var me=this;
        var layer=me.getLayerById(layerId);
        layer.getSource().clear();
    },
    getCfgMap:function(buildMapId,mapExtent,centerPoint){
        return {
	        target: buildMapId,
            logo:false,//우측하단 로고이미지
            // logo:{src:'test',
            //     href:'#'
            //     },
	        controls: ol.control.defaults({
	            attributionOptions: ({
	                collapsible: false
	            })
	        }).extend([
	            new ol.control.ZoomSlider(),
	            new ol.control.ZoomToExtent({
	                extent: mapExtent
	            }),
	            new ol.control.Rotate(),
	            new ol.control.OverviewMap(),
	            new ol.control.ScaleLine(),
	            new ol.control.FullScreen(),
	            new ol.control.MousePosition({
	                coordinateFormat: ol.coordinate.createStringXY(8)
	            })
	        ]),
	        interactions: ol.interaction.defaults().extend([
	          
	            new ol.interaction.DragRotate({condition:ol.events.condition.altShiftKeysOnly}),
	            new ol.interaction.DragZoom({condition:ol.events.condition.altKeyOnly})
	        ]),
	        view: new ol.View({
	            projection: 'EPSG:4326',
	            center: new ol.geom.Point(centerPoint).getCoordinates(),
	            zoom: 20
	        })
	    }    
        
    }

    
});