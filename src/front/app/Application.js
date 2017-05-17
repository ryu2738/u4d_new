/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('Line.Application', {
    extend: 'Ext.app.Application',
    
    name: 'Line',
    appProperty:'lineApp',
    stores: [
        // TODO: add global / shared stores here
    ],
    controllers : [		
		//'Line.pipelinemap.dataUpload.DataUploadController'
	],
    launch: function () {
        // TODO - Launch the application
        //Ext.create('Line.desktop.DesktopApp');
        //Ext.create('Line.Login');
    },

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
