package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tw.com.BeMet.bean.UserInformationBean;
import tw.com.BeMet.service.UserInformationService;

import java.util.List;

@RestController
@RequestMapping("/userinformation")
public class UserInformationController {
    @Autowired
    UserInformationService userInformationService;


    @PostMapping(path = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody UserInformationBean userInformationBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<UserInformationBean> uibList = userInformationService.search(userInformationBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (UserInformationBean ui : uibList) {
                arrayNode.addPOJO(ui);
            }
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/get/{userId}", produces = "application/json;charset=utf-8")
    public String getById(@PathVariable String userId) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            System.out.println(userId);
            UserInformationBean userInformationBean = userInformationService.getById(userId);
            result.put("result", true);
            result.putPOJO("data", userInformationBean);

        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);

    }

    @PostMapping(path = "/getbyidentifier/{identifier}", produces = "application/json;charset=utf-8")
    public String getByIdentifier(@PathVariable String identifier) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            UserInformationBean userInformationBean = userInformationService.getByIdentifier(identifier);
            if (userInformationBean != null) {
                result.put("result", true);
                result.putPOJO("data", userInformationBean);
            } else {
                result.put("result", false);
            }
        } catch (Exception e) {
            result.put("result", false);
//            result.putObject("data");
            e.printStackTrace();
        }
        return o.writeValueAsString(result);

    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody UserInformationBean userInformationBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            UserInformationBean check = userInformationService.getById(userInformationBean.getUserId());
            if(check == null) {
                check = userInformationService.getByIdentifier(userInformationBean.getIdentifier());

                if(check == null) {
                    UserInformationBean uib = userInformationService.add(userInformationBean);

                    result.put("result", true);
                    result.putPOJO("data", uib);
                }else {
                    result.put("result", false);
                    result.put("message", "此手機識別碼已註冊");
                }
            }else {
                result.put("result", false);
                result.put("message", "此帳號已被註冊");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
            result.putObject("data");
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody UserInformationBean userInformationBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            userInformationBean = userInformationService.update(userInformationBean);
            result.put("result", true);
            result.putPOJO("data", userInformationBean);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @GetMapping(path = "/test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("searchPage");
        try {
            List<UserInformationBean> userInformationBeanList = userInformationService.searchAll();
            modelAndView.addObject("dataList", userInformationBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }


}
