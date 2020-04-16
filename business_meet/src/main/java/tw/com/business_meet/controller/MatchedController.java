package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tw.com.business_meet.bean.MatchedBean;
import tw.com.business_meet.service.MatchedService;
import tw.com.business_meet.vo.Matched;

import java.util.List;

@RestController
@RequestMapping("/matched")
public class MatchedController {

    @Autowired
    MatchedService matchedService;
    @PostMapping(path = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody MatchedBean matchedBean) throws Exception{
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try{
            List<MatchedBean> mbList = matchedService.search(matchedBean);
            result.put("result",true);
            ArrayNode arrayNode = result.putArray("data");
            for (MatchedBean mb:mbList){
                arrayNode.addPOJO(mb);
            }
        }catch(Exception e){
            result.put("result",false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody MatchedBean matchedBean) throws Exception{
        System.out.println("success");
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try{
           MatchedBean mb = matchedService.add(matchedBean);
           System.out.println(mb.getCreateDate());
           result.put("result",true);
           result.putPOJO("data",mb);
        }catch(Exception e){
            result.put("result",false);
            result.putObject("data");
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
    @PostMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody MatchedBean matchedBean) throws Exception{
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try{
            matchedService.update(matchedBean);
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
            List<MatchedBean> matchedBeanList = matchedService.searchAll();
            modelAndView.addObject("dataList",matchedBeanList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }
}
