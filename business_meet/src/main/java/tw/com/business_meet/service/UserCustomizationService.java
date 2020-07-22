package tw.com.business_meet.service;

import tw.com.business_meet.bean.UserCustomizationBean;

import java.util.List;

public interface UserCustomizationService {
    public UserCustomizationBean add(UserCustomizationBean userCustomizationBean) throws Exception;

    public UserCustomizationBean update(UserCustomizationBean userCustomizationBean) throws Exception;

    public List<UserCustomizationBean> search(UserCustomizationBean userCustomizationBean) throws Exception;

    public List<UserCustomizationBean> searchAll() throws Exception;

    public void delete(Integer userCustomizationNo) throws Exception;
}
