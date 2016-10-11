package controllers;

import models.dataBase;
import models.picData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import play.mvc.Controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by admin on 2016-10-8.
 */
public class GetQiushibaike extends Controller {

    public play.mvc.Result getWord() throws IOException {
        int page_num = 1;//
        for (int j = 1; j <= 1; j++) {
            String url = "http://www.qiushibaike.com/imgrank/page/" + page_num + "/";
            Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
            org.jsoup.select.Elements elements1 = doc.select("div.article");

            play.Logger.info(String.valueOf(url));

            for (org.jsoup.nodes.Element e : elements1) {
                String id = String.valueOf(e.id());
                String ppData = String.valueOf(e.select("div.content").text());
                org.jsoup.select.Elements elements2= e.getElementsByClass("thumb");
                org.jsoup.select.Elements elements3 = elements2.select("img");
                String ppurl = elements3.attr("src");

                if("Has_None"==judgeExist(id)){
                    if (ppurl != null) {
                        String ImagAlt = id + ".jpg";
                        downLoadFromUrl(ppurl, ImagAlt, "E:/im");

                        insertPicNews(ppData,id,ImagAlt);
                    } else {
                        play.Logger.info("ppurl is null"+id);
                    }
                }
                else {
                    play.Logger.info("存在"+id);
                }
            }
            page_num++;
        }
        return ok();
    }

    //数据库插值
    public void insertPicNews(String Date,String Id,String Alt){
        dataBase db = new dataBase();
        db.setType("Qiushibaike");
        db.setArrt1(Date);
        db.setArrt2(Alt);
        db.setArrt3(Id);
        db.insert();
    }

    //判断是否已经存在
    public String judgeExist(String Id){
        List<dataBase> db = dataBase.find.where().ilike("arrt3", Id).findList();
        if(db.isEmpty()){
            return ("Has_None");
        }
        else{
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
}
