Ext.define('Line.Login',{
    extend:'Ext.panel.Panel',
    layout:'center',
    iitems: [
        {
            xtype: 'fieldset',
            title: 'Enter your name',
            items: [
                {
                    xtype: 'textfield',
                    label: 'First Name',
                    name: 'firstName'
                },
                {
                    xtype: 'textfield',
                    label: 'Last Name',
                    name: 'lastName'
                }
            ]
        }
    ]
});