package tw.com.BeMet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.BeMet.bean.ActivityDateBean;
import tw.com.BeMet.dao.ActivityDateDAO;
import tw.com.BeMet.service.ActivityDateService;
import tw.com.BeMet.utils.BeanUtility;
import tw.com.BeMet.vo.ActivityDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityDateServiceImpl implements ActivityDateService {
    @Autowired
    ActivityDateDAO activityDateDAO;

    @Override
    public ActivityDateBean add(ActivityDateBean activityDateBean) throws Exception {
        ActivityDate activityDate = new ActivityDate();
        activityDateBean.setCreateDate(new Date());
        BeanUtility.copyProperties(activityDateBean, activityDate);
        activityDate = activityDateDAO.saveAndReturn(activityDate);
        BeanUtility.copyProperties(activityDate, activityDateBean);
        return activityDateBean;
    }

    @Override
    public ActivityDateBean update(ActivityDateBean activityDateBean) throws Exception {
        ActivityDate activityDate = activityDateDAO.getById(activityDateBean.getActivityDateNo());
        activityDateBean.setModifyDate(new Date());
        BeanUtility.copyProperties(activityDateBean, activityDate);
        activityDateDAO.update(activityDate);
        BeanUtility.copyProperties(activityDate, activityDateBean);
        return activityDateBean;
    }

    @Override
    public List<ActivityDateBean> search(ActivityDateBean activityDateBean) throws Exception {
        List<ActivityDate> activityDateList = activityDateDAO.search(activityDateBean);
        List<ActivityDateBean> activityDateBeanList = new ArrayList<>();
        for (ActivityDate activityDate :
                activityDateList) {
            ActivityDateBean tb = new ActivityDateBean();
            BeanUtility.copyProperties(activityDate, tb);
            activityDateBeanList.add(tb);
        }

        return activityDateBeanList;
    }

    @Override
    public List<ActivityDateBean> searchAll() throws Exception {
        List<ActivityDate> activityDateList = activityDateDAO.searchAll();
        List<ActivityDateBean> activityDateBeanList = new ArrayList<>();
        for (ActivityDate activityDate :
                activityDateList) {
            ActivityDateBean tb = new ActivityDateBean();
            BeanUtility.copyProperties(activityDate, tb);
            activityDateBeanList.add(tb);
        }

        return activityDateBeanList;
    }

    @Override
    public void delete(Integer activityDateNo) throws Exception {
        ActivityDate activityDate = activityDateDAO.getById(activityDateNo);
        activityDateDAO.delete(activityDate);
    }
}
