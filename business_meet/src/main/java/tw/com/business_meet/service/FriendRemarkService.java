package tw.com.business_meet.service;

import tw.com.business_meet.bean.FriendRemarkBean;

import java.util.List;

public interface FriendRemarkService {
    public FriendRemarkBean add(FriendRemarkBean friendRemarkBean) throws Exception;

    public FriendRemarkBean update(FriendRemarkBean friendRemarkBean) throws Exception;

    public List<FriendRemarkBean> search(FriendRemarkBean friendRemarkBean) throws Exception;

    public List<FriendRemarkBean> searchAll() throws Exception;

    public void delete(Integer friendRemarkNo) throws Exception;
}
