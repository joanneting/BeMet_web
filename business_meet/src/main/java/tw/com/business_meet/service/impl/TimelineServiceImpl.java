package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.TimelineBean;
import tw.com.business_meet.dao.TimelineDAO;
import tw.com.business_meet.service.TimelineService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.Timeline;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Service
public class TimelineServiceImpl implements TimelineService {
    @Autowired
    TimelineDAO timelineDAO;

    @Override
    public TimelineBean add(TimelineBean timelineBean) throws Exception {
        Timeline timeline = new Timeline();
        timelineBean.setCreateDate(new Date());
        BeanUtility.copyProperties(timelineBean, timeline);
        timeline = timelineDAO.saveAndReturn(timeline);
        BeanUtility.copyProperties(timeline, timelineBean);
        return timelineBean;
    }

    @Override
    public TimelineBean update(TimelineBean timelineBean) throws Exception {
        Timeline timeline = timelineDAO.getById(timelineBean.getTimelineNo());
        timelineBean.setModifyDate(new Date());
        BeanUtility.copyProperties(timelineBean, timeline);
        timelineDAO.update(timeline);
        BeanUtility.copyProperties(timeline, timelineBean);
        return timelineBean;
    }

    @Override
    public List<TimelineBean> search(TimelineBean timelineBean) throws Exception {
        List<Timeline> timelineList = timelineDAO.search(timelineBean);
        List<TimelineBean> timelineBeanList = new ArrayList<>();
        for (Timeline timeline :
                timelineList) {
            TimelineBean tb = new TimelineBean();
            BeanUtility.copyProperties(timeline, tb);
           timelineBeanList.add(tb);
        }

        return timelineBeanList;
    }
@Override
    public TimelineBean getById(Integer timelineNo) throws Exception {
        Timeline timeline = timelineDAO.getById(timelineNo);
        TimelineBean timelineBean = new TimelineBean();
        BeanUtility.copyProperties(timeline,timelineBean);
        return timelineBean;
    }

    @Override
    public List<TimelineBean> searchAll() throws Exception {
        List<Timeline> timelineList = timelineDAO.searchAll();
        List<TimelineBean> timelineBeanList = new ArrayList<>();
        for (Timeline timeline :
                timelineList) {
            TimelineBean tb = new TimelineBean();
            BeanUtility.copyProperties(timeline, tb);
            timelineBeanList.add(tb);
        }

        return timelineBeanList;
    }

    @Override
    public void delete(Integer timelineNo) throws Exception {
        Timeline timeline = timelineDAO.getById(timelineNo);
        timelineDAO.delete(timeline);
    }
}
