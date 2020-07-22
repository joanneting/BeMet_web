package tw.com.business_meet.service;

import tw.com.business_meet.bean.FriendCustomizationBean;

import java.util.List;

public interface FriendCustomizationService {
    public FriendCustomizationBean add(FriendCustomizationBean friendCustomizationBean) throws Exception;

    public FriendCustomizationBean update(FriendCustomizationBean friendCustomizationBean) throws Exception;

    public List<FriendCustomizationBean> search(FriendCustomizationBean friendCustomizationBean) throws Exception;

    public List<FriendCustomizationBean> searchAll() throws Exception;

    public void delete(Integer friendCustomizationNo) throws Exception;
}
