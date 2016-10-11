package controllers;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import models.dataBase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import play.mvc.Controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by admin on 2016-10-10.
 */
public class GetBaisibudejie extends Controller {

//    public  play.mvc.Result test_w() {
//        WebDriver driver = new JBrowserDriver();
//        String url="http://www.budejie.com/pic";
//        driver.get(url);
//        String s = driver.getPageSource();
//        Document document = Jsoup.parse(s);
//        for (Element table : document.select(".newlist_list_content").select("table")) {
//            StringBuilder builder = new StringBuilder();
//            builder.append(table.select("td[class='gsmc']").text());
//            System.out.println(builder);
//        }
//        driver.close();
//        play.Logger.info(s);
//        return ok();
//    }

    public play.mvc.Result getWord() throws IOException {
        int page_num = 1;//
        int i=1;
        for (int j = 1; j <= 1; j++) {
            String url = "http://www.budejie.com/pic/" + page_num;
            play.Logger.info(String.valueOf(url));

            Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
            org.jsoup.select.Elements elements1 = doc.select("div.j-r-list-c");

            for (org.jsoup.nodes.Element e : elements1) {
//                String id = String.valueOf(e.id());
                String ppData = String.valueOf(e.select("div.j-r-list-c-desc").text());
                org.jsoup.select.Elements elements2= e.getElementsByClass("j-r-list-c-img");
                org.jsoup.select.Elements elements3 = elements2.select("img");
                String id = elements3.attr("data-original");

                String[] strarray=id.split("/");
                String ppurl = strarray[strarray.length-1];

                play.Logger.info("data:"+ppData);
                play.Logger.info("id:"+id);
                play.Logger.info("url:"+ppurl);

                if("Has_None"==judgeExist(id)){
                    if (ppurl != null) {
                        i++;
                        downLoadFromUrl(id, ppurl, "E:/im");

                        insertPicNews(ppData, id,ppurl);
                    } else {
                        play.Logger.info("ppurl is null"+ ppurl);
                    }
                }
                else {
                    play.Logger.info("已存在"+ ppurl);
                }
            }
            page_num++;
        }
        return ok();
    }

    //数据库插值
    public void insertPicNews(String Date,String Id,String Alt){
        dataBase db = new dataBase();
        db.setType("Baishibudejie");
        db.setArrt1(Date);
        db.setArrt2(Alt);
        db.setArrt3(Id);
        db.insert();
    }

    //判断是否存在
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
