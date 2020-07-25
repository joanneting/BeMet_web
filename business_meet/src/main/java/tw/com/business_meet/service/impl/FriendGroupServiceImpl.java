package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.FriendGroupBean;
import tw.com.business_meet.dao.FriendGroupDAO;
import tw.com.business_meet.service.FriendGroupService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.FriendGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendGroupServiceImpl implements FriendGroupService {
    @Autowired
    FriendGroupDAO friendGroupDAO;

    @Override
    public FriendGroupBean add(FriendGroupBean friendGroupBean) throws Exception {
        FriendGroup friendGroup = new FriendGroup();
        friendGroupBean.setCreateDate(new Date());
        BeanUtility.copyProperties(friendGroupBean, friendGroup);
        friendGroup = friendGroupDAO.saveAndReturn(friendGroup);
        BeanUtility.copyProperties(friendGroup, friendGroupBean);
        return friendGroupBean;
    }

    @Override
    public FriendGroupBean update(FriendGroupBean friendGroupBean) throws Exception {
        FriendGroup friendGroup = friendGroupDAO.getById(friendGroupBean.getFriendGroupNo());
        friendGroupBean.setModifyDate(new Date());
        BeanUtility.copyProperties(friendGroupBean, friendGroup);
        friendGroupDAO.update(friendGroup);
        BeanUtility.copyProperties(friendGroup, friendGroupBean);
        return friendGroupBean;
    }

    @Override
    public List<FriendGroupBean> search(FriendGroupBean friendGroupBean) throws Exception {
        List<FriendGroup> friendGroupList = friendGroupDAO.search(friendGroupBean);
        List<FriendGroupBean> friendGroupBeanList = new ArrayList<>();
        for (FriendGroup friendGroup :
                friendGroupList) {
            FriendGroupBean fgb = new FriendGroupBean();
            BeanUtility.copyProperties(friendGroup, fgb);
            friendGroupBeanList.add(fgb);
        }
        return friendGroupBeanList;
    }

    @Override
    public List<FriendGroupBean> searchAll() throws Exception {
        List<FriendGroup> friendGroupList = friendGroupDAO.searchAll();
        List<FriendGroupBean> friendGroupBeanList = new ArrayList<>();
        for (FriendGroup friendGroup :
                friendGroupList) {
            FriendGroupBean fgb = new FriendGroupBean();
            BeanUtility.copyProperties(friendGroup, fgb);
            friendGroupBeanList.add(fgb);
        }
        return friendGroupBeanList;
    }

    @Override
    public void delete(Integer friendGroupNo) throws Exception {
        FriendGroup friendGroup = friendGroupDAO.getById(friendGroupNo);
        friendGroupDAO.delete(friendGroup);
    }
}