package tw.com.BeMet.dao;

import tw.com.BeMet.bean.TimelineBean;
import tw.com.BeMet.vo.Timeline;

import java.util.List;


public interface TimelineDAO extends BaseDAO<Timeline> {
    public List<Timeline> search(TimelineBean timelineBean);
}
