package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import models.dataBase;
import models.picData;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.StatusHeader;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/5.
 */
public class PicController_h5 extends Controller {

    public play.mvc.Result returnPic() {
        /*
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", i);
            map.put("url", "../pic/" + i + ".jpg");
            map.put("value", "lable:" + i);
            data.add(map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        */

        List<picData> pc = picData.find.where().orderBy("id").setFirstRow(1).setMaxRows(20).findList();
        Map<String, Object> map = new HashMap<>();
        map.put("data", pc);

        try {
            return ok(Json.mapper().writeValueAsString(map));
        } catch (JsonProcessingException e) {
            return badRequest();
        }
    }

    public play.mvc.Result showPic() {
        JsonNode node = request().body().asJson();
        Logger.debug(node.toString());
        String id = node.get("id").asText();
        List<picData> pc = picData.find.where().ilike("id", id).findList();
        Map<String, Object> map = new HashMap<>();
        map.put("data", pc);
        //map.put("data", "../2.pic");

        try {
            return ok(Json.mapper().writeValueAsString(map));
        } catch (JsonProcessingException e) {
            return badRequest();
        }
    }

    public play.mvc.Result showWord() {
        List<dataBase> db = dataBase.find.where().orderBy("id").setFirstRow(1).setMaxRows(20).findList();
        Map<String, Object> map = new HashMap<>();
        map.put("data", db);

        try {
            return ok(Json.mapper().writeValueAsString(map));
        } catch (JsonProcessingException e) {
            return badRequest();
        }
    }
}
