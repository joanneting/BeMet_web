package tw.com.BeMet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.BeMet.bean.FriendBean;
import tw.com.BeMet.dao.FriendDAO;
import tw.com.BeMet.vo.Friend;

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
        if (matchmakerId != null && !matchmakerId.equals("")) {
            detachedCriteria.add(Restrictions.eq("matchmakerId", matchmakerId));
        }
        if (friendId != null && !friendId.equals("")) {
            detachedCriteria.add(Restrictions.eq("friendId", friendId));
        }
        if (remark != null && !remark.equals("")) {
            detachedCriteria.add(Restrictions.eq("remark", remark));
        }
        detachedCriteria.add(Restrictions.eq("status",2));
        return (List<Friend>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
    @Override
    public Friend searchAddData(FriendBean friendBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Friend.class);
        String matchmakerId = friendBean.getMatchmakerId();
        String friendId = friendBean.getFriendId();
        String remark = friendBean.getRemark();

        if (matchmakerId != null && !matchmakerId.equals("")) {
            detachedCriteria.add(Restrictions.eq("matchmakerId", matchmakerId));
        }
        if (friendId != null && !friendId.equals("")) {
            detachedCriteria.add(Restrictions.eq("friendId", friendId));
        }
        if (remark != null && !remark.equals("")) {
            detachedCriteria.add(Restrictions.eq("remark", remark));
        }
        List<Friend> addData =( List<Friend>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(addData.size() > 0) {
            return addData.get(0);
        }else {
            return null;
        }
    }
    @Override
    public List<FriendBean> searchInviteList(FriendBean friendBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Friend.class,"friend");
        detachedCriteria.createAlias("userInformationByFriendId","userInformation");
        String matchmakerId = friendBean.getMatchmakerId();
        String friendId = friendBean.getFriendId();
        String remark = friendBean.getRemark();
        if (matchmakerId != null && !matchmakerId.equals("")) {
            detachedCriteria.add(Restrictions.eq("matchmakerId", matchmakerId));
        }
        if (friendId != null && !friendId.equals("")) {
            detachedCriteria.add(Restrictions.eq("friendId", friendId));
        }
        if (remark != null && !remark.equals("")) {
            detachedCriteria.add(Restrictions.eq("remark", remark));
        }
        detachedCriteria.add(Restrictions.eq("status",2));
        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.property("friend.friendId").as("friendId"));
        projectionList.add(Projections.property("friend.matchmakerId").as("matchmakerId"));
        projectionList.add(Projections.property("userInformation.name").as("userName"));
        projectionList.add(Projections.property("userInformation.avatar").as("userAvatar"));
        projectionList.add(Projections.property("userInformation.firebaseToken").as("firebaseToken"));
        detachedCriteria.setProjection(projectionList);
        List<Object[]> objects = (List<Object[]>) this.getHibernateTemplate().findByCriteria(detachedCriteria);

        List<FriendBean> friendBeanList = new ArrayList<>();
        for (Object[] object : objects) {
            FriendBean resultBean = new FriendBean();
            resultBean.setFriendId(object[0].toString());
            resultBean.setMatchmakerId(object[1].toString());
            resultBean.setFriendName(object[2].toString());
            resultBean.setFriendAvatar(object[3].toString());
            resultBean.setFirebaseToken(object[4]==null?null:object[4].toString());
            friendBeanList.add(resultBean);
        }
        return friendBeanList;
    }

    @Override
    public List<FriendBean> searchInviteNotification(String userId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Friend.class,"friend");
        detachedCriteria.createAlias("userInformationByFriendId","userInformation");

        detachedCriteria.add(Restrictions.eq("friendId",userId));
        detachedCriteria.add(Restrictions.eq("status",1));

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
            resultBean.setFriendId(object[0].toString());
            resultBean.setMatchmakerId(object[1].toString());
            resultBean.setFriendName(object[2].toString());
            resultBean.setFriendAvatar(object[3].toString());
            friendBeanList.add(resultBean);
        }
        return friendBeanList;
    }
    @Override
    public List<FriendBean> searchAllInvite( ) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Friend.class,"friend");
        detachedCriteria.createAlias("userInformationByFriendId","friendUserInformation");
        detachedCriteria.createAlias("userInformationByMatchmakerId","matchmakerUserInformation");

        detachedCriteria.add(Restrictions.eq("status",1));

        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.property("friend.friendId").as("friendId"));
        projectionList.add(Projections.property("friend.matchmakerId").as("matchmakerId"));
        projectionList.add(Projections.property("matchmakerUserInformation.name").as("matchmakerName"));
        projectionList.add(Projections.property("friendUserInformation.firebaseToken").as("firebaseToken"));
        detachedCriteria.setProjection(projectionList);
        List<Object[]> objects = (List<Object[]>) this.getHibernateTemplate().findByCriteria(detachedCriteria);

        List<FriendBean> friendBeanList = new ArrayList<>();
        for (Object[] object : objects) {
            FriendBean resultBean = new FriendBean();
            resultBean.setFriendId(object[0].toString());
            resultBean.setMatchmakerId(object[1].toString());
            resultBean.setFriendName(object[2].toString());
            resultBean.setFirebaseToken(object[3]==null?null:object[3].toString());
            friendBeanList.add(resultBean);
        }
        return friendBeanList;
    }
}
