package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.FriendRemarkBean;
import tw.com.business_meet.dao.FriendRemarkDAO;
import tw.com.business_meet.service.FriendRemarkService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.FriendRemark;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendRemarkServiceImpl implements FriendRemarkService {
    @Autowired
    FriendRemarkDAO friendRemarkDAO;

    @Override
    public FriendRemarkBean add(FriendRemarkBean friendRemarkBean) throws Exception {
        FriendRemark friendRemark = new FriendRemark();
        friendRemarkBean.setCreateDate(new Date());
        BeanUtility.copyProperties(friendRemarkBean, friendRemark);
        friendRemark = friendRemarkDAO.saveAndReturn(friendRemark);
        BeanUtility.copyProperties(friendRemark, friendRemarkBean);
        return friendRemarkBean;
    }

    @Override
    public FriendRemarkBean update(FriendRemarkBean friendRemarkBean) throws Exception {
        FriendRemark friendRemark = friendRemarkDAO.getById(friendRemarkBean.getFriendRemarksNo());
        friendRemarkBean.setModifyDate(new Date());
        BeanUtility.copyProperties(friendRemarkBean, friendRemark);
        friendRemarkDAO.update(friendRemark);
        BeanUtility.copyProperties(friendRemark, friendRemarkBean);
        return friendRemarkBean;
    }

    @Override
    public List<FriendRemarkBean> search(FriendRemarkBean friendRemarkBean) throws Exception {
        List<FriendRemark> friendRemarkList = friendRemarkDAO.search(friendRemarkBean);
        List<FriendRemarkBean> friendRemarkBeanList = new ArrayList<>();
        for (FriendRemark friendRemark :
                friendRemarkList) {
            FriendRemarkBean frb = new FriendRemarkBean();
            BeanUtility.copyProperties(friendRemark, frb);
            friendRemarkBeanList.add(frb);
        }
        return friendRemarkBeanList;
    }

    @Override
    public List<FriendRemarkBean> searchAll() throws Exception {
        List<FriendRemark> friendRemarkList = friendRemarkDAO.searchAll();
        List<FriendRemarkBean> friendRemarkBeanList = new ArrayList<>();
        for (FriendRemark friendRemark :
                friendRemarkList) {
            FriendRemarkBean frb = new FriendRemarkBean();
            BeanUtility.copyProperties(friendRemark, frb);
            friendRemarkBeanList.add(frb);
        }
        return friendRemarkBeanList;
    }

    @Override
    public void delete(Integer friendRemarkNo) throws Exception {
        FriendRemark friendRemark = friendRemarkDAO.getById(friendRemarkNo);
        friendRemarkDAO.delete(friendRemark);
    }
}
