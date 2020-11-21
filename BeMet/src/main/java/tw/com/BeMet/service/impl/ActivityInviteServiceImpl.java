package tw.com.BeMet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.BeMet.bean.ActivityDateBean;
import tw.com.BeMet.bean.ActivityInviteBean;
import tw.com.BeMet.dao.ActivityDateDAO;
import tw.com.BeMet.dao.ActivityInviteDAO;
import tw.com.BeMet.dao.TimelineDAO;
import tw.com.BeMet.dao.UserInformationDAO;
import tw.com.BeMet.service.ActivityInviteService;
import tw.com.BeMet.utils.BeanUtility;
import tw.com.BeMet.vo.ActivityDate;
import tw.com.BeMet.vo.ActivityInvite;
import tw.com.BeMet.vo.Timeline;
import tw.com.BeMet.vo.UserInformation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityInviteServiceImpl implements ActivityInviteService {
    @Autowired
    ActivityInviteDAO activityInviteDAO;
    @Autowired
    UserInformationDAO userInformationDAO;
    @Autowired
    TimelineDAO timelineDAO;
    @Autowired
    ActivityDateDAO activityDateDAO;

    @Override
    public List<ActivityInviteBean> search(ActivityInviteBean activityInviteBean) throws Exception {
        List<ActivityInvite> activityInviteList = activityInviteDAO.search(activityInviteBean);
        List<ActivityInviteBean> activityInviteBeanList = new ArrayList<>();
        for (ActivityInvite activityInvite : activityInviteList) {
            ActivityInviteBean aib = new ActivityInviteBean();
            BeanUtility.copyProperties(activityInvite, aib);
            UserInformation userInformation = userInformationDAO.getById(aib.getUserId());
            Timeline timeline = timelineDAO.getById(aib.getActivityNo());
            ActivityDateBean activityDateBean = new ActivityDateBean();
            activityDateBean.setActivityNo(aib.getActivityNo());
            List<ActivityDate> activityDateList = activityDateDAO.search(activityDateBean);
            ActivityDate activityDate;
            if (activityDateList.size() > 0) {
                activityDate = activityDateList.get(0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date startDate = activityDate.getStartDate();
                Date endDate = activityDate.getEndDate();
                aib.setActivityDate(simpleDateFormat.format(startDate) + "-" + simpleDateFormat.format(endDate));
            }
            aib.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(aib.getCreateDate()));
            aib.setPlace(timeline.getPlace());
            aib.setTitle(timeline.getTitle());
            aib.setUserName(userInformation.getName());
            aib.setAvatar(userInformation.getAvatar());
            activityInviteBeanList.add(aib);
        }
        return activityInviteBeanList;
    }

    @Override
    public List<ActivityInviteBean> searchAccept(ActivityInviteBean activityInviteBean) throws Exception {
        List<ActivityInvite> activityInviteList = activityInviteDAO.searchAccept(activityInviteBean);
        List<ActivityInviteBean> activityInviteBeanList = new ArrayList<>();
        for (ActivityInvite activityInvite : activityInviteList) {
            ActivityInviteBean aib = new ActivityInviteBean();
            BeanUtility.copyProperties(activityInvite, aib);
            UserInformation userInformation = userInformationDAO.getById(aib.getUserId());
            Timeline timeline = timelineDAO.getById(aib.getActivityNo());
            ActivityDateBean activityDateBean = new ActivityDateBean();
            activityDateBean.setActivityNo(aib.getActivityNo());
            ActivityDate activityDate = activityDateDAO.search(activityDateBean).get(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date startDate = activityDate.getStartDate();
            Date endDate = activityDate.getEndDate();
            aib.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(aib.getCreateDate()));
            aib.setActivityDate(simpleDateFormat.format(startDate) + "-" + simpleDateFormat.format(endDate));
            aib.setPlace(timeline.getPlace());
            aib.setTitle(timeline.getTitle());
            aib.setUserName(userInformation.getName());
            aib.setAvatar(userInformation.getAvatar());
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
        activityInviteBean.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(activityInvite.getCreateDate()));
        return activityInviteBean;
    }

    @Override
    public ActivityInviteBean update(ActivityInviteBean activityInviteBean) throws Exception {
        ActivityInvite activityInvite = activityInviteDAO.getById(activityInviteBean.getActivityInviteNo());
        activityInviteBean.setModifyDate(new Date());
        BeanUtility.copyProperties(activityInviteBean, activityInvite);
        activityInviteDAO.update(activityInvite);
        BeanUtility.copyProperties(activityInvite, activityInviteBean);
        activityInviteBean.setModifyDateStr(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(activityInvite.getModifyDate()));

        return activityInviteBean;
    }

    @Override
    public void delete(Integer activityInviteNo) throws Exception {
        ActivityInvite activityInvite = activityInviteDAO.getById(activityInviteNo);
        activityInviteDAO.delete(activityInvite);
    }

    @Override
    public ActivityInviteBean getById(Integer activityInviteNo) throws Exception {
        ActivityInvite activityInvite = activityInviteDAO.getById(activityInviteNo);
        ActivityInviteBean activityInviteBean = new ActivityInviteBean();
        BeanUtility.copyProperties(activityInvite, activityInviteBean);
        return activityInviteBean;
    }
}
