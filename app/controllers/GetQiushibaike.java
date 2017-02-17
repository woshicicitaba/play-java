package controllers;

import models.dataBase;
import models.dataComment;
import models.picData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import play.mvc.Controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Timer;

/**
 * Created by admin on 2016-10-8.
 */
public class GetQiushibaike extends Controller {

    public play.mvc.Result getWord() throws IOException {
        //添加定时器代码
        Timer timer = new Timer();
        MyTask myTask1 = new MyTask();
        timer.schedule(myTask1, 1000, 10800000);//10800000
        return ok();
    }

    public void get_word_main() throws IOException {
//        play.Logger.info("aaaaa");

        int page_num = 1;//
        for (int j = 1; j <= 1; j++) {
            String url = "http://www.qiushibaike.com/imgrank/page/" + page_num + "/";
            Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
            org.jsoup.select.Elements elements1 = doc.select("div.article");

            play.Logger.info(String.valueOf(url));

            for (org.jsoup.nodes.Element e : elements1) {
                String id = String.valueOf(e.id());
                String ppData = String.valueOf(e.select("div.content").text());
                org.jsoup.select.Elements elements2 = e.getElementsByClass("thumb");
                org.jsoup.select.Elements elements3 = elements2.select("img");
                String ppurl = elements3.attr("src");

                org.jsoup.select.Elements elements4 = e.getElementsByClass("qiushi_comments");
                String hrf = elements4.attr("data-share");

//                play.Logger.info("elements4:" + elements4);
//                play.Logger.info("hrf:" + hrf);

                String[] strarray = ppurl.split("/");
                String pSrc = strarray[strarray.length - 1];

                play.Logger.info("data:" + ppData);
                play.Logger.info("id:" + id);
                play.Logger.info("url:" + pSrc);

                if ("Has_None" == judgeExist(id)) {
                    if (ppurl != null) {
                        downLoadFromUrl(ppurl, pSrc, "E:/IdeaProjects/play-java/public/picc");

                        insertPicNews(ppData, id, pSrc);
                        get_comment(hrf, id);
                    } else {
                        play.Logger.info("ppurl is null" + id);
                    }
                } else {
                    play.Logger.info("存在" + id);
                }
            }
            page_num++;
        }
    }

    //数据库插值
    public void insertPicNews(String Date, String Id, String Alt) {

        String new_alt = "picc/" + Alt;

        picData date_pic = new picData();
        date_pic.setType_pic("Qiushibaike");
        date_pic.setValue(Date);
        date_pic.setUrl(new_alt);
        date_pic.setSource_id(Id);
        date_pic.setLike_num((long) 0);
        date_pic.setLike_num((long) 0);
        date_pic.insert();

    }

    //判断是否已经存在
    public String judgeExist(String Id) {
//        List<dataBase> db = dataBase.find.where().ilike("arrt3", Id).findList();
        List<picData> db=picData.find.where().ilike("source_id",Id).findList();
        if (db.isEmpty()) {
            return ("Has_None");
        } else {
            return ("Has");
        }
    }

    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3 * 1000);
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream = conn.getInputStream();
        byte[] getData = readInputStream(inputStream);

        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        //   play.Logger.info("info:" + url + " download success");
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public void get_comment(String link, String id) throws IOException {
        String real_id = id;
        String real_link = "http://www.qiushibaike.com" + link;
        Document doc = Jsoup.connect(real_link).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
        org.jsoup.select.Elements elements1 = doc.select("div.replay");
        //play.Logger.info("replay:" + elements1);

        for (org.jsoup.nodes.Element e : elements1) {
            String comment = String.valueOf(e.select("span.body").text());
            String person = String.valueOf(e.select("a").text());
            dataComment dataComment = new dataComment();
            dataComment.setComment_detail(comment);
            dataComment.setComment_header(real_id);
            dataComment.setComment_person(person);
            dataComment.insert();
//            play.Logger.info("person:" + person);
//            play.Logger.info("comment" + comment);
        }
    }

    class MyTask extends java.util.TimerTask {

        @Override
        public void run() {
            // TODO Auto-generated method stub
//            play.Logger.info("aaaaa");
            try {
                get_word_main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
