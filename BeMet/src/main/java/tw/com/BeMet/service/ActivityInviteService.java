package tw.com.BeMet.service;

import tw.com.BeMet.bean.ActivityInviteBean;

import java.util.List;

public interface ActivityInviteService {
    public List<ActivityInviteBean> search(ActivityInviteBean activityInviteBean) throws Exception;
    public List<ActivityInviteBean> searchAccept(ActivityInviteBean activityInviteBean) throws Exception;
//    public List<ActivityInviteBean> searchCommonActivity(ActivityInviteBean activityInviteBean) throws Exception;

    public List<ActivityInviteBean> searchAll() throws Exception;

    public ActivityInviteBean add(ActivityInviteBean activityInviteBean) throws Exception;

    public ActivityInviteBean update(ActivityInviteBean activityInviteBean) throws Exception;
    public ActivityInviteBean getById(Integer activityInviteNo) throws Exception;

    public void delete(Integer activityInviteNo) throws Exception;

}
