package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.BeMet.bean.ActivityInviteBean;
import tw.com.BeMet.service.ActivityInviteService;

import java.util.List;

@RestController
@RequestMapping("/activityinvite")
public class ActivityInviteController {
    @Autowired
    ActivityInviteService activityInviteService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody ActivityInviteBean activityInviteBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityInviteBean aib = activityInviteService.add(activityInviteBean);
            result.putPOJO("data", aib);
            result.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody ActivityInviteBean activityInviteBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityInviteBean aib = activityInviteService.update(activityInviteBean);
            result.put("result", true);
            result.putPOJO("date", aib);

        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody ActivityInviteBean activityInviteBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<ActivityInviteBean> activityInviteBeanList = activityInviteService.search(activityInviteBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (ActivityInviteBean aib :
                    activityInviteBeanList) {
                arrayNode.addPOJO(aib);
            }
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/{activityInviteNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer activityInviteNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            activityInviteService.delete(activityInviteNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
}
