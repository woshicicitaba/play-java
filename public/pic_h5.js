/**
 * Created by admin on 2016/8/8.
 */
var n = 1;//���ӵ�div������
var m = 0;//���ӵ�div�Ĵ���
var j = 1;//ɾ��div������
var r;//��ȡjson��ֵ

function add(id, u, v) {
    var nDiv = "<div class='col-xs-12' style='border:1px solid red;'" + "id=" + id + " onclick='tiaozhuan(" + id + ")'><img id='img1' src=" + u + " alt='bigai' class='img-responsive center-block img-rounded'> <div class=��caption��><h3>" + v + "</h3> </div> </div>";
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

window.onscroll = function () {
    if ($("#oBody").height() - $("#oBody").scrollTop() <= $(window).height()) {
        load();
    }
    //remove();
}

function load() {
    $.get('/getJson', function (res) {
            //console.info(res);
            r = $.parseJSON(res).data;
            for (var ii = 0; ii < r.length; ii++) {
                // console.info(r[ii].url);
                add(r[ii].id, r[ii].url, r[ii].value);
            }
            m++;
            console.info(m);
        }
    )
}

function tiaozhuan(id) {
    location.href = "show.html?i=" + id;//����txt���������
}

function getDt() {
    alert('begin');
    $.get('/getData', function () {
        console.info('end');
    })
}