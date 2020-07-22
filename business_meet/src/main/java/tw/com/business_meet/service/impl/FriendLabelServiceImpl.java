package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.FriendLabelBean;
import tw.com.business_meet.dao.FriendLabelDAO;
import tw.com.business_meet.service.FriendLabelService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.FriendLabel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendLabelServiceImpl implements FriendLabelService {
    @Autowired
    FriendLabelDAO friendLabelDAO;

    @Override
    public FriendLabelBean add(FriendLabelBean friendLabelBean) throws Exception {
        FriendLabel friendLabel = new FriendLabel();
        friendLabelBean.setCreateDate(new Date());
        BeanUtility.copyProperties(friendLabelBean, friendLabel);
        friendLabel = friendLabelDAO.saveAndReturn(friendLabel);
        BeanUtility.copyProperties(friendLabelBean, friendLabel);
        return friendLabelBean;
    }

    @Override
    public FriendLabelBean update(FriendLabelBean friendLabelBean) throws Exception {
        FriendLabel friendLabel = friendLabelDAO.getById(friendLabelBean.getFriendLabelNo());
        friendLabelBean.setModifyDate(new Date());
        BeanUtility.copyProperties(friendLabelBean, friendLabel);
        friendLabelDAO.update(friendLabel);
        BeanUtility.copyProperties(friendLabel, friendLabelBean);
        return friendLabelBean;
    }

    @Override
    public List<FriendLabelBean> searchAll() throws Exception {
        List<FriendLabel> friendLabelList = friendLabelDAO.searchAll();
        List<FriendLabelBean> friendLabelBeanList = new ArrayList<>();
        for (FriendLabel friendLabel :
                friendLabelList) {
            FriendLabelBean friendLabelBean = new FriendLabelBean();
            BeanUtility.copyProperties(friendLabel, friendLabelBean);
            friendLabelBeanList.add(friendLabelBean);
        }
        return friendLabelBeanList;
    }

    @Override
    public List<FriendLabelBean> search(FriendLabelBean friendLabelBean) throws Exception {
        List<FriendLabel> friendLabelList = friendLabelDAO.searchAll();
        List<FriendLabelBean> friendLabelBeanList = new ArrayList<>();
        for (FriendLabel friendLabel :
                friendLabelList) {
            FriendLabelBean flb = new FriendLabelBean();
            BeanUtility.copyProperties(friendLabel, flb);
            friendLabelBeanList.add(flb);
        }
        return friendLabelBeanList;
    }

    @Override
    public void delete(Integer friendLabelNo) throws Exception {
        FriendLabel friendLabel = friendLabelDAO.getById(friendLabelNo);
        friendLabelDAO.delete(friendLabel);
    }
}
