package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.UserCustomizationBean;
import tw.com.business_meet.dao.UserCustomizationDAO;
import tw.com.business_meet.service.UserCustomizationService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.UserCustomization;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserCustomizationServiceImpl implements UserCustomizationService {
    @Autowired
    UserCustomizationDAO userCustomizationDAO;

    @Override
    public UserCustomizationBean add(UserCustomizationBean userCustomizationBean) throws Exception {
        UserCustomization userCustomization = new UserCustomization();
        userCustomizationBean.setCreateDate(new Date());
        BeanUtility.copyProperties(userCustomizationBean, userCustomization);
        userCustomization = userCustomizationDAO.saveAndReturn(userCustomization);
        BeanUtility.copyProperties(userCustomization, userCustomizationBean);
        return userCustomizationBean;
    }

    @Override
    public UserCustomizationBean update(UserCustomizationBean userCustomizationBean) throws Exception {
        UserCustomization userCustomization = userCustomizationDAO.getById(userCustomizationBean.getUserCustomizationNo());
        userCustomizationBean.setCreateDate(new Date());
        BeanUtility.copyProperties(userCustomizationBean, userCustomization);
        userCustomizationDAO.update(userCustomization);
        BeanUtility.copyProperties(userCustomization, userCustomizationBean);
        return userCustomizationBean;
    }

    @Override
    public List<UserCustomizationBean> search(UserCustomizationBean userCustomizationBean) throws Exception {
        List<UserCustomization> userCustomizationList = userCustomizationDAO.search(userCustomizationBean);
        List<UserCustomizationBean> userCustomizationBeanList = new ArrayList<>();
        for (UserCustomization userCustimization :
                userCustomizationList) {
            UserCustomizationBean ucb = new UserCustomizationBean();
            BeanUtility.copyProperties(userCustimization, ucb);
            userCustomizationBeanList.add(ucb);
        }
        return userCustomizationBeanList;
    }

    @Override
    public List<UserCustomizationBean> searchAll() throws Exception {
        List<UserCustomization> userCustomizationList = userCustomizationDAO.searchAll();
        List<UserCustomizationBean> userCustomizationBeanList = new ArrayList<>();
        for (UserCustomization userCustimization :
                userCustomizationList) {
            UserCustomizationBean ucb = new UserCustomizationBean();
            BeanUtility.copyProperties(userCustimization, ucb);
            userCustomizationBeanList.add(ucb);
        }
        return userCustomizationBeanList;
    }

    @Override
    public void delete(Integer userCustomizationNo) throws Exception {
        UserCustomization userCustomization = userCustomizationDAO.getById(userCustomizationNo);
        userCustomizationDAO.delete(userCustomization);
    }
}
