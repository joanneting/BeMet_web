package tw.com.BeMet.dao;


import tw.com.BeMet.bean.UserInformationBean;
import tw.com.BeMet.vo.UserInformation;

import java.util.List;


public interface UserInformationDAO extends BaseDAO<UserInformation> {
    public List<UserInformation> search(UserInformationBean userInformationBean);
}
