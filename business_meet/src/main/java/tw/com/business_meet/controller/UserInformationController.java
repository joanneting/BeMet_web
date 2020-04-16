package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
            List<UserInformationBean> uibList = userInformationService.search(userInformationBean);
            result.put("result",true);
            ArrayNode arrayNode = result.putArray("data");
            for (UserInformationBean ui : uibList) {
                arrayNode.addPOJO(ui);
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
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView("searchPage");
        try{
            List<UserInformationBean> userInformationBeanList = userInformationService.searchAll();
            modelAndView.addObject("dataList",userInformationBeanList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }


}
