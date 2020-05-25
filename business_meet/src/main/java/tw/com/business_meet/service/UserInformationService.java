package tw.com.business_meet.service;

import tw.com.business_meet.bean.UserInformationBean;

import java.util.List;

public interface UserInformationService {
    public List<UserInformationBean> search(UserInformationBean userInformationBean);
    public UserInformationBean add(UserInformationBean userInformationBean);
    public void update(UserInformationBean userInformationBean);
    public List<UserInformationBean> searchAll();
    public UserInformationBean getById(String blueTooth);
}
