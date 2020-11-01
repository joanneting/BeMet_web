package tw.com.BeMet.service;

import tw.com.BeMet.bean.ActivityRemindBean;

import java.util.List;

public interface ActivityRemindService {
    public ActivityRemindBean add(ActivityRemindBean activityRemindBean) throws Exception;

    public ActivityRemindBean update(ActivityRemindBean activityRemindBean) throws Exception;

    public List<ActivityRemindBean> search(ActivityRemindBean activityRemindBean) throws Exception;

    public List<ActivityRemindBean> searchAll() throws Exception;

    public void delete(Integer activityRemindNo) throws Exception;

}
