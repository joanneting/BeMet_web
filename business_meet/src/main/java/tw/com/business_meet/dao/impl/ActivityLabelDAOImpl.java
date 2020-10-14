package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.ActivityLabelBean;
import tw.com.business_meet.dao.ActivityLabelDAO;
import tw.com.business_meet.vo.ActivityLabel;

import java.util.List;

@Repository
public class ActivityLabelDAOImpl extends BaseDAOImpl<ActivityLabel> implements ActivityLabelDAO {
    @Override
    public List<ActivityLabel> search(ActivityLabelBean activityLabelBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ActivityLabel.class);
        Integer activityNo = activityLabelBean.getActivityNo();
        String content = activityLabelBean.getContent();
        if (activityNo != null && activityNo != 0) {
            detachedCriteria.add(Restrictions.eq("activityNo", activityNo));
        }
        if (content != null && !content.equals("")) {
            detachedCriteria.add(Restrictions.like("content", "%" + content + "%"));
        }
        return (List<ActivityLabel>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
