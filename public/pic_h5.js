/**
 * Created by admin on 2016/8/8.
 */
var n = 1;//���ӵ�div������
var m = 0;//���ӵ�div�Ĵ���
var j = 1;//ɾ��div������
var r;//��ȡjson��ֵ

function add(id, u, v) {

    var nDiv = "<div onclick='tiaozhuan(" + id + ")'><img id="+id+" src=" + u + " style='width:500px'></div>";
    $("#row").append(nDiv);
    n++;
}

//����3���Ժ󣬿�ʼɾ��
function remove() {
    if (m > 3) {
        for (var i = 1; i <= 5; i++) {
            var id = "#" + j;
            $(id).remove();
            j++;
        }
    }
}

//window.onscroll = function () {
//    if ($("#oBody").height() - $("#oBody").scrollTop() <= $(window).height()) {
//        load();
//    }
//    //remove();
//}

function load() {
    console.info(m);
    $.get('/getJson', function (res) {
            //console.info(res);
            r = $.parseJSON(res).data;
            for (var ii = 0; ii < r.length; ii++) {
                // console.info(r[ii].url);
                add(r[ii].id, r[ii].url, r[ii].value);
            }
            m++;
            //console.info(m);
        }
    )
}

function tiaozhuan(id) {
    location.href = "show.html?i=" + id;//����txt���������
}

function getDt() {
    $.get('/getData', function () {
        console.info('end');
    })
}


function getWd() {
    $.get('/getWord', function () {
        console.info('end');
    })
}

function showPoc() {
    $.get('/showPic_test', function () {
        console.info('end');
    })
}
