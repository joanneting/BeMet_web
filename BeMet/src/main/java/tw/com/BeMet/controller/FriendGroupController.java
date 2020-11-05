package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.BeMet.bean.FriendGroupBean;
import tw.com.BeMet.service.FriendGroupService;

import java.util.List;

@RestController
@RequestMapping("/friendgroup")
public class FriendGroupController {
    @Autowired
    FriendGroupService friendGroupService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody FriendGroupBean friendGroupBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendGroupBean fgb = friendGroupService.add(friendGroupBean);
            result.putPOJO("data", fgb);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody FriendGroupBean friendGroupBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendGroupBean fgb = friendGroupService.update(friendGroupBean);
            result.put("result", true);
            result.putPOJO("data", fgb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody FriendGroupBean friendGroupBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<FriendGroupBean> friendGroupBeanList = friendGroupService.search(friendGroupBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (FriendGroupBean fgb :
                    friendGroupBeanList) {
                arrayNode.addPOJO(fgb);
            }
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
    @PostMapping(value = "/search/friend/group/{groupNo}", produces = "application/json;charset=UTF-8")
    public String searchFriendByGroup(@PathVariable Integer groupNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {

            List<FriendGroupBean> friendGroupBeanList = friendGroupService.searchFriendByGroup(groupNo);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (FriendGroupBean fgb :
                    friendGroupBeanList) {
                arrayNode.addPOJO(fgb);
            }
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
    @PostMapping(value = "/search/count", produces = "application/json;charset=UTF-8")
    public String searchCount() throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<FriendGroupBean> friendGroupBeanList = friendGroupService.searchGroupCount();
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (FriendGroupBean fgb :
                    friendGroupBeanList) {
                arrayNode.addPOJO(fgb);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/{friendGroupNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer friendGroupNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            friendGroupService.delete(friendGroupNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
