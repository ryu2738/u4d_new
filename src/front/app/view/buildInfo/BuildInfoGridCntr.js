Ext.define('Line.buildInfo.BuildInfoGridCntr',{
    extend:'Ext.app.ViewController',
    alias:'controller.buildInfoGridCntr',
    onSearch:function(obj,selObj){
        var me=this;
        var store=obj.up('grid').getStore();//builidInfoStore
        store.load()
    },
    onAddRow:function(obj, eOpts){
        var me=this;

        var store=obj.up('grid').getStore();//builidInfoStore
        var newData={
            surpntMthCode:'',
            insLatu:'',
            insLgtu:'',
            regDate:'2016.11.13'
        }
        store.insert(0, newData);
        //rowEditing.startEdit(0, 0);

    },
    onBoxReady:function(obj , eOpts){
        //console.log(obj, this);
        var me=this;
        var store=obj.getStore();//builidInfoStore
        store.findAll();

    },
    onDeleteRow: function(obj , eOpts){
        var me=this;
        var store=obj.up('grid').getStore();//builidInfoStore

        var grid=me.getView(); //Line.buildInfo.BuildInfoGrid
       // var selection = grid.getView().getSelectionModel().getSelection()[0];
        var selection =grid.getSelection();
        store.remove(selection);
        /*if (selection) {
             store.delete(selection);
        }*/
    },
    onSave:function(obj , eOpts) {
        var me = this;
        var store=obj.up('grid').getStore();//builidInfoStore
        // 서버로 전송
        store.sync({
            callback: function (records, operation, success) {
                console.log('callback');
                store.reload()
            },
            success: function (batch, options) {
                console.log('success');
            },
            failure: function (batch, options) {
                console.log('failure');

            }
        })
    }

})