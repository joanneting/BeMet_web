package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.FriendBean;
import tw.com.business_meet.dao.FriendDAO;
import tw.com.business_meet.vo.Friend;
import tw.com.business_meet.vo.UserInformation;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendDAOImpl extends BaseDAOImpl<Friend> implements FriendDAO {
    @Override
    public List<Friend> search(FriendBean friendBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Friend.class);
        String matchmakerId = friendBean.getMatchmakerId();
        String friendId = friendBean.getFriendId();
        String remark = friendBean.getRemark();
        System.out.println("matched search matchmakerId: " + matchmakerId);
        System.out.println("matched search friendId: " + friendId);
        if (matchmakerId != null && !matchmakerId.equals("")) {
            detachedCriteria.add(Restrictions.eq("matchmakerId", matchmakerId));
        }
        if (friendId != null && !friendId.equals("")) {
            detachedCriteria.add(Restrictions.eq("friendId", friendId));
        }
        if (remark != null && !remark.equals("")) {
            detachedCriteria.add(Restrictions.eq("remark", remark));
        }
        return (List<Friend>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
    @Override
    public List<FriendBean> searchInviteList(FriendBean friendBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Friend.class,"friend");
        detachedCriteria.createAlias("userInformationByFriendId","userInformation");
        String matchmakerId = friendBean.getMatchmakerId();
        String friendId = friendBean.getFriendId();
        String remark = friendBean.getRemark();
        System.out.println("matched search matchmakerId: " + matchmakerId);
        System.out.println("matched search friendId: " + friendId);
        if (matchmakerId != null && !matchmakerId.equals("")) {
            detachedCriteria.add(Restrictions.eq("matchmakerId", matchmakerId));
        }
        if (friendId != null && !friendId.equals("")) {
            detachedCriteria.add(Restrictions.eq("friendId", friendId));
        }
        if (remark != null && !remark.equals("")) {
            detachedCriteria.add(Restrictions.eq("remark", remark));
        }
        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.property("friend.friendId").as("friendId"));
        projectionList.add(Projections.property("friend.matchmakerId").as("matchmakerId"));
        projectionList.add(Projections.property("userInformation.name").as("userName"));
        projectionList.add(Projections.property("userInformation.avatar").as("userAvatar"));
        detachedCriteria.setProjection(projectionList);
        List<Object[]> objects = (List<Object[]>) this.getHibernateTemplate().findByCriteria(detachedCriteria);

        List<FriendBean> friendBeanList = new ArrayList<>();
        for (Object[] object : objects) {
            FriendBean resultBean = new FriendBean();
            System.out.println("object[2] = " + object[2]);
            resultBean.setFriendId(object[0].toString());
            resultBean.setMatchmakerId(object[1].toString());
            resultBean.setFriendName(object[2].toString());
            resultBean.setFriendAvatar(object[3].toString());
            friendBeanList.add(resultBean);
        }
        return friendBeanList;
    }
}
