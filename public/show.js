/**
 * Created by admin on 2016/8/9.
 */
//��ȡID
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);  //��ȡurl��"?"������ַ���������ƥ��
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}

//ҳ��load�¼�
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