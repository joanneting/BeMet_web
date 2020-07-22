package tw.com.business_meet.dao;

import tw.com.business_meet.bean.TimelinePropertiesBean;
import tw.com.business_meet.vo.TimelineProperties;

import java.util.List;

public interface TimelinePropertiesDAO extends BaseDAO<TimelineProperties> {
    public List<TimelineProperties> search(TimelinePropertiesBean timelinePropertiesBean);
}
