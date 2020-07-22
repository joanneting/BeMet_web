package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tw.com.business_meet.bean.ActivityInviteBean;
import tw.com.business_meet.dao.ActivityInviteDAO;
import tw.com.business_meet.service.ActivityInviteService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.ActivityInvite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityInviteServiceImpl implements ActivityInviteService {
    @Autowired
    ActivityInviteDAO activityInviteDAO;

    @Override
    public List<ActivityInviteBean> search(ActivityInviteBean activityInviteBean) throws Exception {
        List<ActivityInvite> activityInviteList = activityInviteDAO.search(activityInviteBean);
        List<ActivityInviteBean> activityInviteBeanList = new ArrayList<>();
        for (ActivityInvite activityInvite : activityInviteList) {
            ActivityInviteBean aib = new ActivityInviteBean();
            BeanUtility.copyProperties(activityInvite, aib);
            activityInviteBeanList.add(aib);
        }
        return activityInviteBeanList;
    }

    @Override
    public List<ActivityInviteBean> searchAll() throws Exception {
        List<ActivityInvite> activityInviteList = activityInviteDAO.searchAll();
        List<ActivityInviteBean> activityInviteBeanList = new ArrayList<>();
        for (ActivityInvite activityInvite : activityInviteList) {
            ActivityInviteBean activityInviteBean = new ActivityInviteBean();
            BeanUtility.copyProperties(activityInvite, activityInviteBean);
            activityInviteBeanList.add(activityInviteBean);
        }
        return activityInviteBeanList;
    }

    @Override
    public ActivityInviteBean add(ActivityInviteBean activityInviteBean) throws Exception {
        ActivityInvite activityInvite = new ActivityInvite();
        activityInviteBean.setCreateDate(new Date());
        BeanUtility.copyProperties(activityInviteBean, activityInvite);
        activityInvite = activityInviteDAO.saveAndReturn(activityInvite);
        BeanUtility.copyProperties(activityInvite, activityInviteBean);
        return activityInviteBean;
    }

    @Override
    public ActivityInviteBean update(ActivityInviteBean activityInviteBean) throws Exception {
        ActivityInvite activityInvite = activityInviteDAO.getById(activityInviteBean.getActivityInviteNo());
        activityInviteBean.setModifyDate(new Date());
        BeanUtility.copyProperties(activityInviteBean, activityInvite);
        activityInviteDAO.update(activityInvite);
        BeanUtility.copyProperties(activityInvite, activityInviteBean);

        return activityInviteBean;
    }

    @Override
    public void delete(Integer activityInviteNo) throws Exception {
        ActivityInvite activityInvite = activityInviteDAO.getById(activityInviteNo);
        activityInviteDAO.delete(activityInvite);
    }
}
