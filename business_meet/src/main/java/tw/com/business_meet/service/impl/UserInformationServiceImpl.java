package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.UserInformationBean;
import tw.com.business_meet.dao.UserInformationDAO;
import tw.com.business_meet.service.UserInformationService;
import tw.com.business_meet.utils.BCryUtility;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.UserInformation;

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
        System.out.println("userInformationBean.getName() : " + uib.getName());
        System.out.println("uib.getRoleNo() = " + uib.getRoleNo());
        return uib;
    }

    @Override
    public UserInformationBean update(UserInformationBean userInformationBean) {
        UserInformation ui = userInformationDAO.getById(userInformationBean.getUserId());
        System.out.println("ui.getCreateDate() = " + ui.getCreateDate());
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
        UserInformationBean userInformationBean = new UserInformationBean();
        BeanUtility.copyProperties(userInformation, userInformationBean);
        return userInformationBean;
    }

    @Override
    public UserInformationBean getByBluetooth(String bluetooth) {
        UserInformationBean userInformationBean = new UserInformationBean();
        userInformationBean.setBluetooth(bluetooth);
        List<UserInformation> userInformationBeanList = userInformationDAO.search(userInformationBean);
        if (userInformationBeanList.size() != 0) {
            BeanUtility.copyProperties(userInformationBeanList.get(0), userInformationBean);
            System.out.println("userinformationServicce");
            System.out.println(userInformationBean.getUserId());
            return userInformationBean;
        }
        return null;
    }
}
