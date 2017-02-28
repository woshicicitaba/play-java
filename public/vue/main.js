/**
 * Created by admin on 2017-2-24.
 */
var app_main = new Vue({
    el: '#app',
    data: {data_main:{}},
    //methods: {
    //    add: function () {
    //        var r;
    //        var jsonData = {};
    //        var order = 1;
    //        var num = 10;
    //
    //        jsonData.order = order;
    //        jsonData.num = num;
    //        $.post('/get_Old_Data', jsonData, function (res) {
    //            // process response
    //            r = $.parseJSON(res);
    //            app_main.data_main = r.data;
    //        })
    //    }
    //}

})

var r;
function add() {

    var jsonData = {};
    var order = 1;
    var num = 10;

    jsonData.order = order;
    jsonData.num = num;
    $.post('/get_Old_Data', jsonData, function (res) {
        // process response
        r = $.parseJSON(res);
        app_main.data_main = r.data;
    })
}