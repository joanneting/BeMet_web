package tw.com.business_meet.service;

import tw.com.business_meet.bean.UserInformationBean;
import tw.com.business_meet.vo.UserInformation;

import java.util.List;

public interface UserInformationService {
    public List<UserInformationBean> search(UserInformationBean userInformationBean);
    public UserInformationBean add(UserInformationBean userInformationBean);
    public void update(UserInformationBean userInformationBean);
}
