package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.BeMet.bean.ActivityRemindBean;
import tw.com.BeMet.service.ActivityRemindService;

import java.util.List;

@RestController
@RequestMapping("/activityremind")
public class ActivityRemindController {
    @Autowired
    ActivityRemindService activityRemindService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody ActivityRemindBean activityRemindBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityRemindBean arb = activityRemindService.add(activityRemindBean);
            result.putPOJO("data", arb);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody ActivityRemindBean activityRemindBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityRemindBean arb = activityRemindService.update(activityRemindBean);
            result.put("result", true);
            result.putPOJO("data", arb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody ActivityRemindBean activityRemindBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<ActivityRemindBean> activityRemindBeanList = activityRemindService.search(activityRemindBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (ActivityRemindBean arb :
                    activityRemindBeanList) {
                arrayNode.addPOJO(arb);
            }
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);

    }

    @PostMapping(value = "/{activityRemindNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer activityRemindNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            activityRemindService.delete(activityRemindNo);
            result.put("result", true);

        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

}
