package tw.com.business_meet.service;

import tw.com.business_meet.bean.FriendLabelBean;

import java.util.List;

public interface FriendLabelService {
    public FriendLabelBean add(FriendLabelBean friendLabelBean) throws Exception;

    public FriendLabelBean update(FriendLabelBean friendLabelBean) throws Exception;

    public List<FriendLabelBean> searchAll() throws Exception;

    public List<FriendLabelBean> search(FriendLabelBean friendLabelBean) throws Exception;

    public void delete(Integer friendLabelNo) throws Exception;
}
