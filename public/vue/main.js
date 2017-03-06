/**
 * Created by admin on 2017-2-24.
 */
var app = new Vue({
    el: '#app',
    data: {
        data_main: []
    },
    methods: {
        add: function (i) {
            if (app.data_main[i].show_commit == false && app.data_main[i].flag_first == false) {
                app.data_main[i].show_commit = true;
                app.data_main[i].flag_first = false;
                //console.info(app.data_main[i].id);
                //发送请求获取评论
                var jsonComment = {};
                jsonComment.id = app.data_main[i].id;
                $.post('/get_Old_Comment', jsonComment, function (res) {
                    var rr = $.parseJSON(res);
                    app.data_main[i].commit = rr.pic_comment1;
                })//post
            }//if

            else if (app.data_main[i].show_commit == false && app.data_main[i].flag_first == true) {
                app.data_main[i].show_commit = true;
            }
            else {
                app.data_main[i].show_commit = false;
            }
        },

        heart: function (i) {
            if (app.data_main[i].class_heart == false) {
                app.data_main[i].class_heart = true;
            }
            else {
                app.data_main[i].class_heart = false;
            }
        }

    }
})

function load() {

    var jsonData = {};
    var order = 1;
    var num = 10;
    var r;

    jsonData.order = order;
    jsonData.num = num;
    $.post('/get_Old_Data', jsonData, function (res) {
        // process response
        r = $.parseJSON(res);
        for (var i = 0; i < r.data.length; i++) {
            r.data[i].show_commit = false;
            r.data[i].class_heart = false;
            r.data[i].flag_first = false;
            r.data[i].commit = [];
            app.data_main.push(r.data[i]);
        }
    })
}