package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.BeMet.bean.UserCustomizationBean;
import tw.com.BeMet.service.UserCustomizationService;

import java.util.List;

@RestController
@RequestMapping("/usercustomization")
public class UserCustomizationController {
    @Autowired
    UserCustomizationService userCustomizationService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody UserCustomizationBean userCustomizationBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            UserCustomizationBean ucb = userCustomizationService.add(userCustomizationBean);
            result.put("result", true);
            result.putPOJO("data", ucb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody UserCustomizationBean userCustomizationBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            UserCustomizationBean ucb = userCustomizationService.update(userCustomizationBean);
            result.put("result", true);
            result.putPOJO("data", ucb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody UserCustomizationBean userCustomizationBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<UserCustomizationBean> userCustomizationBeanList = userCustomizationService.search(userCustomizationBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (UserCustomizationBean ucb :
                    userCustomizationBeanList) {
                arrayNode.addPOJO(ucb);
            }
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/{userCustomizationNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer userCustomizationNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            userCustomizationService.delete(userCustomizationNo);
            result.put("result", true);

        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
}
