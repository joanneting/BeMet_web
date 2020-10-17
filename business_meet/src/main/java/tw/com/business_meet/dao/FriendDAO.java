package tw.com.business_meet.dao;

import tw.com.business_meet.bean.FriendBean;
import tw.com.business_meet.vo.Friend;

import java.util.List;

public interface FriendDAO extends BaseDAO<Friend> {
    public List<Friend> search(FriendBean matchedBean);
    public Friend searchAddData(FriendBean matchedBean);
    public List<FriendBean> searchInviteList(FriendBean matchedBean);
    public List<FriendBean> searchInviteNotification(String userId);
}
