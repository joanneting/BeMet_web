package tw.com.BeMet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.BeMet.bean.TimelineBean;
import tw.com.BeMet.dao.TimelineDAO;
import tw.com.BeMet.vo.Timeline;

import java.util.List;

@Repository
public class TimelineDAOImpl extends BaseDAOImpl<Timeline> implements TimelineDAO {
    @Override
    public List<Timeline> search(TimelineBean timelineBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Timeline.class);
        String color = timelineBean.getColor();
        String place = timelineBean.getPlace();
        String remark = timelineBean.getRemark();
        Integer timelinePropertiesNo = timelineBean.getTimelinePropertiesNo();
        String title = timelineBean.getTitle();
        String friendId = timelineBean.getFriendId();
        String matchmakerId = timelineBean.getMatchmakerId();
        if (matchmakerId != null && !matchmakerId.equals("")) {
            detachedCriteria.add(Restrictions.eq("matchmakerId",matchmakerId));
        }
        if (friendId != null && !friendId.equals("")) {
            detachedCriteria.add(Restrictions.eq("friendId",friendId));
        }else{
            detachedCriteria.add(Restrictions.isNull("friendId"));
        }

        if (color != null && !color.equals("")) {
            detachedCriteria.add(Restrictions.eq("color", color));
        }
        if (place != null && !place.equals("")) {
            detachedCriteria.add(Restrictions.like("place", "%" + place + "%"));
        }
        if (remark != null && !remark.equals("")) {
            detachedCriteria.add(Restrictions.like("remark", "%" + remark + "%"));
        }
        if (timelinePropertiesNo != null && !timelinePropertiesNo.equals("")) {
            detachedCriteria.add(Restrictions.eq("timelinePropertiesNo", timelinePropertiesNo));
        }
        if (title != null && !title.equals("")) {
            detachedCriteria.add(Restrictions.like("title", "%" + title + "%"));
        }

        return (List<Timeline>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
