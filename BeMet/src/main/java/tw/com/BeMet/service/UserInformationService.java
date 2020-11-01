package tw.com.BeMet.service;

import tw.com.BeMet.bean.UserInformationBean;

import java.util.List;

public interface UserInformationService {
    public List<UserInformationBean> search(UserInformationBean userInformationBean);

    public UserInformationBean add(UserInformationBean userInformationBean);

    public UserInformationBean update(UserInformationBean userInformationBean);

    public List<UserInformationBean> searchAll();

    public UserInformationBean getById(String userId);

    public UserInformationBean getByIdentifier(String blurtooth);
}
