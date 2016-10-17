package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.picData;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016-10-11.
 */
public class ShowPic_all extends Controller {
    public play.mvc.Result returnPic() {

        List<picData> pc = picData.find.setFirstRow(2).setMaxRows(5).findList();
//        Map<String, Object> map = new HashMap<>();
//        map.put("data", pc);
//
//        try {
//            return ok(Json.mapper().writeValueAsString(map));
//        } catch (JsonProcessingException e) {
//            return badRequest();
//        }

        Logger.debug(String.valueOf(pc));
        return ok();
    }
}
