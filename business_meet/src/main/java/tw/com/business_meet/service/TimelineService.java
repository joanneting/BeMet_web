package tw.com.business_meet.service;

import tw.com.business_meet.bean.TimelineBean;

import java.util.List;

public interface TimelineService {
    public TimelineBean add(TimelineBean timelineBean) throws Exception;

    public TimelineBean update(TimelineBean timelineBean) throws Exception;

    public List<TimelineBean> search(TimelineBean timelineBean) throws Exception;

    public List<TimelineBean> searchAll() throws Exception;

    public void delete(Integer timelineNo) throws Exception;
}
