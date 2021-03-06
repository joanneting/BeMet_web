package tw.com.business_meet.service;

import tw.com.business_meet.bean.FriendBean;
import tw.com.business_meet.vo.Friend;

import java.util.List;

public interface FriendService {
    public List<FriendBean> search(FriendBean friendBean) throws Exception;

    public List<FriendBean> searchInviteList(FriendBean friendBean) throws Exception;

    public FriendBean add(FriendBean friendBean) throws Exception;

    public FriendBean update(FriendBean friendBean) throws Exception;

    public FriendBean getById(Integer friendNo) throws Exception;

    public void delete(Integer friendNo) throws Exception;

    public List<FriendBean> searchAll() throws Exception;
    public List<FriendBean> inviteNotification() throws Exception;
    public List<FriendBean> searchAllInvite() throws Exception;
    public FriendBean searchAddData(FriendBean friendBean) throws Exception;
}
