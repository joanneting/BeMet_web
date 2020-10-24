package tw.com.business_meet.dao;

import tw.com.business_meet.bean.ActivityInviteBean;
import tw.com.business_meet.vo.ActivityInvite;

import java.util.List;

public interface ActivityInviteDAO extends BaseDAO<ActivityInvite> {
    public List<ActivityInvite> search(ActivityInviteBean activityInviteBean);
    public List<ActivityInvite> searchAccept(ActivityInviteBean activityInviteBean);
//    public List<ActivityInvite> searchCommonActivity(ActivityInviteBean activityInviteBean);

}
