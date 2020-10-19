package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.FriendGroupBean;
import tw.com.business_meet.dao.FriendGroupDAO;
import tw.com.business_meet.vo.FriendGroup;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendGroupDAOImpl extends BaseDAOImpl<FriendGroup> implements FriendGroupDAO {
    @Override
    public List<FriendGroup> search(FriendGroupBean friendGroupBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FriendGroup.class);
        detachedCriteria.createAlias("groupsByGroupNo","groups");
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
        detachedCriteria.createAlias("groupsByGroupNo","groups");
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

        projectionList.add(Projections.property("friendGroup.groupNo").as("groupNo"));
        projectionList.add(Projections.property("groups.name").as("groupName"));
        projectionList.add(Projections.count("friendGroup.groupNo"));
        projectionList.add(Projections.groupProperty("friendGroup.groupNo"));
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
}
