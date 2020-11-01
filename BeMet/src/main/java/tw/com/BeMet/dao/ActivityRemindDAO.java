package tw.com.BeMet.dao;

import tw.com.BeMet.bean.ActivityRemindBean;
import tw.com.BeMet.vo.ActivityRemind;

import java.util.List;

public interface ActivityRemindDAO extends BaseDAO<ActivityRemind> {
    public List<ActivityRemind> search(ActivityRemindBean activityRemindBean);
}
