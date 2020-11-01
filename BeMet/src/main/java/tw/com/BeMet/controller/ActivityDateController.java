package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.BeMet.bean.ActivityDateBean;
import tw.com.BeMet.service.ActivityDateService;

import java.util.List;

@RestController
@RequestMapping("/activitydate")
public class ActivityDateController {
    @Autowired
    ActivityDateService activityDateService;

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody ActivityDateBean activityDateBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityDateBean tb = activityDateService.add(activityDateBean);
            result.put("result", true);
            result.putPOJO("data", tb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/update", produces = "application/json;charset=UtF-8")
    public String update(@RequestBody ActivityDateBean activityDateBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityDateBean tb = activityDateService.update(activityDateBean);
            result.put("result", true);
            result.putPOJO("data", tb);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);

        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody ActivityDateBean activityDateBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<ActivityDateBean> activityDateBeanList = activityDateService.search(activityDateBean);
            ArrayNode arrayNode = result.putArray("data");
            for (ActivityDateBean tb :
                    activityDateBeanList) {
                arrayNode.addPOJO(tb);
            }
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/{activityDateNp}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer activityDateNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            activityDateService.delete(activityDateNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
