package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.dataComment;
import models.picData;
import play.Logger;
import play.api.libs.iteratee.Enumeratee;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.StatusHeader;
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

    public play.mvc.Result returnLoad() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
//        List<picData> pc = picData.find.setFirstRow(1).setMaxRows(3).orderBy("id desc").findList();
        List<picData> pc_1 = picData.find.setFirstRow(1).setMaxRows(1).orderBy("id desc").findList();
        map.put("data1", pc_1);
        for (picData pp : pc_1) {
            String source = String.valueOf(pp.getSource_id());
            List<dataComment> dataComments = dataComment.find.where().like("comment_header", source).findList();
            map.put("pic_comment1", dataComments);
        }

        List<picData> pc_2 = picData.find.setFirstRow(2).setMaxRows(1).orderBy("id desc").findList();
        map.put("data2", pc_2);
        for (picData pp : pc_2) {
            String source = String.valueOf(pp.getSource_id());
            List<dataComment> dataComments = dataComment.find.where().like("comment_header", source).findList();
            map.put("pic_comment2", dataComments);
        }

        try {
            return ok(Json.mapper().writeValueAsString(map));
        } catch (JsonProcessingException e) {
            return badRequest();
        }
    }

    //addͼƬ
    public play.mvc.Result returnPic() throws JsonProcessingException {
        Map<String, String> getData = form.bindFromRequest().data();
//        Logger.debug(String.valueOf(getData));
        String id = getData.get("id");
        String like = getData.get("like");
        String dislike = getData.get("dislike");
        //       Logger.debug(String.valueOf(id));

        List<picData> pc = picData.find.where().ilike("id", id).findList();
        for (picData p : pc) {
//            p.setValue("aaas");
            int old_dislike_num = Integer.parseInt(String.valueOf(p.getDis_like_num()));
            int dislike_num = Integer.parseInt(String.valueOf(dislike));
            int new_dislike_num = old_dislike_num + dislike_num;
            p.setDis_like_num((long) new_dislike_num);

            int old_like_num = Integer.parseInt(String.valueOf(p.getLike_num()));
            int like_num = Integer.parseInt(String.valueOf(like));
            int new_like_num = old_like_num + like_num;
            p.setLike_num((long) new_like_num);

            p.update();
        }

        int id_new = Integer.parseInt(id) - 1;
//        List<picData> new_pc = picData.find.where().ilike("id", String.valueOf(id_new)).findList();
        List<picData> new_pc = picData.find.setFirstRow(Integer.parseInt(id)).setMaxRows(1).orderBy("id desc").findList();
        List<dataComment> dataComments = null;
        for (picData pp : new_pc) {
            String source = String.valueOf(pp.getSource_id());
            dataComments = dataComment.find.where().like("comment_header", source).findList();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", new_pc);
        map.put("comment", dataComments);

        try {
            return ok(Json.mapper().writeValueAsString(map));
        } catch (JsonProcessingException e) {
            return badRequest();
        }
    }
}
