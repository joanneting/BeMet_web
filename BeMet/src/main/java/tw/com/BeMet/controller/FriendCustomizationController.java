package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.BeMet.bean.FriendCustomizationBean;
import tw.com.BeMet.service.FriendCustomizationService;

import java.util.List;

@RestController
@RequestMapping("/friendcustomization")
public class FriendCustomizationController {
    @Autowired
    FriendCustomizationService friendCustomizationService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody FriendCustomizationBean friendCustomizationBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendCustomizationBean fcb = friendCustomizationService.add(friendCustomizationBean);
            result.put("result", true);
            result.putPOJO("data", fcb);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody FriendCustomizationBean friendCustomizationBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendCustomizationBean fcb = friendCustomizationService.update(friendCustomizationBean);
            result.put("result", true);
            result.putPOJO("data", fcb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody FriendCustomizationBean friendCustomizationBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<FriendCustomizationBean> friendCustomizationBeanList = friendCustomizationService.search(friendCustomizationBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (FriendCustomizationBean fcb :
                    friendCustomizationBeanList) {
                arrayNode.addPOJO(fcb);
            }
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);

    }

    @PostMapping(value = "/delete/{friendCustomizationNo}", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer friendCustomizationNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            friendCustomizationService.delete(friendCustomizationNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
