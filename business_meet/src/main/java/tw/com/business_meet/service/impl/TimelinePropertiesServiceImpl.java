package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.TimelinePropertiesBean;
import tw.com.business_meet.dao.TimelinePropertiesDAO;
import tw.com.business_meet.service.TimelinePropertiesService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.TimelineProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TimelinePropertiesServiceImpl implements TimelinePropertiesService {
    @Autowired
    TimelinePropertiesDAO timelinePropertiesDAO;

    @Override
    public TimelinePropertiesBean add(TimelinePropertiesBean timelinePropertiesBean) throws Exception {
        TimelineProperties timelineProperties = new TimelineProperties();
        timelinePropertiesBean.setCreateDate(new Date());
        BeanUtility.copyProperties(timelinePropertiesBean, timelineProperties);
        timelineProperties = timelinePropertiesDAO.saveAndReturn(timelineProperties);
        BeanUtility.copyProperties(timelineProperties, timelinePropertiesBean);
        return timelinePropertiesBean;
    }

    @Override
    public TimelinePropertiesBean update(TimelinePropertiesBean timelinePropertiesBean) throws Exception {
        TimelineProperties timelineProperties = timelinePropertiesDAO.getById(timelinePropertiesBean.getTimelinePropertiesNo());
        timelinePropertiesBean.setModifyDate(new Date());
        BeanUtility.copyProperties(timelinePropertiesBean, timelineProperties);
        timelinePropertiesDAO.update(timelineProperties);
        BeanUtility.copyProperties(timelineProperties, timelinePropertiesBean);
        return timelinePropertiesBean;
    }

    @Override
    public List<TimelinePropertiesBean> search(TimelinePropertiesBean timelinePropertiesBean) throws Exception {
        List<TimelineProperties> timelinePropertiesList = timelinePropertiesDAO.search(timelinePropertiesBean);
        List<TimelinePropertiesBean> timelinePropertiesBeanList = new ArrayList<>();
        for (TimelineProperties timelineProperties :
                timelinePropertiesList) {
            TimelinePropertiesBean tpb = new TimelinePropertiesBean();
            BeanUtility.copyProperties(timelineProperties, tpb);
            timelinePropertiesBeanList.add(tpb);
        }
        return timelinePropertiesBeanList;
    }

    @Override
    public List<TimelinePropertiesBean> searchAll() throws Exception {
        List<TimelineProperties> timelinePropertiesList = timelinePropertiesDAO.searchAll();
        List<TimelinePropertiesBean> timelinePropertiesBeanList = new ArrayList<>();
        for (TimelineProperties timelineProperties :
                timelinePropertiesList) {
            TimelinePropertiesBean tpb = new TimelinePropertiesBean();
            BeanUtility.copyProperties(timelineProperties, tpb);
            timelinePropertiesBeanList.add(tpb);
        }
        return timelinePropertiesBeanList;
    }

    @Override
    public void delete(Integer timelinePropertiesNo) throws Exception {
        TimelineProperties timelineProperties = timelinePropertiesDAO.getById(timelinePropertiesNo);
        timelinePropertiesDAO.delete(timelineProperties);
    }
}
