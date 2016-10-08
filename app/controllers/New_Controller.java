package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.picData;
import play.Logger;
import play.api.libs.iteratee.Enumeratee;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import services.UserService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016-9-14.
 */
public class New_Controller extends Controller {

    private DynamicForm form;

    @Inject
    public New_Controller(FormFactory formFactory) {
        form = formFactory.form();
    }

    public play.mvc.Result returnPic() throws JsonProcessingException {
        Map<String, String> getData=form.bindFromRequest().data();
//        String id = getData.get("url");
        Logger.debug(String.valueOf(getData));

        List<picData> pc = picData.find.where().ilike("id", "1").findList();
        for(picData p:pc){
            p.setValue("aaas");
            p.update();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("data", pc);

        try {
            return ok(Json.mapper().writeValueAsString(map));
        } catch (JsonProcessingException e) {
            return badRequest();
        }
    }
}
