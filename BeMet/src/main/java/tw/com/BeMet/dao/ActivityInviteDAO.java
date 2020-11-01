package tw.com.BeMet.dao;

import tw.com.BeMet.bean.ActivityInviteBean;
import tw.com.BeMet.vo.ActivityInvite;

import java.util.List;

public interface ActivityInviteDAO extends BaseDAO<ActivityInvite> {
    public List<ActivityInvite> search(ActivityInviteBean activityInviteBean);
    public List<ActivityInvite> searchAccept(ActivityInviteBean activityInviteBean);

}
