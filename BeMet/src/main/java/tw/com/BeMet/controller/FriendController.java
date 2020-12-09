package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tw.com.BeMet.bean.FriendBean;
import tw.com.BeMet.bean.UserInformationBean;
import tw.com.BeMet.service.FriendService;
import tw.com.BeMet.service.UserInformationService;
import tw.com.BeMet.vo.UserInformation;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;
    @Autowired
    UserInformationService userInformationService;

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
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            FriendBean resultBean = new FriendBean();
            FriendBean searchBean = friendService.searchAddData(friendBean);
            if (searchBean != null) {
                if (searchBean.getStatus() == 3) {
                    searchBean.setStatus(1);
                    resultBean = friendService.update(searchBean);
                } else {
                    resultBean = searchBean;
                }
            } else {
                friendBean.setStatus(1);
                resultBean = friendService.add(friendBean);
            }
            UserInformationBean userInformationBean = userInformationService.getById(friendBean.getFriendId());
            UserInformation myInformation = (UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String registrationToken = userInformationBean.getFirebaseToken();
            // See documentation on defining a message payload.
            AndroidConfig androidConfig = getAndroidConfig("friend" + friendBean.getFriendNo());
            Message message = Message.builder()
                    .setAndroidConfig(androidConfig).putData("type", "friendInvite").putData("friendId", myInformation.getUserId()).putData("friendName", myInformation.getName()).setToken(registrationToken)
                    .build();
            // Send a message to the device corresponding to the provided
            // registration token.
            String response = FirebaseMessaging.getInstance().sendAsync(message).get();
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
    public String invite(@RequestBody FriendBean friendBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            UserInformation userInformation = (UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Integer status = friendBean.getStatus();
            friendBean.setMatchmakerId(userInformation.getUserId());
            FriendBean friendMatchBean = new FriendBean();
            friendMatchBean.setMatchmakerId(friendBean.getFriendId());
            friendMatchBean.setFriendId(friendBean.getMatchmakerId());
            friendMatchBean = friendService.searchAddData(friendMatchBean);
            if (status == null) {
                friendService.delete(friendMatchBean.getFriendNo());
            } else if (status == 2) {
                FriendBean searchBean = new FriendBean();
                searchBean.setFriendId(friendBean.getFriendId());
                searchBean.setMatchmakerId(friendBean.getMatchmakerId());
                FriendBean searchResultBean = friendService.searchAddData(searchBean);
                if (searchResultBean == null) {
                    searchBean.setStatus(2);
                    friendService.add(searchBean);
                } else {
                    searchResultBean.setStatus(2);
                    searchResultBean = friendService.update(searchResultBean);
                }
                friendMatchBean.setStatus(2);
                FriendBean resultBean = friendService.update(friendMatchBean);

                UserInformationBean friendInformation = userInformationService.getById(friendBean.getFriendId());
                UserInformationBean myInformation = userInformationService.getById(friendBean.getMatchmakerId());
                String registrationToken = friendInformation.getFirebaseToken();
                if (registrationToken != null) {
                    AndroidConfig androidConfig = getAndroidConfig("friend" + resultBean.getFriendNo());
                    Message message = Message.builder()
                            .setAndroidConfig(androidConfig).putData("type", "acceptFriendInvite").putData("friendId", myInformation.getUserId()).putData("friendName", myInformation.getName()).setToken(registrationToken)
                            .build();
                    String response = FirebaseMessaging.getInstance().sendAsync(message).get();
                }

                result.putPOJO("data", searchResultBean);
            }
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/delete/{friendNo}", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer friendNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();

        try {
            FriendBean friendBean = friendService.getById(friendNo);
            friendBean.setStatus(3);
            friendService.update(friendBean);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
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
                arrayNode.addPOJO(fb);
            }

        } catch (Exception e) {
            result.put("result", false);
            result.putObject("data");
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/invite/notification", produces = "application/json")
    public String inviteNotification() throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<FriendBean> friendBeanList = friendService.inviteNotification();
            ArrayNode arrayNode = result.putArray("data");
            for (FriendBean friendBean : friendBeanList) {
                arrayNode.addPOJO(friendBean);
            }
            result.put("result", true);
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


    private AndroidConfig getAndroidConfig(String topic) {
        AndroidConfig build = AndroidConfig.builder()
                .setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().build()).build();
        return build;
    }
}
