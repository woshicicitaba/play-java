package controllers;

import models.dataBase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import play.api.mvc.Result;
import play.mvc.Controller;
import play.mvc.Results;
import play.mvc.StatusHeader;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by admin on 2016-8-23.
 */
public class GetQiushiMM extends Controller {
    public play.mvc.Result getDta() throws IOException {
        int PageNum = 1;
        for (int j = 1; j <= 5; j++) {
            String url = "http://www.qiushimm.com/page/" + PageNum;
            PageNum++;
            play.Logger.info(String.valueOf(PageNum));
            Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
            // String html = doc.title();
            org.jsoup.select.Elements elements = doc.select("img");

            for (org.jsoup.nodes.Element e : elements) {
                String ImagAlt = String.valueOf(e.attr("src"));
                String[] strarray=ImagAlt.split("/");
                ImagAlt = strarray[strarray.length-1];
                play.Logger.info(ImagAlt);
                downLoadFromUrl(e.attr("src"), ImagAlt, "E:/im");

                // play.Logger.info(String.valueOf(ImagAlt));
                //play.Logger.info(String.valueOf(PicName));
            }
            PageNum++;
        }
        return ok();
    }

    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
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
