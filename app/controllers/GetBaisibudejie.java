package controllers;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import models.dataBase;
import models.dataComment;
import models.picData;
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

    public play.mvc.Result getWord() throws IOException {
        int page_num = 1;//
        int i = 1;
        for (int j = 1; j <= 1; j++) {
            String url = "http://www.budejie.com/pic/" + page_num;
            play.Logger.info(String.valueOf(url));

            Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
            org.jsoup.select.Elements elements1 = doc.select("div.j-r-list");

            for (org.jsoup.nodes.Element e : elements1) {
                org.jsoup.select.Elements e_img_data = e.getElementsByClass("j-r-list-c-img");
                for (org.jsoup.nodes.Element e_i_d : e_img_data) {
                    org.jsoup.select.Elements e_a = e_i_d.select("a");
                    String a_link = e_a.attr("href");
                    org.jsoup.select.Elements img = e_i_d.select("img");
                    String img_url = img.attr("data-original");
                    String img_title = img.attr("title");

//                    play.Logger.info(a_link);
//                    play.Logger.info(img_url);
//                    play.Logger.info(img_title);
//                    play.Logger.info(String.valueOf(i));
//                    i++;

                    String[] strarray = img_url.split("/");
                    String pSrc = strarray[strarray.length - 1];

                    if ("Has_None" == judgeExist(a_link)) {
                        if (img_url != null) {
                            i++;
                            downLoadFromUrl(img_url, pSrc, "E:/IdeaProjects/play-java/public/picc");
                            insertPicNews(a_link, pSrc, img_title);
                            get_comment(a_link, a_link);
                        } else {
                            play.Logger.info("ppurl is null" + img_url);
                        }
                    } else {
//                        play.Logger.info("已存在" + img_url);
                    }
                }
            }
            page_num++;
        }
        return ok();
    }

    //数据库插值
    public void insertPicNews(String Date, String url, String title) {
        String real_url = "picc/" + url;
        picData date_pic = new picData();
        date_pic.setType_pic("Baishibudejie");
        date_pic.setValue(title);
        date_pic.setUrl(real_url);
        date_pic.setSource_id(Date);
        date_pic.setLike_num((long) 0);
        date_pic.setLike_num((long) 0);
        date_pic.insert();
    }

    //判断是否存在
    public String judgeExist(String Id) {
        List<picData> db = picData.find.where().ilike("source_id", Id).findList();
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

    //未测试
    public void get_comment(String link, String id) throws IOException {
        String real_id = id;
        String real_link = "http://www.budejie.com" + link;
//        play.Logger.info("real_link:" + real_link);

        WebDriver driver = new JBrowserDriver();
        driver.get(real_link);
        String s = driver.getPageSource();
        Document doc = Jsoup.parse(s);
//        play.Logger.info(String.valueOf(doc));

        org.jsoup.select.Elements comment_detail = doc.select("div.g-mnc1");
        for (org.jsoup.nodes.Element e : comment_detail) {
            org.jsoup.select.Elements div_p = e.select("p");
//            play.Logger.info(String.valueOf(div_p.text()));

            String comment = div_p.text();
            if (comment != " ") {
                dataComment dataComment = new dataComment();
                dataComment.setComment_detail(comment);
                dataComment.setComment_header(real_id);
                dataComment.insert();
            }
        }

    }

}
