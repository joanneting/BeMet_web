package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.business_meet.bean.FriendRemarkBean;
import tw.com.business_meet.service.FriendRemarkService;

import java.util.List;

@RestController
@RequestMapping("/friendremark")
public class FriendRemarkController {
    @Autowired
    FriendRemarkService friendRemarkService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody FriendRemarkBean friendRemarkBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendRemarkBean frb = friendRemarkService.add(friendRemarkBean);
            result.put("result", true);
            result.putPOJO("data", frb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody FriendRemarkBean friendRemarkBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendRemarkBean frb = friendRemarkService.update(friendRemarkBean);
            result.put("result", true);
            result.putPOJO("data", frb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody FriendRemarkBean friendRemarkBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<FriendRemarkBean> friendRemarkBeanList = friendRemarkService.search(friendRemarkBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (FriendRemarkBean frb :
                    friendRemarkBeanList) {
                arrayNode.addPOJO(frb);
            }
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/{friendRemarkNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer friendRemarkNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            friendRemarkService.delete(friendRemarkNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
