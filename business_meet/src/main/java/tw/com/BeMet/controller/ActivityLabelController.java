package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.BeMet.bean.ActivityLabelBean;
import tw.com.BeMet.service.ActivityLabelService;

import java.util.List;

@RestController
@RequestMapping("/activitylabel")
public class ActivityLabelController {
    @Autowired
    ActivityLabelService activityLabelService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(ActivityLabelBean activityLabelBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityLabelBean alb = activityLabelService.add(activityLabelBean);
            result.putPOJO("data", alb);
            result.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", true);
        }
        return o.writeValueAsString(result);
    }



    @PostMapping(value = "/update", produces = "applicaiton/json;charset=UTF-8")
    public String update(ActivityLabelBean activityLabelBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityLabelBean alb = activityLabelService.update(activityLabelBean);
            result.putPOJO("data", alb);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(ActivityLabelBean activityLabelBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<ActivityLabelBean> activityLabelBeanList = activityLabelService.search(activityLabelBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (ActivityLabelBean alb :
                    activityLabelBeanList) {
                arrayNode.addPOJO(alb);
            }
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/{activityLabelNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer activityLabelNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            activityLabelService.delete(activityLabelNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
