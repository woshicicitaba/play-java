/**
 * Created by admin on 2016-9-14.
 */
var id = 1;
var r;
var jsonData={};

function up() {
    id++;
    jsonData.id=id;
    jsonData.like=1;
    jsonData.dislike=0;
    get_data();
}

function down() {
    id++;
    jsonData.id=id;
    jsonData.like=0;
    jsonData.dislike=1;
    get_data();
}

function up_pic(url) {
    var url_pic = "url(" + url + ")";
    $('#main').css('background-image', url_pic);
    console.info(url_pic);
}

function get_data() {
    $.post('/get_New_Data', jsonData, function (res) {
        // process response
        r = $.parseJSON(res);
        var url= r.data[0].url;
        console.info(url);
        up_pic(url);
    })
}
