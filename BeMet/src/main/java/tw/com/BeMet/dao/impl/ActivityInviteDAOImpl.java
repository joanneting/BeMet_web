package tw.com.BeMet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.BeMet.bean.ActivityInviteBean;
import tw.com.BeMet.dao.ActivityInviteDAO;
import tw.com.BeMet.vo.ActivityInvite;

import java.util.List;

@Repository
public class ActivityInviteDAOImpl extends BaseDAOImpl<ActivityInvite> implements ActivityInviteDAO {
    @Override
    public List<ActivityInvite> search(ActivityInviteBean activityInviteBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ActivityInvite.class);
        Integer activityNo = activityInviteBean.getActivityNo();
        Integer status = activityInviteBean.getStatus();
        String userId = activityInviteBean.getUserId();
        if (activityNo != null && activityNo != 0) {
            detachedCriteria.add(Restrictions.eq("activityNo", activityNo));
        }
        if (status != null && status != 0) {
            detachedCriteria.add(Restrictions.eq("status", status));
        }
        if (userId != null && !userId.equals("")) {
            detachedCriteria.add(Restrictions.eq("userId", userId));
        }

        return (List<ActivityInvite>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
    @Override
    public List<ActivityInvite> searchAccept(ActivityInviteBean activityInviteBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ActivityInvite.class);
        Integer activityNo = activityInviteBean.getActivityNo();
        Integer status = activityInviteBean.getStatus();
        String userId = activityInviteBean.getUserId();
        if (activityNo != null && activityNo != 0) {
            detachedCriteria.add(Restrictions.eq("activityNo", activityNo));
        }
            detachedCriteria.add(Restrictions.eq("status", 2));
        if (userId != null && !userId.equals("")) {
            detachedCriteria.add(Restrictions.eq("userId", userId));
        }
        return (List<ActivityInvite>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}


