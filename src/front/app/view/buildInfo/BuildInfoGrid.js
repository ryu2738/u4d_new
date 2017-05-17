Ext.define('Line.buildInfo.BuildInfoGrid',{
        extend:'Ext.grid.Panel',
        xtype:'buildInfo-grid',
        id:'buildInfo-grid',
        alias:'widget.buildInfoGrid',
        requires: ['Line.buildInfo.BuildInfoGridCntr','Ext.toolbar.Paging', 'Ext.grid.plugin.CellEditing'],
        plugins:'cellediting',

        store:{
            fields : [
                'qrSerno','surpntMthCode','insLatu','insLgtu','regDate'
            ],
            type:'linestore',
            id:'builidInfoStore',
            apiUrlId:'qrList'
        },
        //!! important
        controller:'buildInfoGridCntr',
        //!! important
        //viewModel:{
        //    type:'buildInfoList'
        //},
        columns : [
			{
				text : '관리번호',
                dataIndex : 'qrSerno',
                flex : 1
			},{
                text : '측량방식',
                dataIndex : 'surpntMthCode',
                flex : 1,
                editor: {
                    xtype: 'textfield'
                }
             },{
				text : '위도',
                flex : 1,
                dataIndex : 'insLatu',
                editor: {
                    xtype: 'textfield'
                }
			},{
				text : '경도',
                flex : 1,
                dataIndex : 'insLgtu',
                editor: {
                    xtype: 'textfield'
                }
			},{
                text:'regDate',
                flex:1,
                 renderer: function (value, metaData, record, row, col, store, gridView) {
                    return Line.Date.dateFormat(record.get('regDate'));
				 }
            }
        ],
        bbar: {
            xtype: 'pagingtoolbar',
            displayInfo: true,
            displayMsg: 'Displaying topics {0} - {1} of {2}',
            emptyMsg: "No topics to display"
        },
        tbar:[{
                text: '추가',
                itemId : 'add',
                iconCls: 'icon-add',
                handler: 'onAddRow',

            }, '-', {
                text: '삭제',
                itemId: 'delete',
                iconCls: 'icon-delete',
                handler: 'onDeleteRow'
            },{
                text: '저장',
                itemId: 'save',
                iconCls: 'icon-save',
                handler: 'onSave'
        }],
        listeners:{
            //!! important
            boxready :'onBoxReady'
        }
})