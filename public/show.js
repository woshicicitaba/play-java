/**
 * Created by admin on 2016/8/9.
 */
//截取ID
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}

//页面load事件
var a;
function load() {
    var jsons = {};
    jsons.id = GetQueryString("i");
    $.ajax({
        url: '/showPic',
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(jsons),
        success: function (data) {
            var nDiv = "<img src=" + data.data[0].url + "/>";
            $("#row").append(nDiv);
            // a=data;
            //console.info(a);
        },
        dataType: 'JSON',
        type: 'post'
    });
}