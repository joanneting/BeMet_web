package tw.com.BeMet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tw.com.BeMet.bean.FriendGroupBean;
import tw.com.BeMet.dao.FriendGroupDAO;
import tw.com.BeMet.service.FriendGroupService;
import tw.com.BeMet.utils.BeanUtility;
import tw.com.BeMet.vo.FriendGroup;
import tw.com.BeMet.vo.UserInformation;

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
    public List<FriendGroupBean> searchFriendByGroup(Integer groupNo) throws Exception {
        FriendGroupBean friendGroupBean = new FriendGroupBean();
        friendGroupBean.setGroupNo(groupNo);
        List<FriendGroupBean> friendGroupBeanList = friendGroupDAO.searchFriendByGroup(friendGroupBean);

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

    @Override
    public List<FriendGroupBean> searchGroupCount() throws Exception {
        FriendGroupBean friendGroupBean = new FriendGroupBean();
        UserInformation userInformation = (UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        friendGroupBean.setUserId(userInformation.getUserId());
        List<FriendGroupBean> friendGroupBeanList = friendGroupDAO.searchGroupCount(friendGroupBean);
        return friendGroupBeanList;
    }
}
