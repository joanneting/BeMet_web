package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.ActivityLabelBean;
import tw.com.business_meet.dao.ActivityLabelDAO;
import tw.com.business_meet.dao.UserInformationDAO;
import tw.com.business_meet.service.ActivityLabelService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.ActivityLabel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityLabelServiceImpl implements ActivityLabelService {
    @Autowired
    ActivityLabelDAO activityLabelDAO;

    @Override
    public ActivityLabelBean add(ActivityLabelBean activityLabelBean) throws Exception {
        ActivityLabel activityLabel = new ActivityLabel();
        activityLabelBean.setCreateDate(new Date());
        BeanUtility.copyProperties(activityLabelBean, activityLabel);
        activityLabel = activityLabelDAO.saveAndReturn(activityLabel);
        BeanUtility.copyProperties(activityLabel, activityLabelBean);
        return activityLabelBean;
    }

    @Override
    public ActivityLabelBean update(ActivityLabelBean activityLabelBean) throws Exception {
        ActivityLabel activityLabel = activityLabelDAO.getById(activityLabelBean.getActivityLabelNo());
        activityLabelBean.setModifyDate(new Date());
        BeanUtility.copyProperties(activityLabelBean, activityLabel);
        activityLabelDAO.update(activityLabel);
        BeanUtility.copyProperties(activityLabel, activityLabelBean);
        return activityLabelBean;
    }

    @Override
    public List<ActivityLabelBean> searchAll() throws Exception {
        List<ActivityLabel> activityLabelList = activityLabelDAO.searchAll();
        List<ActivityLabelBean> activityLabelBeanList = new ArrayList<>();
        for (ActivityLabel activityLabel :
                activityLabelList) {
            ActivityLabelBean activityLabelBean = new ActivityLabelBean();
            BeanUtility.copyProperties(activityLabel, activityLabelBean);
            activityLabelBeanList.add(activityLabelBean);
        }
        return activityLabelBeanList;
    }

    @Override
    public List<ActivityLabelBean> search(ActivityLabelBean activityLabelBean) throws Exception {
        List<ActivityLabel> activityLabelList = activityLabelDAO.search(activityLabelBean);
        List<ActivityLabelBean> activityLabelBeanList = new ArrayList<>();
        for (ActivityLabel activityLabel :
                activityLabelList) {
            ActivityLabelBean alb = new ActivityLabelBean();
            BeanUtility.copyProperties(activityLabel, alb);
            activityLabelBeanList.add(alb);
        }
        return activityLabelBeanList;
    }

    @Override
    public void delete(Integer activityLabelNo) throws Exception {
        ActivityLabel activityLabel = activityLabelDAO.getById(activityLabelNo);
        activityLabelDAO.delete(activityLabel);

    }
}
