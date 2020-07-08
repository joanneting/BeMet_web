package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tw.com.business_meet.bean.FriendBean;
import tw.com.business_meet.service.FriendService;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    @PostMapping(path = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody FriendBean friendBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        System.out.println("searchBlueTooth : " + friendBean.getMatchmakerId());
        try {
            List<FriendBean> fbList = friendService.search(friendBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            if (fbList.size() <= 0) {
                fbList.add(friendBean);
            }
            for (FriendBean fb : fbList) {
                System.out.println("search : " + fb.getMatchmakerId() + " : " + fb.getFriendId());
                arrayNode.addPOJO(fb);
            }

        } catch (Exception e) {
            result.put("result", false);
            result.putObject("data");
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody FriendBean friendBean) throws Exception {
        System.out.println("success");
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendBean fb = friendService.add(friendBean);
            System.out.println(fb.getCreateDate());
            result.put("result", true);
            result.putPOJO("data", fb);
        } catch (Exception e) {
            result.put("result", false);
            result.putObject("data");
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody FriendBean friendBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            friendBean = friendService.update(friendBean);
            result.put("result", true);
            result.putPOJO("data", friendBean);
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
            List<FriendBean> friendBeanList = friendService.searchAll();
            modelAndView.addObject("dataList", friendBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}
