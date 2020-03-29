package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.business_meet.bean.UserInformationBean;
import tw.com.business_meet.service.UserInformationService;

import java.util.List;

@RestController
@RequestMapping("/userinformation")
public class UserInformationController {
    @Autowired
    UserInformationService userInformationService;
    @PostMapping(path = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody  UserInformationBean userInformationBean) throws Exception{
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            System.out.println(userInformationBean.getBlueTooth());
            List<UserInformationBean> uibList = userInformationService.search(userInformationBean);
            System.out.println("success");
            result.put("result",true);
            ArrayNode arrayNode = result.putArray("data");
            for (UserInformationBean informationBean : uibList) {
                System.out.println(informationBean.getUserName());
                arrayNode.addPOJO(informationBean);
            }
        }catch (Exception e){
            result.put("result",false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody UserInformationBean userInformationBean) throws Exception{
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        System.out.println("uib.getAvatar() = " + userInformationBean.getAvatar());
        try{
            UserInformationBean uib = userInformationService.add(userInformationBean);

            result.put("result",true);
            result.putPOJO("data",uib);
        }catch(Exception e){
            e.printStackTrace();
            result.put("result",false);
            result.putObject("data");
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody UserInformationBean userInformationBean) throws Exception{
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try{
            userInformationService.update(userInformationBean);
            result.put("result",true);
        }catch(Exception e){
            result.put("result",false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
    @GetMapping(path = "/test")
    public String test(){
        return "success";
    }


}
