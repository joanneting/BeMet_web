package tw.com.business_meet.service;

import tw.com.business_meet.bean.FriendBean;

import java.util.List;

public interface FriendService {
    public List<FriendBean> search(FriendBean friendBean) throws Exception;

    public FriendBean add(FriendBean friendBean) throws Exception;

    public FriendBean update(FriendBean friendBean) throws Exception;

    public List<FriendBean> searchAll() throws Exception;
}
