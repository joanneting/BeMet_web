package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.ActivityInviteBean;
import tw.com.business_meet.dao.ActivityInviteDAO;
import tw.com.business_meet.vo.ActivityInvite;

import java.util.List;

@Repository
public class ActivityInviteDAOImpl extends BaseDAOImpl<ActivityInvite> implements ActivityInviteDAO {
    @Override
    public List<ActivityInvite> search(ActivityInviteBean activityInviteBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ActivityInvite.class);
        Integer activityNo = activityInviteBean.getActivityNo();
        Integer statusCode = activityInviteBean.getStatusCode();
        String userId = activityInviteBean.getUserId();
        if (activityNo != null && activityNo != 0) {
            detachedCriteria.add(Restrictions.eq("activityNo", activityNo));
        }
        if (statusCode != null && statusCode != 0) {
            detachedCriteria.add(Restrictions.eq("statusCode", statusCode));
        }
        if (userId != null && !userId.equals("")) {
            detachedCriteria.add(Restrictions.eq("userId", userId));
        }

        return (List<ActivityInvite>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
