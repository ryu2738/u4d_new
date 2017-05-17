Ext.define('Line.common.FileDownload', {
	extend : 'Ext.Component',
	//id : 'FileDownload',
	alias : 'widget.filedownload',
	autoEl : {
		tag : 'iframe',
		cls : 'x-hidden',
		src : Ext.SSL_SECURE_URL
	},
	load : function(config) {
		var e = this.getEl();

		e.dom.src = config.url + (config.params ? '?' + Ext.urlEncode(config.params) : '');
		e.dom.onload = function() {
			if (e.dom.contentDocument.body.childNodes[0].wholeText == '404') {
				Ext.Msg.show({
					title : '에러',
					msg : '파일을 찾을 수 없습니다.',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				})
			}
		}
	}
});