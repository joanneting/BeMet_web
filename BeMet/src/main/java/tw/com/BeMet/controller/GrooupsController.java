package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.BeMet.bean.FriendGroupBean;
import tw.com.BeMet.bean.GroupsBean;
import tw.com.BeMet.service.FriendGroupService;
import tw.com.BeMet.service.GroupsService;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GrooupsController {
    @Autowired
    GroupsService groupsService;
    @Autowired
    FriendGroupService friendGroupService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody GroupsBean groupsBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            GroupsBean gb = groupsService.add(groupsBean);
            result.put("result", true);
            result.putPOJO("data", gb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody GroupsBean groupsBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            GroupsBean gb = groupsService.update(groupsBean);
            result.put("result", true);
            result.putPOJO("data", gb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody GroupsBean groupsBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<GroupsBean> groupsBeanList = groupsService.search(groupsBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (GroupsBean gb :
                    groupsBeanList) {
                arrayNode.addPOJO(gb);
            }
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/{groupsNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer groupsNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendGroupBean friendGroupBean = new FriendGroupBean();
            friendGroupBean.setFriendGroupNo(groupsNo);
            List<FriendGroupBean> friendGroupBeanList = friendGroupService.search(friendGroupBean);
            for (FriendGroupBean groupBean : friendGroupBeanList) {
                friendGroupService.delete(groupBean.getFriendGroupNo());
            }
            groupsService.delete(groupsNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
