package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.ActivityDateBean;
import tw.com.business_meet.dao.ActivityDateDAO;
import tw.com.business_meet.vo.ActivityDate;

import java.util.Date;
import java.util.List;

@Repository
public class ActivityDateDAOImpl extends BaseDAOImpl<ActivityDate> implements ActivityDateDAO {
    @Override
    public List<ActivityDate> search(ActivityDateBean activityDateBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ActivityDate.class);
        Integer activityNo = activityDateBean.getActivityNo();
        Date endDate = activityDateBean.getEndDate();
        Date startDate = activityDateBean.getStartDate();
        if (activityNo != null && activityNo !=0) {
            detachedCriteria.add(Restrictions.eq("activity_no",activityNo));
        }
        if (endDate != null ) {
            detachedCriteria.add(Restrictions.le("end_date",endDate));
        }
        if (startDate != null ) {
            detachedCriteria.add(Restrictions.ge("start_date",startDate));
        }
        return (List<ActivityDate>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
