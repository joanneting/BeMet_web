package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tw.com.business_meet.bean.FriendBean;
import tw.com.business_meet.service.FriendService;
import tw.com.business_meet.vo.Friend;

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
            FriendBean resultBean = new FriendBean();
            FriendBean searchBean = friendService.searchAddData(friendBean);
            if (searchBean!=null){
                if (searchBean.getStatus() == 3){
                    searchBean.setStatus(2);
                    resultBean = friendService.update(searchBean);
                }else {
                    resultBean = searchBean;
                }
            }else{
                 friendBean.setStatus(1);
                 resultBean = friendService.add(friendBean);
            }
            result.put("result", true);
            result.putPOJO("data", resultBean);
        } catch (Exception e) {
            result.put("result", false);
            result.putObject("data");
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
    @PostMapping(path = "/invite", produces = "application/json;charset=UTF-8")
    public String invite(@RequestBody FriendBean friendBean) throws Exception{
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            Integer status = friendBean.getStatus();
            if(status == null){
                friendService.delete(friendBean.getFriendNo());
            }else if (status == 2){
                FriendBean searchBean = new FriendBean();
                searchBean.setFriendId(friendBean.getFriendId());
                searchBean.setMatchmakerId(friendBean.getMatchmakerId());
                FriendBean searchResultBean = friendService.searchAddData(searchBean);
                if(searchResultBean ==null) {
                    searchBean.setStatus(2);
                    friendService.add(searchBean);
                }else {
                    searchResultBean.setStatus(2);
                    friendService.update(searchResultBean);
                }
                FriendBean friendMatchBean = new FriendBean();
                friendMatchBean.setMatchmakerId(friendBean.getFriendId());
                friendMatchBean.setFriendId(friendBean.getMatchmakerId());
                friendMatchBean = friendService.searchAddData(friendMatchBean);
                friendMatchBean.setStatus(2);
                FriendBean resultBean = friendService.update(friendMatchBean);
                result.putPOJO("data",resultBean);
            }
            result.put("result",true);
        } catch (Exception e) {
            result.put("result",false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
    @PostMapping(path = "/delete/{friendNo}", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer friendNo) throws Exception{
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        System.out.println("\"delete\" = " + "delete");
        try {
            FriendBean friendBean = friendService.getById(friendNo);
            friendBean.setStatus(3);
            friendService.update(friendBean);
            result.put("result",true);
        } catch (Exception e) {
            result.put("result",false);
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

    @PostMapping(path = "/search/invitelist", produces = "application/json;charset=UTF-8")
    public String searchInviteList(@RequestBody FriendBean friendBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            System.out.println("searchinviteList");
            List<FriendBean> fbList = friendService.searchInviteList(friendBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");

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
    @PostMapping(path = "/invite/notification",produces = "application/json")
    public String inviteNotification() throws Exception{
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<FriendBean> friendBeanList = friendService.inviteNotification();
            ArrayNode arrayNode = result.putArray("data");
            for (FriendBean friendBean : friendBeanList) {
                arrayNode.addPOJO(friendBean);
            }
            result.put("result",true);
        } catch (Exception e) {
            result.put("result",false);
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
