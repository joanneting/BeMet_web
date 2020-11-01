package tw.com.BeMet.filter;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tw.com.BeMet.bean.FriendBean;
import tw.com.BeMet.service.FriendService;

import java.util.List;

@Component
public class ScheduledTask {
    @Autowired
    FriendService friendService;
    @Scheduled(fixedRate = 360000)
    public void sendInviteMessage() throws Exception {
        List<FriendBean> friendBeanList = friendService.searchAllInvite();
        for (FriendBean friendBean : friendBeanList) {
            if (friendBean.getFirebaseToken() != null) {
                System.out.println("friendBean.getMatchmakerId() = " + friendBean.getMatchmakerId());
                String registrationToken = friendBean.getFirebaseToken();
                // See documentation on defining a message payload.
                AndroidConfig androidConfig = getAndroidConfig("friend" + friendBean.getFriendNo());
                Message message = Message.builder()
                        .setAndroidConfig(androidConfig).putData("type", "friendInvite").putData("friendId", friendBean.getMatchmakerId()).putData("friendName", friendBean.getFriendName()).setToken(registrationToken)
                        .build();
                // Send a message to the device corresponding to the provided
                // registration token.
                String response = FirebaseMessaging.getInstance().sendAsync(message).get();
                // Response is a message ID string.
                System.out.println("Successfully sent message: " + response);
            }
        }
    }


    private AndroidConfig getAndroidConfig(String topic) {
        AndroidConfig build = AndroidConfig.builder()
                .setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().build()).build();
        return build;
    }
}
