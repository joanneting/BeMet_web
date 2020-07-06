package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.FriendBean;
import tw.com.business_meet.dao.FriendDAO;
import tw.com.business_meet.service.FriendService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.Friend;

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
    public FriendBean add(FriendBean friendBean) throws Exception {
        Friend f = new Friend();
        BeanUtility.copyProperties(friendBean, f);
        f.setCreateDate(new Date());


        Friend result = friendDAO.saveAndReturn(f);
        BeanUtility.copyProperties(result, friendBean);
        return friendBean;
    }

    @Override
    public FriendBean update(FriendBean friendBean) throws Exception {
        Friend f = new Friend();
        FriendBean searchBean = new FriendBean();
        searchBean.setMatchmakerId(friendBean.getMatchmakerId());
        searchBean.setFriendId(friendBean.getFriendId());
        List<Friend> friendList = friendDAO.search(searchBean);
        if (friendList.size() > 0) {
            f = friendList.get(0);
            BeanUtility.copyProperties(friendBean, f);
            f.setModifyDate(new Date());
        }
        friendDAO.update(f);
        BeanUtility.copyProperties(f, friendBean);
        return friendBean;
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
}
