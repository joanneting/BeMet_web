package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.TimelinePropertiesBean;
import tw.com.business_meet.dao.TimelinePropertiesDAO;
import tw.com.business_meet.vo.TimelineProperties;

import java.util.List;

@Repository
public class TimelinePropertiesDAOImpl extends BaseDAOImpl<TimelineProperties> implements TimelinePropertiesDAO {
    @Override
    public List<TimelineProperties> search(TimelinePropertiesBean timelinePropertiesBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TimelineProperties.class);
        String name = timelinePropertiesBean.getName();
        if (name != null && !name.equals("")) {
            detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
        }

        return (List<TimelineProperties>) this.getHibernateTemplate().findByCriteria(detachedCriteria);

    }
}
