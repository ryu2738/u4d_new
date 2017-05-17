Ext.define('Line.pipelinemap.MapView', {
    extend:'Ext.panel.Panel',
    requires:['Line.pipelinemap.MapLayer'],
    alias:'widget.mapView',
    mapId:null,
    mapLayer:null,    
    constructor: function(config){
        var me=this;
        me.mapId=config.mapId
        me.initMap();
    },    
    initMap:function(){
        var me=this;
        me.mapLayer=Ext.create('Line.pipelinemap.MapLayer',{mapId:me.mapId});        
        var map=me.mapLayer.map;
        map.on('moveend',me.onMoveEnd)
    },
    getMap:function(){
        var me=this;
        return me.mapLayer.map;
    },
    onMoveEnd:function(evt){
        console.log('onMoveEnd');
        var evtTarget=this;      
        var win = Ext.getCmp('map-window');
        var me=win.mapView;
        var configZoom=me.mapLayer.zoomLevel;
        var currentZoom=evt.target.getView().getZoom();
        if(currentZoom>=configZoom){ 
            me.addFeatureOnZoomLeveLayer();
        }else{
            //zoom Layer 피쳐 삭제
            me.mapLayer.removeAllFeatureOnVectorLayer('zoomLevelLayer');
        }
    },
    addFeatureOnZoomLeveLayer:function(){
        var win = Ext.getCmp('map-window');
        var me=win.mapView;
        var view=me.mapLayer.getBaseMapView();
        var currentExtent=view.calculateExtent(me.mapLayer.map.getSize());

        var southWestCoord=ol.proj.transform([currentExtent[0],currentExtent[1]], 'EPSG:4326','EPSG:3857');
        var eastNorthCoord=ol.proj.transform([currentExtent[2],currentExtent[3]], 'EPSG:4326','EPSG:3857');

        var extentString=southWestCoord[0]+','+southWestCoord[1]+','+eastNorthCoord[0]+','+eastNorthCoord[1];

        var baseUrl=me.mapLayer.mapLayerInfo.baseUrl;
        var qrLayer=me.mapLayer.mapLayerInfo.qrLayer;
        var linkLayer=me.mapLayer.mapLayerInfo.linkLayer;
        
        var defaultParameters = {
            service: 'WFS',
            version: '1.0.0',
            request: 'GetFeature',
            typeName: qrLayer,
            maxFeatures: 200,
            outputFormat: 'text/javascript',
            format_options: 'callback:callBakcGetJson',
            srsName:'EPSG:4326',
            bbox:extentString

        };

        $.ajax({
            jsonp : false,
            url: baseUrl,
            data:defaultParameters,
            dataType: 'jsonp',
            jsonpCallback:'callBakcGetJson',
            success: function(response){
                var win = Ext.getCmp('map-window');
                var mapLayers=win.mapView.mapLayer;
                var features= (new ol.format.GeoJSON()).readFeatures(response);
                mapLayers.removeAndAddFeatureOnVectorlayer('zoomLevelLayer', features);
            }
        });   
    }
});

