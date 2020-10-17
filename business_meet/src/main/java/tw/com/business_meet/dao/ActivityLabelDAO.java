package tw.com.business_meet.dao;

import tw.com.business_meet.bean.ActivityLabelBean;
import tw.com.business_meet.vo.ActivityLabel;

import java.util.List;

public interface ActivityLabelDAO extends BaseDAO<ActivityLabel> {
    public List<ActivityLabel> search(ActivityLabelBean activityLabelBean);
}
