/**
 * Created by admin on 2017-2-24.
 */

var order = 1;
var num = 10;
var add_flag = true;

var app = new Vue({
    el: '#app',
    data: {
        data_main: [],
        button_flag_open: false,
        button_flag_dialogue: false,
        message: ''
    },
    ready: function () {
        //这里是vue初始化完成后执行的函数
        window.addEventListener('scroll', this.handleScroll);
    },
    methods: {
        add_commit: function (i) {
            if (app.data_main[i].show_commit == false && app.data_main[i].flag_first == false) {
                app.data_main[i].show_commit = true;
                app.data_main[i].flag_first = false;
                //console.info(app.data_main[i].id);
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
        },

        add_pic: function () {
            var jsonData = {};
            var r;
            order = order + 10;
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
                setTimeout(function () {
                    add_flag = true;
                }, 3000);
            })
        },

        show_detail: function () {
            //console.info(this.button_flag_open);
            if (this.button_flag_open == false) {
                this.button_flag_open = true;
            }
            else {
                this.button_flag_open = false;
            }
        },

        show_dialogue: function () {
            //console.info(this.button_flag_open);
            if (this.button_flag_dialogue == false) {
                this.button_flag_dialogue = true;
            }
            else {
                this.button_flag_dialogue = false;
            }
        },

        show_commit_area: function (i) {
            app.data_main[i].submit_commit = true;
        },

        submit_commit: function (index) {
            //alert(this.message);
            //alert(app.data_main[index].id);
            var jsonData = {};
            var r;
            jsonData.id = app.data_main[index].id;
            jsonData.value = this.message;
            $.post('/submit_comment', jsonData, function (res) {
                // process response
                r = $.parseJSON(res);
                //r.pic_comment1[0].floor = '新进X';
                app.data_main[index].commit.unshift(r.pic_comment1[0]);
                //console.info(r.pic_comment1[0]);
            });
            this.message = null;
        },

        handleScroll: function () {
            var scrollTop = $(window).scrollTop();
            var scrollHeight = $(document).height();
            var windowHeight = $(window).height();
            //console.info(scrollTop + '$$' + scrollHeight + '$$' + windowHeight);
            //console.info((scrollTop + windowHeight + 30) == (scrollHeight));
            //console.info((scrollTop + windowHeight + 30));
            //console.info((scrollHeight));
            console.info(add_flag);
            if (((scrollTop + windowHeight + 50) > (scrollHeight)) && (add_flag == true)) {
                //alert('a');
                //console.info((scrollTop + windowHeight+30) + '$$' + (scrollHeight) + '$$' +(scrollTop + windowHeight+30-scrollHeight) )
                add_flag = false;
                this.add_pic();
            }
        }
    }
})

function load() {

    var jsonData = {};
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
            r.data[i].submit_commit = false;
            app.data_main.push(r.data[i]);
        }
    })
}