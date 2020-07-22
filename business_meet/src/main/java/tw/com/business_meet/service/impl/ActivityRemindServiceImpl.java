package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tw.com.business_meet.bean.ActivityRemindBean;
import tw.com.business_meet.dao.ActivityRemindDAO;
import tw.com.business_meet.service.ActivityRemindService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.ActivityRemind;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityRemindServiceImpl implements ActivityRemindService {
    @Autowired
    ActivityRemindDAO activityRemindDAO;

    @Override
    public ActivityRemindBean add(ActivityRemindBean activityRemindBean) throws Exception {
        ActivityRemind activityRemind = new ActivityRemind();
        activityRemindBean.setCreateDate(new Date());
        BeanUtility.copyProperties(activityRemindBean, activityRemind);
        activityRemind = activityRemindDAO.saveAndReturn(activityRemind);
        BeanUtility.copyProperties(activityRemind, activityRemindBean);
        return activityRemindBean;
    }

    @Override
    public ActivityRemindBean update(ActivityRemindBean activityRemindBean) throws Exception {
        ActivityRemind activityRemind = activityRemindDAO.getById(activityRemindBean.getActivityRemindNo());
        activityRemindBean.setModifyDate(new Date());
        BeanUtility.copyProperties(activityRemindBean, activityRemind);
        activityRemindDAO.update(activityRemind);
        BeanUtility.copyProperties(activityRemind, activityRemindBean);
        return activityRemindBean;
    }

    @Override
    public List<ActivityRemindBean> search(ActivityRemindBean activityRemindBean) throws Exception {
        List<ActivityRemind> activityRemindList = activityRemindDAO.search(activityRemindBean);
        List<ActivityRemindBean> activityRemindBeanList = new ArrayList<>();
        for (ActivityRemind activityRemind :
                activityRemindList) {
            ActivityRemindBean arb = new ActivityRemindBean();
            BeanUtility.copyProperties(activityRemind, arb);
            activityRemindBeanList.add(arb);
        }
        return activityRemindBeanList;
    }

    @Override
    public List<ActivityRemindBean> searchAll() throws Exception {
        List<ActivityRemind> activityRemindList = activityRemindDAO.searchAll();
        List<ActivityRemindBean> activityRemindBeanList = new ArrayList<>();
        for (ActivityRemind activityRemind :
                activityRemindList) {
            ActivityRemindBean arb = new ActivityRemindBean();
            BeanUtility.copyProperties(activityRemind, arb);
            activityRemindBeanList.add(arb);
        }
        return activityRemindBeanList;
    }

    @Override
    public void delete(Integer activityRemindNo) throws Exception {
        ActivityRemind activityRemind = activityRemindDAO.getById(activityRemindNo);
        activityRemindDAO.delete(activityRemind);

    }
}
