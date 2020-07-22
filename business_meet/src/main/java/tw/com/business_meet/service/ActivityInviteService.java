package tw.com.business_meet.service;

import tw.com.business_meet.bean.ActivityInviteBean;

import java.util.List;

public interface ActivityInviteService {
    public List<ActivityInviteBean> search(ActivityInviteBean activityInviteBean) throws Exception;

    public List<ActivityInviteBean> searchAll() throws Exception;

    public ActivityInviteBean add(ActivityInviteBean activityInviteBean) throws Exception;

    public ActivityInviteBean update(ActivityInviteBean activityInviteBean) throws Exception;

    public void delete(Integer activityInviteNo) throws Exception;

}
