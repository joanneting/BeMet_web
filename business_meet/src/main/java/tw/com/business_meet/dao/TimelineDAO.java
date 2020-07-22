package tw.com.business_meet.dao;

import tw.com.business_meet.bean.TimelineBean;
import tw.com.business_meet.vo.Timeline;

import java.util.List;


public interface TimelineDAO extends BaseDAO<Timeline> {
    public List<Timeline> search(TimelineBean timelineBean);
}
