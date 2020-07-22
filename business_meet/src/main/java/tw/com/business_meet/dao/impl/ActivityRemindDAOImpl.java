package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.ActivityRemindBean;
import tw.com.business_meet.dao.ActivityRemindDAO;
import tw.com.business_meet.vo.ActivityRemind;

import java.util.Date;
import java.util.List;

@Repository
public class ActivityRemindDAOImpl extends BaseDAOImpl<ActivityRemind> implements ActivityRemindDAO {
    @Override
    public List<ActivityRemind> search(ActivityRemindBean activityRemindBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ActivityRemind.class);
        Integer activityNo = activityRemindBean.getActivityNo();
        Date time = activityRemindBean.getTime();
        if (activityNo != null && activityNo != 0) {
            detachedCriteria.add(Restrictions.eq("activityNo", activityNo));
        }
        return (List<ActivityRemind>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
