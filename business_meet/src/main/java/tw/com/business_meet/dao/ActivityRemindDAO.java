package tw.com.business_meet.dao;

import tw.com.business_meet.bean.ActivityRemindBean;
import tw.com.business_meet.vo.ActivityRemind;

import java.util.List;

public interface ActivityRemindDAO extends BaseDAO<ActivityRemind> {
    public List<ActivityRemind> search(ActivityRemindBean activityRemindBean);
}
