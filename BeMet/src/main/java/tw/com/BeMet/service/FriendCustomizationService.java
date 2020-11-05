package tw.com.BeMet.service;

import tw.com.BeMet.bean.FriendCustomizationBean;

import java.util.List;

public interface FriendCustomizationService {
    public FriendCustomizationBean add(FriendCustomizationBean friendCustomizationBean) throws Exception;

    public FriendCustomizationBean update(FriendCustomizationBean friendCustomizationBean) throws Exception;

    public List<FriendCustomizationBean> search(FriendCustomizationBean friendCustomizationBean) throws Exception;

    public List<FriendCustomizationBean> searchAll() throws Exception;

    public void delete(Integer friendCustomizationNo) throws Exception;
}
