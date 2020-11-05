package tw.com.BeMet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.BeMet.bean.ActivityLabelBean;
import tw.com.BeMet.dao.ActivityLabelDAO;
import tw.com.BeMet.vo.ActivityLabel;

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
