package tw.com.BeMet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.BeMet.bean.ActivityDateBean;
import tw.com.BeMet.dao.ActivityDateDAO;
import tw.com.BeMet.vo.ActivityDate;

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
            detachedCriteria.add(Restrictions.eq("activityNo",activityNo));
        }
        if (endDate != null ) {
            detachedCriteria.add(Restrictions.le("endDate",endDate));
        }
        if (startDate != null ) {
            detachedCriteria.add(Restrictions.ge("startDate",startDate));
        }
        return (List<ActivityDate>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
