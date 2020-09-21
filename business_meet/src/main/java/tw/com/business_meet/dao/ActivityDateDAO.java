package tw.com.business_meet.dao;

import tw.com.business_meet.bean.ActivityDateBean;
import tw.com.business_meet.vo.ActivityDate;

import java.util.List;


public interface ActivityDateDAO extends BaseDAO<ActivityDate> {
    public List<ActivityDate> search(ActivityDateBean activityDateBean);
}
