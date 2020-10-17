package tw.com.business_meet.service;

import tw.com.business_meet.bean.ActivityLabelBean;

import java.util.List;

public interface ActivityLabelService {
    public ActivityLabelBean add(ActivityLabelBean activityLabelBean) throws Exception;

    public ActivityLabelBean update(ActivityLabelBean activityLabelBean) throws Exception;

    public List<ActivityLabelBean> searchAll() throws Exception;

    public List<ActivityLabelBean> search(ActivityLabelBean activityLabelBean) throws Exception;

    public void delete(Integer activityLabelNo) throws Exception;
    public ActivityLabelBean getById(Integer activityLabelNo) throws Exception;
}
