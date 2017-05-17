var Line = Line || {};

// Line.Date = (function () {
//     var utilDate;
//     utilDate={   
//         dateFormat:function (orgDate, format){
//             var dtFormat = format || 'Y.m.d H:i';
//             return Ext.Date.format(new Date(orgDate), dtFormat);    
//         }
//     }
//     return utilDate;
// }());

Line.Date = {
    dateFormat:function (orgDate, format){
        var dtFormat = format || 'Y.m.d H:i';
        return Ext.Date.format(new Date(orgDate), dtFormat);    
    }
}
