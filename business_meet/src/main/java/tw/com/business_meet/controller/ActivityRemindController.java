package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.business_meet.bean.ActivityRemindBean;
import tw.com.business_meet.service.ActivityRemindService;

import java.util.List;

@RestController
@RequestMapping("/activityremind")
public class ActivityRemindController {
    @Autowired
    ActivityRemindService activityRemindService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody ActivityRemindBean activityRemindBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityRemindBean arb = activityRemindService.add(activityRemindBean);
            result.putPOJO("data", arb);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody ActivityRemindBean activityRemindBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ActivityRemindBean arb = activityRemindService.update(activityRemindBean);
            result.put("result", true);
            result.putPOJO("data", arb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody ActivityRemindBean activityRemindBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<ActivityRemindBean> activityRemindBeanList = activityRemindService.search(activityRemindBean);
            result.put("result", true);
            ArrayNode arrayNode = result.putArray("data");
            for (ActivityRemindBean arb :
                    activityRemindBeanList) {
                arrayNode.addPOJO(arb);
            }
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);

    }

    @PostMapping(value = "/{activityRemindNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer activityRemindNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            activityRemindService.delete(activityRemindNo);
            result.put("result", true);

        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @GetMapping(value = "/set/firebase/message", produces = "applicaiton/json;charset=UTF-8")
    public void sendFirebaseMessage() throws Exception {
        String registrationToken = "dVVECXwPQ-KSGiBXbnJojv:APA91bGe3zpcJyE5aDkfQeGdKgQkSWnOvB0IVT7PGzsZPN6A795zOJUpKH2stWGExsF5JzqKIK6WeZ_NVT36s6B0jsLfPePYVeZOt-jcgexQ7ZD2DY_d2N1L96aPPcse88p8AkRTPrmC";

        // See documentation on defining a message payload.
        AndroidConfig androidConfig = getAndroidConfig("activity1");

        Message message = Message.builder()
                .setAndroidConfig(androidConfig).setNotification(
                        new Notification("測試", "測試")).setToken(registrationToken)
                .build();
        ;

        // Send a message to the device corresponding to the provided
        // registration token.
        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);

    }

    private AndroidConfig getAndroidConfig(String topic) {
        AndroidConfig build = AndroidConfig.builder()
                .setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().build()).build();
        return build;
    }
}
