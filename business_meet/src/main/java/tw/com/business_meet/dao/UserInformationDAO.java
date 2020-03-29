package tw.com.business_meet.dao;


import tw.com.business_meet.bean.UserInformationBean;
import tw.com.business_meet.vo.UserInformation;

import java.util.List;


public interface UserInformationDAO extends BaseDAO<UserInformation>{
    public List<UserInformation> search(UserInformationBean userInformationBean);
}
