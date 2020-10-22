package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.FriendBean;
import tw.com.business_meet.dao.FriendDAO;
import tw.com.business_meet.service.FriendService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.Friend;
import tw.com.business_meet.vo.UserInformation;

import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendDAO friendDAO;

    @Override
    public List<FriendBean> search(FriendBean friendBean) throws Exception {

        List<Friend> fList = friendDAO.search(friendBean);
        List<FriendBean> fbList = new ArrayList<>();
        for (Friend f : fList) {
            FriendBean fb = new FriendBean();
            BeanUtility.copyProperties(f, fb);
            fbList.add(fb);
        }
        return fbList;
    }
    @Override
    public FriendBean searchAddData(FriendBean friendBean) throws Exception {

        Friend friend = friendDAO.searchAddData(friendBean);
        if(friend != null) {
            BeanUtility.copyProperties(friend, friendBean);
            return friendBean;
        }
        return null;
    }


 @Override
    public List<FriendBean> searchInviteList(FriendBean friendBean) throws Exception {

        List<FriendBean> fbList = friendDAO.searchInviteList(friendBean);

        return fbList;
    }


    @Override
    public FriendBean add(FriendBean friendBean) throws Exception {
        Friend f = new Friend();
        BeanUtility.copyProperties(friendBean, f);
        f.setCreateDate(new Date());
        System.out.println("f.getFriendNo()" + f.getFriendNo());

        Friend result = friendDAO.saveAndReturn(f);
        BeanUtility.copyProperties(result, friendBean);
        return friendBean;
    }

    @Override
    public FriendBean update(FriendBean friendBean) throws Exception {
        FriendBean searchBean = new FriendBean();
        searchBean.setMatchmakerId(friendBean.getMatchmakerId());
        searchBean.setFriendId(friendBean.getFriendId());
        Friend friend = friendDAO.searchAddData(searchBean);
        if (friend!=null) {
            BeanUtility.copyProperties(friendBean, friend);
            friend.setModifyDate(new Date());
            System.out.println("f.getStatus() = " + friend.getStatus());
            friendDAO.update(friend);
            BeanUtility.copyProperties(friend, friendBean);
            return friendBean;
        }
        return null;
    }

    @Override
    public FriendBean getById(Integer friendNo) throws Exception {
        Friend friend = friendDAO.getById(friendNo);
        FriendBean friendBean = new FriendBean();
        BeanUtility.copyProperties(friend,friendBean);
        return friendBean;
    }

    @Override
    public void delete(Integer friendNo) throws Exception {
        Friend friend = friendDAO.getById(friendNo);
        friendDAO.delete(friend);
    }

    @Override
    public List<FriendBean> searchAll() throws Exception {
        List<Friend> friendList = friendDAO.searchAll();
        List<FriendBean> friendBeanList = new ArrayList<>();
        for (Friend f : friendList) {
            FriendBean friendBean = new FriendBean();
            BeanUtility.copyProperties(f, friendBean);
            friendBeanList.add(friendBean);
        }
        return friendBeanList;
    }

    @Override
    public List<FriendBean> inviteNotification() throws Exception {
        UserInformation userInformation = (UserInformation)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<FriendBean> friendBeanList = friendDAO.searchInviteNotification(userInformation.getUserId());
        return friendBeanList;
    }
    @Override
    public List<FriendBean> searchAllInvite() throws Exception {
        List<FriendBean> friendBeanList = friendDAO.searchAllInvite();
        return friendBeanList;
    }
}
