package tw.com.business_meet.service;

import tw.com.business_meet.bean.ActivityDateBean;

import java.util.List;

public interface ActivityDateService {
    public ActivityDateBean add(ActivityDateBean activityDateBean) throws Exception;

    public ActivityDateBean update(ActivityDateBean activityDateBean) throws Exception;

    public List<ActivityDateBean> search(ActivityDateBean activityDateBean) throws Exception;

    public List<ActivityDateBean> searchAll() throws Exception;

    public void delete(Integer activityDateNo) throws Exception;
}
