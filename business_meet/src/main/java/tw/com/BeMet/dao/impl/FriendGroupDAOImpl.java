package tw.com.BeMet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import tw.com.BeMet.bean.FriendBean;
import tw.com.BeMet.bean.FriendGroupBean;
import tw.com.BeMet.dao.FriendGroupDAO;
import tw.com.BeMet.vo.FriendGroup;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendGroupDAOImpl extends BaseDAOImpl<FriendGroup> implements FriendGroupDAO {
    @Override
    public List<FriendGroup> search(FriendGroupBean friendGroupBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FriendGroup.class);
        Integer friendNo = friendGroupBean.getFriendNo();
        Integer groupNo = friendGroupBean.getGroupNo();
        if (friendNo != null && friendNo != 0) {
            detachedCriteria.add(Restrictions.eq("friendNo", friendNo));
        }
        if (groupNo != null && groupNo != 0) {
            detachedCriteria.add(Restrictions.eq("groupNo", groupNo));
        }

        return (List<FriendGroup>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
    @Override
    public List<FriendGroupBean> searchGroupCount(FriendGroupBean friendGroupBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FriendGroup.class,"friendGroup");

        detachedCriteria.createAlias("friendByFriendNo","friend",JoinType.RIGHT_OUTER_JOIN,Restrictions.eq("status",2));
        detachedCriteria.createAlias("groupsByGroupNo","groups", JoinType.RIGHT_OUTER_JOIN);
        Integer friendNo = friendGroupBean.getFriendNo();
        Integer groupNo = friendGroupBean.getGroupNo();
        String userId = friendGroupBean.getUserId();
        if (friendNo != null && friendNo != 0) {
            detachedCriteria.add(Restrictions.eq("friendNo", friendNo));
        }
        if (groupNo != null && groupNo != 0) {
            detachedCriteria.add(Restrictions.eq("groupNo", groupNo));
        }
        if (userId != null && !userId.equals("")) {
            detachedCriteria.add(Restrictions.eq("groups.userId",userId));
        }

        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.property("groups.groupNo").as("groupNo"));
        projectionList.add(Projections.property("groups.name").as("groupName"));
        projectionList.add(Projections.count("friendGroup.groupNo"));
        projectionList.add(Projections.groupProperty("groups.groupNo"));
        projectionList.add(Projections.groupProperty("groups.name"));
        detachedCriteria.setProjection(projectionList);

        List<Object[]> objects = (List<Object[]>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        List<FriendGroupBean> friendGroupBeanList = new ArrayList<>();
        for (Object[] object : objects) {
            FriendGroupBean insertFriendGroupBean = new FriendGroupBean();
            insertFriendGroupBean.setGroupNo(Integer.parseInt(object[0].toString()));
            insertFriendGroupBean.setGroupName(object[1].toString());
            insertFriendGroupBean.setCount(Integer.parseInt(object[2].toString()));
            friendGroupBeanList.add(insertFriendGroupBean);
        }
        return friendGroupBeanList;
    }
    @Override
    public List<FriendGroupBean> searchFriendByGroup(FriendGroupBean friendGroupBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FriendGroup.class,"friendGroup");
        detachedCriteria.createAlias("friendByFriendNo","friend",JoinType.INNER_JOIN,Restrictions.eq("status",2));
        detachedCriteria.createAlias("friend.userInformationByFriendId","userInformation");
        Integer groupNo = friendGroupBean.getGroupNo();

        if (groupNo != null && groupNo != 0) {
            detachedCriteria.add(Restrictions.eq("groupNo", groupNo));
        }

        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.property("friendNo").as("friendNo"));
        projectionList.add(Projections.property("userInformation.name").as("userName"));
        projectionList.add(Projections.property("userInformation.profession").as("profession"));
        projectionList.add(Projections.property("userInformation.avatar").as("avatar"));
        projectionList.add(Projections.property("friend.friendId").as("friendId"));
        detachedCriteria.setProjection(projectionList);

        List<Object[]> objects = (List<Object[]>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        List<FriendGroupBean> friendGroupBeanList = new ArrayList<>();
        for (Object[] object : objects) {
            FriendGroupBean insertFriendGroupBean = new FriendGroupBean();
            FriendBean friendBean = new FriendBean();
            friendBean.setFriendNo(Integer.parseInt(object[0].toString()));
            friendBean.setFriendName(object[1].toString());
            friendBean.setFriendProfession(object[2].toString());
            friendBean.setFriendAvatar(object[3].toString());
            friendBean.setFriendId(object[4].toString());
            insertFriendGroupBean.setFriendBean(friendBean);
            friendGroupBeanList.add(insertFriendGroupBean);
        }
        return friendGroupBeanList;
    }
}
