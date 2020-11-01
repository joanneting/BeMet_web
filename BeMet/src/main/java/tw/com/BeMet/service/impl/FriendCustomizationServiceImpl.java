package tw.com.BeMet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.BeMet.bean.FriendCustomizationBean;
import tw.com.BeMet.dao.FriendCustomizationDAO;
import tw.com.BeMet.service.FriendCustomizationService;
import tw.com.BeMet.utils.BeanUtility;
import tw.com.BeMet.vo.FriendCustomization;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendCustomizationServiceImpl implements FriendCustomizationService {
    @Autowired
    FriendCustomizationDAO friendCustomizationDAO;

    @Override
    public FriendCustomizationBean add(FriendCustomizationBean friendCustomizationBean) throws Exception {
        FriendCustomization friendCustomization = new FriendCustomization();
        friendCustomizationBean.setCreateDate(new Date());
        BeanUtility.copyProperties(friendCustomizationBean, friendCustomization);
        friendCustomization = friendCustomizationDAO.saveAndReturn(friendCustomization);
        BeanUtility.copyProperties(friendCustomization, friendCustomizationBean);
        return friendCustomizationBean;
    }

    @Override
    public FriendCustomizationBean update(FriendCustomizationBean friendCustomizationBean) throws Exception {
        FriendCustomization friendCustomization = friendCustomizationDAO.getById(friendCustomizationBean.getFriendCustomizationNo());
        friendCustomizationBean.setModifyDate(new Date());
        BeanUtility.copyProperties(friendCustomizationBean, friendCustomization);
        friendCustomizationDAO.update(friendCustomization);
        BeanUtility.copyProperties(friendCustomizationBean, friendCustomization);
        return friendCustomizationBean;
    }

    @Override
    public List<FriendCustomizationBean> search(FriendCustomizationBean friendCustomizationBean) throws Exception {
        List<FriendCustomization> friendCustomizationList = friendCustomizationDAO.search(friendCustomizationBean);
        List<FriendCustomizationBean> friendCustomizationBeanList = new ArrayList<>();
        for (FriendCustomization friendCustomization :
                friendCustomizationList) {
            FriendCustomizationBean fcb = new FriendCustomizationBean();
            BeanUtility.copyProperties(friendCustomization, fcb);
            friendCustomizationBeanList.add(fcb);
        }
        return friendCustomizationBeanList;
    }

    @Override
    public List<FriendCustomizationBean> searchAll() throws Exception {
        List<FriendCustomization> friendCustomizationList = friendCustomizationDAO.searchAll();
        List<FriendCustomizationBean> friendCustomizationBeanList = new ArrayList<>();
        for (FriendCustomization friendCustomization :
                friendCustomizationList) {
            FriendCustomizationBean fcb = new FriendCustomizationBean();
            BeanUtility.copyProperties(friendCustomization, fcb);
            friendCustomizationBeanList.add(fcb);
        }
        return friendCustomizationBeanList;
    }

    @Override
    public void delete(Integer friendCustomizationNo) throws Exception {
        FriendCustomization friendCustomization = friendCustomizationDAO.getById(friendCustomizationNo);
        friendCustomizationDAO.delete(friendCustomization);
    }
}
