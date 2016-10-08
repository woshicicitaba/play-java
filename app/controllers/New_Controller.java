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
        Map<String, String> getData = form.bindFromRequest().data();
        Logger.debug(String.valueOf(getData));
        String id = getData.get("id");
        String like = getData.get("like");
        String dislike = getData.get("dislike");
        //       Logger.debug(String.valueOf(id));

        List<picData> pc = picData.find.where().ilike("id", id).findList();
        for (picData p : pc) {
//            p.setValue("aaas");
            int old_dislike_num = Integer.parseInt(String.valueOf(p.getDis_like_num()));
            int dislike_num = Integer.parseInt(String.valueOf(dislike));
            int new_dislike_num=old_dislike_num+dislike_num;
            p.setDis_like_num((long) new_dislike_num);

            int old_like_num = Integer.parseInt(String.valueOf(p.getLike_num()));
            int like_num = Integer.parseInt(String.valueOf(like));
            int new_like_num=old_like_num+like_num;
            p.setLike_num((long) new_like_num);

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
