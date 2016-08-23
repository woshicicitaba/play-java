package controllers;

import com.google.inject.Inject;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by admin on 2016/7/29.
 */
public class PicController extends Controller {

    private DynamicForm form;
    private Object imageByte;

    @Inject
    public PicController(FormFactory formFactory) {
        form = formFactory.form();
    }

    public Result getPic() throws IOException {
        //Logger.debug("xxxx");
        //   Http.MultipartFormData files = request().body().asMultipartFormData();
        //   Http.MultipartFormData.FilePart img = files.getFile("img");
        //   String fileName = img.getFilename();
        //    String contentType = img.getContentType();
        //   Map<String, String> data=form.bindFromRequest().data();
        //     String p_x = data.get("p_x");
        //     String p_y = data.get("p_y");
        //    String n_x = data.get("n_x");
        //    String n_y = data.get("n_y");
        //   String note = data.get("note");
        //   Logger.debug(fileName.toString());

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        String fileName = picture.getFilename();
        Logger.debug(fileName.toString());
        String contentType = picture.getContentType();
        Logger.debug(contentType.toString());
        File file = picture.getFile();

        Image srcImg = ImageIO.read(file);
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);

        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();

        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        Font f=new Font("Arial",Font.BOLD,50);
        g.setFont(f);
        g.setColor(Color.BLACK);
        int x = 10;
        int y = 100;
        g.drawString("big nai", x, y);
        g.dispose();

        FileOutputStream outImgStream = new FileOutputStream("E://2.jpg");
        ImageIO.write(bufImg, "jpg", outImgStream);
        outImgStream.flush();
        outImgStream.close();

        return redirect("/show.html");
    }

    public Result returnPic() throws IOException {
        return ok(new File("E:/2.jpg"));
    }
}
