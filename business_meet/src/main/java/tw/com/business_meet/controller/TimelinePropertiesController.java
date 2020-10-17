package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.business_meet.bean.TimelinePropertiesBean;
import tw.com.business_meet.service.TimelinePropertiesService;

import java.util.List;

@RestController
@RequestMapping("/timelineproperties")
public class TimelinePropertiesController {
    @Autowired
    TimelinePropertiesService timelinePropertiesService;

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody TimelinePropertiesBean timelinePropertiesBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            TimelinePropertiesBean tpb = timelinePropertiesService.add(timelinePropertiesBean);
            result.put("result", true);
            result.putPOJO("data", tpb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/update", produces = "application/json;charset=UtF-8")
    public String update(@RequestBody TimelinePropertiesBean timelinePropertiesBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            TimelinePropertiesBean tpb = timelinePropertiesService.update(timelinePropertiesBean);
            result.put("result", true);
            result.putPOJO("data", tpb);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);

        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody TimelinePropertiesBean timelinePropertiesBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<TimelinePropertiesBean> timelinePropertiesList = timelinePropertiesService.search(timelinePropertiesBean);
            ArrayNode arrayNode = result.putArray("data");
            for (TimelinePropertiesBean tpb :
                    timelinePropertiesList) {
                arrayNode.addPOJO(tpb);
            }
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/{timelinePropertiesNp}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer timelinePropertiesNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            timelinePropertiesService.delete(timelinePropertiesNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
