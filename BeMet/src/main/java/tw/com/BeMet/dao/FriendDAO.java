package tw.com.BeMet.dao;

import tw.com.BeMet.bean.FriendBean;
import tw.com.BeMet.vo.Friend;

import java.util.List;

public interface FriendDAO extends BaseDAO<Friend> {
    public List<Friend> search(FriendBean matchedBean);
    public Friend searchAddData(FriendBean matchedBean);
    public List<FriendBean> searchInviteList(FriendBean matchedBean);
    public List<FriendBean> searchInviteNotification(String userId);
    public List<FriendBean> searchAllInvite();
}
