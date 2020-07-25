package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.business_meet.bean.TimelineBean;
import tw.com.business_meet.service.TimelineService;

import java.util.List;

@RestController
@RequestMapping("/timeline")
public class TimelineController {
    @Autowired
    TimelineService timelineService;

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody TimelineBean timelineBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            TimelineBean tb = timelineService.add(timelineBean);
            result.put("result", true);
            result.putPOJO("data", tb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/update", produces = "application/json;charset=UtF-8")
    public String update(@RequestBody TimelineBean timelineBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            TimelineBean tb = timelineService.update(timelineBean);
            result.put("result", true);
            result.putPOJO("data", tb);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);

        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody TimelineBean timelineBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<TimelineBean> timelineBeanList = timelineService.search(timelineBean);
            ArrayNode arrayNode = result.putArray("data");
            for (TimelineBean tb :
                    timelineBeanList) {
                arrayNode.addPOJO(tb);
            }
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/{timelineNp}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer timelineNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            timelineService.delete(timelineNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
