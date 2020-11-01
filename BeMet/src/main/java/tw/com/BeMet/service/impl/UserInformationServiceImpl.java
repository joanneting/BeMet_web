package tw.com.BeMet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.BeMet.bean.UserInformationBean;
import tw.com.BeMet.dao.UserInformationDAO;
import tw.com.BeMet.service.UserInformationService;
import tw.com.BeMet.utils.BCryUtility;
import tw.com.BeMet.utils.BeanUtility;
import tw.com.BeMet.vo.UserInformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    UserInformationDAO userInformationDAO;

    @Override
    public List<UserInformationBean> search(UserInformationBean userInformationBean) {
        List<UserInformation> uiList = userInformationDAO.search(userInformationBean);
        List<UserInformationBean> uibList = new ArrayList<>();
        for (UserInformation ui : uiList) {
            UserInformationBean uib = new UserInformationBean();
            BeanUtility.copyProperties(ui, uib);
            uibList.add(uib);
        }

        return uibList;
    }

    @Override
    public UserInformationBean add(UserInformationBean userInformationBean) {
        UserInformation ui = new UserInformation();
        userInformationBean.setPassword(BCryUtility.encode(userInformationBean.getPassword()));
        BeanUtility.copyProperties(userInformationBean, ui);
        ui.setCreateDate(new Date());
        ui = userInformationDAO.saveAndReturn(ui);
        UserInformationBean uib = new UserInformationBean();
        BeanUtility.copyProperties(ui, uib);
        return uib;
    }

    @Override
    public UserInformationBean update(UserInformationBean userInformationBean) {
        UserInformation ui = userInformationDAO.getById(userInformationBean.getUserId());
        BeanUtility.copyProperties(userInformationBean, ui);
        ui.setModifyDate(new Date());
        userInformationDAO.update(ui);
        BeanUtility.copyProperties(ui, userInformationBean);
        return userInformationBean;
    }

    @Override
    public List<UserInformationBean> searchAll() {
        List<UserInformation> userInformationList = userInformationDAO.searchAll();
        List<UserInformationBean> userInformationBeanList = new ArrayList<>();
        for (UserInformation ui : userInformationList) {
            UserInformationBean uib = new UserInformationBean();
            BeanUtility.copyProperties(ui, uib);
            userInformationBeanList.add(uib);
        }
        return userInformationBeanList;
    }

    @Override
    public UserInformationBean getById(String userId) {
        UserInformation userInformation = userInformationDAO.getById(userId);
        if(userInformation == null){
            return null;
        }
        UserInformationBean userInformationBean = new UserInformationBean();
        BeanUtility.copyProperties(userInformation, userInformationBean);
        return userInformationBean;
    }

    @Override
    public UserInformationBean getByIdentifier(String identifier) {
        UserInformationBean userInformationBean = new UserInformationBean();
        userInformationBean.setIdentifier(identifier);
        List<UserInformation> userInformationBeanList = userInformationDAO.search(userInformationBean);
        if (userInformationBeanList.size() != 0) {
            BeanUtility.copyProperties(userInformationBeanList.get(0), userInformationBean);
            return userInformationBean;
        }
        return null;
    }
}
