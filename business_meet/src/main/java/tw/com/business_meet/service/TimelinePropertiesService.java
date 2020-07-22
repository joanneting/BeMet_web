package tw.com.business_meet.service;

import tw.com.business_meet.bean.TimelinePropertiesBean;

import java.util.List;

public interface TimelinePropertiesService {
    public TimelinePropertiesBean add(TimelinePropertiesBean timelinePropertiesBean) throws Exception;

    public TimelinePropertiesBean update(TimelinePropertiesBean timelinePropertiesBean) throws Exception;

    public List<TimelinePropertiesBean> search(TimelinePropertiesBean timelinePropertiesBean) throws Exception;

    public List<TimelinePropertiesBean> searchAll() throws Exception;

    public void delete(Integer timelinePropertiesNo) throws Exception;
}
