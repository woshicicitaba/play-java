package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends Controller {

    private DynamicForm form;

    @Inject
    private UserService userService;

    @Inject
    public UserController(FormFactory formFactory) {
        form = formFactory.form();
    }

    public Result picc() {
        Http.MultipartFormData files = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart img = files.getFile("img");
        Map<String, String> data=form.bindFromRequest().data();
        String p_x = data.get("p_x");
        String p_y = data.get("p_y");
        String n_x = data.get("n_x");
        String n_y = data.get("n_y");
        Logger.debug(n_y.toString());
        return ok();
    }

    public Result get() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "wh");
        data.put("message", "Hello world");
        try {
            return ok(Json.mapper().writeValueAsString(data));
        } catch (JsonProcessingException e) {
            return badRequest();
        }
    }

    public Result json() {
        JsonNode node = request().body().asJson();
        Logger.debug(node.toString());
        String p_x = node.get("p_x").asText();
        String p_y = node.get("p_y").asText();
        String n_x = node.get("n_x").asText();
        String n_y = node.get("n_y").asText();
       // name = userService.save(name);
       Map<String, Object> data = new HashMap<>();
      //  data.put("p_x", name);
        try {
            return ok(Json.mapper().writeValueAsString(data));
        } catch (JsonProcessingException e) {
            return badRequest();
        }
    }

    public Result image() {
        Http.MultipartFormData files = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart img = files.getFile("img");
        Logger.debug(img.getFilename());
        try (FileOutputStream out = new FileOutputStream("C:/a.jpg"); FileInputStream in = new FileInputStream((File) img.getFile())) {
            int c = in.read();
            while (c != -1) {
                out.write(c);
                c = in.read();
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return redirect("");
//        return ok((File) img.getFile());
    }

}