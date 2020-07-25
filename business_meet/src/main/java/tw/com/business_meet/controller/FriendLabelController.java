package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.business_meet.bean.FriendLabelBean;
import tw.com.business_meet.service.FriendLabelService;

import java.util.List;

@RestController
@RequestMapping("/friendlabel")
public class FriendLabelController {
    @Autowired
    FriendLabelService friendLabelService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody FriendLabelBean friendLabelBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendLabelBean flb = friendLabelService.add(friendLabelBean);
            result.put("result", true);
            result.putPOJO("data", flb);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody FriendLabelBean friendLabelBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendLabelBean flb = friendLabelService.add(friendLabelBean);
            result.put("result", true);
            result.putPOJO("data", flb);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody FriendLabelBean friendLabelBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<FriendLabelBean> friendLabelBeanList = friendLabelService.search(friendLabelBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (FriendLabelBean flb :
                    friendLabelBeanList) {
                arrayNode.addPOJO(flb);
            }
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/{friendLabelNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer friendLabelNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            friendLabelService.delete(friendLabelNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
